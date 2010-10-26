
package org.sgu.oecde.web.jsfbeans.tests;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.CurriculumDao;
import org.sgu.oecde.core.education.dao.ResourceDao;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.journal.Journal;
import org.sgu.oecde.tests.Answer;
import org.sgu.oecde.tests.AnsweredQuestion;
import org.sgu.oecde.tests.GivenAnswer;
import org.sgu.oecde.tests.Question;
import org.sgu.oecde.tests.QuestionType;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestService;
import org.sgu.oecde.tests.dao.TestAttemptDao;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.util.HTMLSanitiser;

/**
 * бин для прохождения теста
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 27.09.2010
 * @todo 
 */
@ManagedBean
@ViewScoped
public class TestPassingBean implements Serializable {

    long serialVersionUID = 123123L;

    private Long testId;
    private Long curriculumId;
    
    private Curriculum curriculum;
    private TestEntity testView; 
    
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

    int countRight=0;
    int points=0;
    private Long beginDate;
    private AbstractUser  currentUser;  
    
    @ManagedProperty(value="#{resourceDao}")
    private ResourceDao resourceDao;
    @ManagedProperty(value="#{testService}")
    private TestService testService;
    @ManagedProperty(value="#{testAttemptDao}")
    private TestAttemptDao testAttemptDao;
    @ManagedProperty(value="#{curriculumDao}")
    private CurriculumDao cDao;
    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;
    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;
    @ManagedProperty(value="#{journalServise}")
    private Journal journal;
    @Resource
    private SemesterGetter semesterGetter;

    public TestPassingBean() {
          currentUser = SecurityContextHandler.getUser();
    }
    
    public void setTestId(Long testId){
        this.testId = testId;
    }

    /**
     * сеттер для айди плана и проверка на доступ к тесту
     */
    public void setCurriculumId(Long curriculumId) throws MalformedURLException, IOException {
        this.curriculumId = curriculumId==null?0:curriculumId;
        curriculum=(Curriculum) cDao.getById(curriculumId);
        DeCurriculum c = resourceService.getDisciplineForStudent((Student) currentUser,curriculumId);
        if (c!=null){
           TestEntity t = resourceService.getResource(c,new TestEntity(testId),TestEntity.class);
           if(t!=null){
                    Object[] test = resourceService.getTestForStudent(testAttemptService.getStudentSingleTestWithAttempts(t, (AbstractStudent) currentUser, c), (Student) currentUser);
                    if(((Boolean) test[0])){
                            AdditionalSelfDependentWork work= (AdditionalSelfDependentWork) test[1];
                            testView=work.getWork();
                            checkTestAttemptType(work);
                            questions=new ArrayList<Question>(testView.getQuestions());
                            attempt.setWork(testView);
                            makeQuestionList();
                            beginDate= System.currentTimeMillis();
                            renderNoAccess=false;
                    }
           }
       }

    }

    /**
     * проверка типа попытки, если прошлый семестр - переэкзаменовка, если текущий - проверяем даты, если осталась попытка для пробного - пробный
     * @param w
     */
     private void checkTestAttemptType(AdditionalSelfDependentWork w){
        if (curriculum.getSemester()!=SemesterGetter.CURRENT_SEMESTER){
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
        makeAnswersList();        
    }

    /*
     * записываем ответ
     */
    public void answer() throws MalformedURLException, IOException{
       questionsView[iterQ].setAnswered(true);       
       AnsweredQuestion answeredQuestion =new AnsweredQuestion();
       //@todo проверить нормально ли что я кладу в вопрос еще не сформированную попытку
       answeredQuestion.setAttempt(attempt);
       HashSet givenAnswers=new HashSet();
       Boolean rightQuestion=false;
       Boolean checkFlag=false;

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
                              points+=10;
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
                      GivenAnswer ga=new GivenAnswer();
                      ga.setAnsweredQuestion(answeredQuestion);
                      ga.setRightAnswer(a);
                      for(Answer aS:selectedAnswers){
                          if (a.getId().equals(aS.getId())){
                              checkFlag=true;
                              ga.setGivenAnswer("1");
                              if (a.getRightAnswer().equals("0")){
                                  rightQuestion=false;
                              }
                          }else{                              
                              ga.setGivenAnswer("0");
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
                   points+=10;
                }
                  break;
              case text:
                   GivenAnswer ga=new GivenAnswer();
                   ga.setAnsweredQuestion(answeredQuestion);
                   Answer answer=(Answer) currentQustionView.getQuestion().getAnswers().toArray()[0];
                   ga.setRightAnswer(answer);
                   ga.setGivenAnswer(selectedAnswerText);
                  if(answer.getRightAnswer().toLowerCase().equals(selectedAnswerText.toLowerCase())){
                      rightQuestion=true;
                      countRight++;
                      points+=10;
                  }
                  break;
              case comparison:
                   int i=0;
                   rightQuestion=true;
                   for(Answer a:currentQustionView.getQuestion().getAnswers()){
                      GivenAnswer ga2=new GivenAnswer();
                      ga2.setAnsweredQuestion(answeredQuestion);
                      ga2.setRightAnswer(a);
                      ga2.setGivenAnswer(answerComparisnView.get(i++).getAnswer());
                      givenAnswers.add(ga2);
                   
                  }
                   for(AnswerComparisnView a:answerComparisnView){
                       ArrayList<Answer> array=new ArrayList(currentQustionView.getQuestion().getAnswers());
                       String titleQ =array.get(Integer.parseInt(a.getAnswer())-1).getRightAnswer();
                       String titleA=a.getTitle2();
                    if (!titleQ.equals(titleA)) rightQuestion=false;                    
                   }
                   if(rightQuestion){
                       countRight++;
                       points+=10;
                   }
                  break;  
          }
         answeredQuestion.setGivenAnswers(givenAnswers);  
         answeredQuestion.setRight(rightQuestion);
         answeredQuestion.setResultPoints(10);
         answeredQuestion.setQuestion(currentQustionView.getQuestion());
         answeredQuestions.add(answeredQuestion);     
      
         currentQustionView=questionsView[NumberNextQustion()];
         questionTitle=checkForFormulaOrLink(currentQustionView.getQuestion().getTitle());

         makeAnswersList();
    }

      /*
       * записываем весь тест в базу
       * @todo проверить какую именно попытку мы записываем
       */

    public void completeTest(){
        if (attempt!=null){
             renderCompleteTest=true;
             attempt.setPoints(points);
             attempt.setRightAnswers(countRight);
             attempt.setDuration(new Integer(Long.toString(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-beginDate))));
             attempt.setAnsweredQuestions(answeredQuestions);
             attempt.setCurriculum(curriculum);
             attempt.setStudent((AbstractStudent) currentUser);
             attempt.setType(testAttemptType);
             attempt.setDate(DateConverter.currentDate());
             testAttemptDao.saveAttempt(attempt);
             journal.logTestGrading(currentUser, testView.getId(), curriculum.getUmk().getId());
             attempt=null;}
    }

   

     /*
      * формируем списек вопросов
      */
     private void makeQuestionList() throws MalformedURLException, IOException {
        questionsView = new QuestionImpl[questions.size()];
        int i=0;
        for(Question q:questions){
            questionsView[i++]=new QuestionImpl(q);
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
            }
        }
        if ((type==QuestionType.comparison)){       
            answerComparisnView=new ArrayList<AnswerComparisnView>();
            List<String> titles2=new ArrayList<String>();
            for(Answer a:currentQustionView.getQuestion().getAnswers()){                
                answerComparisnView.add(new AnswerComparisnView(a.getTitle()));
                titles2.add(a.getRightAnswer());
            }
            Collections.shuffle(titles2);
            int i=0;
             for(AnswerComparisnView a:answerComparisnView){                
               a.setTitle2(titles2.get(i++));
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
        for(int i=0;i<questions.size();i++){
            f=questionsView[i].isAnswered()&f;
        }
        return f;
    }

    /*
     * проверяем есть в тексте формула или это хтмлка
     */
    private String checkForFormulaOrLink(String s)throws MalformedURLException, IOException {
        while (s.indexOf("$")!=-1){
          s= s.replaceFirst("\\$+"," <img src='http://oec.sgu.ru/latex/latex.php?code=");
          s= s.replaceFirst("\\$+"," '/> ");
         }
        if(s.indexOf("link:")!=-1){
             URL url = new URL("http://oecdo.sgu.ru/textbooks/avtomat_offise/m0-z4.html");
             String str="";
              StringBuffer strbuf = new StringBuffer();
              BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
              while ((str = in.readLine()) != null) {strbuf.append(str);}
              //str=strbuf.toString().replaceAll("src=\"", "src=\""+currentUrl+"/");
              s=HTMLSanitiser.encodeInvalidMarkup(strbuf.toString());
        }
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


    //-----------------------
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public void setTestAttemptDao(TestAttemptDao testAttemptDao) {
        this.testAttemptDao = testAttemptDao;
    }

    public void setcDao(CurriculumDao cDao) {
        this.cDao = cDao;
    }

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }


  }



