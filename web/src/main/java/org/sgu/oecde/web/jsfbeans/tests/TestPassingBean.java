
package org.sgu.oecde.web.jsfbeans.tests;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.CurriculumDao;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.tests.*;
import org.sgu.oecde.tests.dao.TestAttemptDao;
import org.sgu.oecde.web.ResourceService;

/**
 * бин для прохождения теста
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 27.09.2010
 * @todo 
 */
@ManagedBean(name="testPassingBean")
@ViewScoped
public class TestPassingBean implements Serializable {

    long serialVersionUID = 123123L;

    private Long testId;
    private Long curriculumId;
    
    private Curriculum curriculum;
    private TestEntity testView;

    private int countQuestions=0;
    private int countAnsweredquestion=0;
    
    private List<Question> questions;
    private QuestionImpl[] questionsView;
    private QuestionImpl currentQustionView;
    private String questionTitle;
    private TestAttemptType testAttemptType=TestAttemptType.regular;
    private int iterQ=0;

    private List<SelectItem> answers;
    private List<AnswerComparisnView> answerComparisnView;
    private Answer selectedAnswer;
    private List<Answer> selectedAnswers;
    private String selectedAnswerText;
    private List<String> selectedAnswerComparisn;

    private boolean renderNoAccess=true;
    private boolean renderCompleteTest=false;    

    private HashSet<AnsweredQuestion> answeredQuestions= new HashSet();
    private TestAttempt attempt=new TestAttempt();

    private int countRight=0;
    private int points=0;

    private Long beginDate;
    private AbstractUser  currentUser;
    //режим просмотра теста?
    private Boolean viewTest=false;
    
    @ManagedProperty(value="#{curriculumDao}")
    private CurriculumDao cDao;
    @ManagedProperty(value="#{testAttemptDao}")
    private TestAttemptDao testAttemptDao;
    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;
    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;
    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    public TestPassingBean() {
          currentUser = SecurityContextHandler.getUser();
    }
    
    public void setTestId(Long testId){
        this.testId = testId;
    }

    /**
     * сеттер для айди плана и проверка на доступ к тесту
     */
    public void startTest(Object[] test, Curriculum c) throws MalformedURLException, IOException {
       curriculum=c;
      // DeCurriculum c = resourceService.getDisciplineForStudent((Student) currentUser,curriculumId);
       AdditionalSelfDependentWork work= (AdditionalSelfDependentWork) test[1];
       testView=work.getWork();
       checkTestAttemptType(work);
        questions=new ArrayList<Question>(testView.getQuestions());
        if (!questions.isEmpty()){
            attempt.setWork(testView);
            countQuestions=(testView.getQuantity()<questions.size())?testView.getQuantity():questions.size();
            makeQuestionList();
            beginDate= System.currentTimeMillis();

       }
    }

     public void startAdminTest(TestEntity test, boolean view) throws MalformedURLException, IOException {
        testView=test;
        this.viewTest=view;
     //  checkTestAttemptType(testView);
        questions=new ArrayList<Question>(testView.getQuestions());
        if (!questions.isEmpty()){
            attempt.setWork(testView);
            countQuestions=questions.size();
            makeQuestionList();
       }
    }

     public void startTeacherTest(TestEntity test) throws MalformedURLException, IOException {
        testView=test;
        this.viewTest=true;
     //  checkTestAttemptType(testView);
        questions=new ArrayList<Question>(testView.getQuestions());
        if (!questions.isEmpty()){
            attempt.setWork(testView);
            countQuestions=questions.size();
            makeQuestionList();
       }
    }

    /**
     * проверка типа попытки, если прошлый семестр - переэкзаменовка, если текущий - проверяем даты, если осталась попытка для пробного - пробный
     * @param w
     * TODO брать семестр по кусру студента и сравнивать с учебным планом
     */
     private void checkTestAttemptType(AdditionalSelfDependentWork w){
        if (curriculum.getSemester() %2 ==(semesterGetter.getCurrentSemester())){
             String currentDate = DateConverter.currentDate();
             if((currentDate.compareTo(resourceService.getReExameBeginDate())>=0)&&(currentDate.compareTo(resourceService.getReExameEndDate())<0))
                testAttemptType=TestAttemptType.reTest;
             else
                 if(w.getTrialAttemptsUsedNumber()<w.getWork().getTrialNumber())
                     testAttemptType=TestAttemptType.trial;}
        else testAttemptType=TestAttemptType.reTest;

    }

    /*
     * вызываем следующий вопрос без записи
     */
    public void next() throws MalformedURLException, IOException{
        currentQustionView=questionsView[NumberNextQustion()];
         questionTitle=checkForFormulaOrLink(currentQustionView.getQuestion().getTitle());
      //  System.out.println("pre "+currentQustionView.getQuestion().getTitle());
      //  System.out.println("past "+questionTitle);

        makeAnswersList();        
    }

    /*
     * записываем ответ
     */
    public void answer() throws MalformedURLException, IOException{
       questionsView[iterQ].setAnswered(true);       
       AnsweredQuestion answeredQuestion =new AnsweredQuestion();    
       answeredQuestion.setAttempt(attempt);
       HashSet givenAnswers=new HashSet();
       Boolean rightQuestion=false;
       Boolean checkFlag=false;
       countAnsweredquestion++;

          switch(currentQustionView.getQuestion().getType()){
              case radio:              
                  for(Answer a:currentQustionView.getQuestion().getAnswers()){
                      GivenAnswer ga=new GivenAnswer();
                      ga.setAnsweredQuestion(answeredQuestion);
                      ga.setRightAnswer(a);
                      if (a.getId().equals(selectedAnswer.getId())){
                          ga.setGivenAnswer("1");
                          if (a.getRightAnswer().equals("1")){
                              rightQuestion=true;
                              countRight++;
                            //  points+=10;
                          }
                      }else{
                          ga.setGivenAnswer("0");
                      }                      
                      givenAnswers.add(ga);                  
                  }              
                  break;
              case check:
                    rightQuestion=true;
                    for(Answer a:currentQustionView.getQuestion().getAnswers()){
                      checkFlag=false;
                      GivenAnswer ga=new GivenAnswer();
                      ga.setAnsweredQuestion(answeredQuestion);
                      ga.setRightAnswer(a);
                      ga.setGivenAnswer("0");
                      for(Answer aS:selectedAnswers){
                          if (a.getId().equals(aS.getId())){
                              checkFlag=true;
                              ga.setGivenAnswer("1");
                              if (a.getRightAnswer().equals("0")){
                                  rightQuestion=false;
                              }
                          }else{                              
                            //  ga.setGivenAnswer("0");
                          }
                      }
                       if ((a.getRightAnswer().equals("1"))&&(!checkFlag)){
                            rightQuestion=false;
                            checkFlag=false;
                       }
                      givenAnswers.add(ga);
                  }
                if (rightQuestion){
                   countRight++;
                 //  points+=10;
                }
                  break;
              case text:
                   GivenAnswer ga=new GivenAnswer();
                   ga.setAnsweredQuestion(answeredQuestion);
                   Answer answer=  currentQustionView.getQuestion().getAnswers().iterator().next();
                   ga.setRightAnswer(answer);
                   ga.setGivenAnswer(selectedAnswerText);
                  if(answer.getRightAnswer().toLowerCase().trim().equals(selectedAnswerText.toLowerCase().trim())){
                      rightQuestion=true;
                      countRight++;
                    //  points+=10;
                  }
                  givenAnswers.add(ga);
                  selectedAnswerText="";
                  break;
              case comparison:
                   int i=0;
                   rightQuestion=true;
                //   ArrayList<Answer> array=new ArrayList(currentQustionView.getQuestion().getAnswers());
                   for(Answer a:currentQustionView.getQuestion().getAnswers()){
                      GivenAnswer ga2=new GivenAnswer();
                      ga2.setAnsweredQuestion(answeredQuestion);
                      ga2.setRightAnswer(a);
                      ga2.setGivenAnswer(answerComparisnView.get(Integer.parseInt(answerComparisnView.get(i).getAnswer())-1).getTitle2());
                      givenAnswers.add(ga2);
                      String titleQ =checkForFormulaOrLink(a.getRightAnswer());
                      String titleA=ga2.getGivenAnswer();
                      i++;
                      if (!titleQ.equals(titleA)) rightQuestion=false;
                   
                  }                
                   if(rightQuestion){
                       countRight++;
                     //  points+=10;
                   }
                  break;  
          }
         answeredQuestion.setGivenAnswers(givenAnswers);  
         answeredQuestion.setRight(rightQuestion);
         answeredQuestion.setResultPoints(10);
         answeredQuestion.setQuestion(currentQustionView.getQuestion());
         answeredQuestions.add(answeredQuestion);     
      
         currentQustionView=questionsView[NumberNextQustion()];
         questionTitle=(currentQustionView.getQuestion().getTitle()==null)?"":checkForFormulaOrLink(currentQustionView.getQuestion().getTitle());

         makeAnswersList();
    }

      /*
       * записываем весь тест в базу
       * 
       */

    public void completeTest(){
            if (attempt!=null){
                 renderCompleteTest=true;
                 countRight = coountRightQuestions();
                 if  (countQuestions<countRight) countQuestions = countRight;
                 points=(100*countRight)/countQuestions;
                 attempt.setPoints(points);
                 attempt.setRightAnswers(countRight);             
                 attempt.setAnsweredQuestions(answeredQuestions);
                 attempt.setCurriculum(curriculum); 
                 attempt.setDate(DateConverter.currentDate());
//        Collection<GrantedAuthority> authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        if(!SwitchedUserCheker.check(authority)){
            if(currentUser instanceof Student){
                attempt.setType(testAttemptType);
                attempt.setStudent((AbstractStudent) currentUser);
                attempt.setDuration(new Integer(Long.toString(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-beginDate))));
                testAttemptDao.saveAttempt(attempt);
                journalService.save(EventType.TEST_END, currentUser,curriculum.getUmk(),testView );
             }
                 attempt=null;}



    }

    private int coountRightQuestions(){
        int r=0;
        for(AnsweredQuestion aq :answeredQuestions)  {
            if (aq.getRight()) r+=1;
        }
        return r;
    }
     /*
      * формируем списек вопросов
      */
     private void makeQuestionList() throws MalformedURLException, IOException {        
       questionsView = new QuestionImpl[countQuestions];
        int i=0;
        Collections.shuffle(questions);
        for(Question q:questions){
            questionsView[i++]=new QuestionImpl(q);
            if (i>=countQuestions) break;
        }
        currentQustionView=questionsView[0];
        questionTitle=checkForFormulaOrLink(currentQustionView.getQuestion().getTitle());
        makeAnswersList();
    }

     /*
      * формируем список ответов
      */
     private void makeAnswersList() throws MalformedURLException, IOException {
        QuestionType type=currentQustionView.getQuestion().getType();
        if ((type==QuestionType.radio)||(type==QuestionType.check)){
            answers=new ArrayList<SelectItem>();
            for(Answer a:currentQustionView.getQuestion().getAnswers()){
                answers.add(new SelectItem(a, checkForFormulaOrLink(a.getTitle())));
                //System.out.println("!!"+answers.get(0).getLabel());
            }
        }
        if ((type==QuestionType.comparison)){       
            answerComparisnView=new ArrayList<AnswerComparisnView>();
            List<String> titles2=new ArrayList<String>();
            for(Answer a:currentQustionView.getQuestion().getAnswers()){                
                answerComparisnView.add(new AnswerComparisnView(checkForFormulaOrLink(a.getTitle())));
                titles2.add(a.getRightAnswer());
            }
            Collections.shuffle(titles2);
            int i=0;
             for(AnswerComparisnView a:answerComparisnView){                
             a.setTitle2(checkForFormulaOrLink(titles2.get(i++)));
            }
             
        }

    }
     /*
      * определяет номер следующего
      */
     private int NumberNextQustion(){
        if(checkListQuestion()){
          completeTest();
          return iterQ=0;
        }
        iterQ++;
        if (iterQ>(questionsView.length-1)){
            iterQ=0;
            while(questionsView[iterQ].isAnswered())iterQ++;
        }else{
            while((questionsView[iterQ].isAnswered())&&(iterQ<(questionsView.length-1)))iterQ++;
            if ((iterQ==questionsView.length-1)&&(questionsView[iterQ].isAnswered())){
                iterQ=0;
                while(questionsView[iterQ].isAnswered())iterQ++;
            }

        }
        return iterQ;
    }

     /*
      * проверка пройден ли список
      */
    private boolean checkListQuestion(){
        boolean f=true;
        for(int i=0;i<countQuestions;i++){
            f=questionsView[i].isAnswered()&f;
        }
        return f;
    }

    /*
     * проверяем есть в тексте формула или это хтмлка
     */
    static public String checkForFormulaOrLink(String s)throws MalformedURLException, IOException {
        if((s.indexOf("$")!=-1)&&(s.indexOf("$",s.indexOf("$")+1 ))!=-1){          
           // s=s.replaceAll("\\$+[*\\+*]\\$+", "$$%2B");
            AbstractUser currentUser = SecurityContextHandler.getUser();
            while (s.indexOf("$")!=-1){
               StringBuilder str =new StringBuilder(s);
               String replaceString="";
               if (s.indexOf("$$")==-1)
                    replaceString=str.substring(str.indexOf("$"), str.indexOf("$",str.indexOf("$")+1 )+1).replaceAll("\\+", "%2B");
               else replaceString=str.substring(str.indexOf("$"), str.indexOf("$",str.indexOf("$")+2 )+2).replaceAll("\\+", "%2B");

              // str=str.replace(str.indexOf("$"), str.indexOf("$",str.indexOf("$")+2 )+2, replaceString);
               s=str.toString();
               // System.out.println("check "+s);
//http://oec.sgu.ru/latex/latex.php?code=\color{red}A=\x5C
              s= s.replaceFirst("\\$+"," <img src='http://oec.sgu.ru/latex/latex.php?code=\\\\color{"+currentUser.getSkin().getFormulaColor()+"}");
              while ((s.indexOf("+")!=-1)&&(s.indexOf("$")>s.indexOf("+"))){
               s=s.replaceFirst("\\+", "%2b");            
              }
              s= s.replaceFirst("\\$+"," '/> ");
             }
        }
      //  System.out.println("________megacheck "+s);
        return s;
    }

    public List<AnswerComparisnView> getAnswerComparisnView() {
        return answerComparisnView;
    }

    public void setAnswerComparisnView(List<AnswerComparisnView> answerComparisnView) {
        this.answerComparisnView = answerComparisnView;
    }
    
    

    public Long getCurriculumId() {
        return curriculumId;
    }

    public Long getTestId() {
        return testId;
    }
    
    public int getCountRight() {
        return countRight;
    }


    public int getPoints() {
        return points;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<SelectItem> getAnswers() {
        return answers;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    } 
  
    public QuestionImpl getCurrentQustionView() {
        return currentQustionView;
    }

    public void setCurrentQustionView(QuestionImpl currentQustionView) {
        this.currentQustionView = currentQustionView;
    }

    public QuestionImpl[] getQuestionsView() {
        return questionsView;
    }

    public void setQuestionsView(QuestionImpl[] questionsView) {
        this.questionsView = questionsView;
    }

    public List<Answer> getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(List<Answer> selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public String getSelectedAnswerText() {
        return selectedAnswerText;
    }

    public void setSelectedAnswerText(String selectedAnswerText) {
        this.selectedAnswerText = selectedAnswerText;
    }
    
    
    public boolean isRenderCompleteTest() {
        return renderCompleteTest;
    }

    public boolean isRenderNoAccess() {
        return renderNoAccess;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }
    
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getTimeTest(){
          return testView.getDuration().toString();
    }

    public int getCountAnsweredquestion() {
        return countAnsweredquestion;
    }

    public int getCountQuestions() {
        return countQuestions;
    }



    public void setcDao(CurriculumDao cDao) {
        this.cDao = cDao;
    }

    public void setTestAttemptDao(TestAttemptDao testAttemptDao) {
        this.testAttemptDao = testAttemptDao;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

    public int getIterQ() {
        return iterQ;
    }

    public void setIterQ(int iterQ) {
        this.iterQ = iterQ;
    }

    public Boolean getViewTest() {
        return viewTest;
    }

    public void setViewTest(Boolean viewTest) {
        this.viewTest = viewTest;
    }


  }