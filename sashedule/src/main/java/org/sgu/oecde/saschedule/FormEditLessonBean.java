package org.sgu.oecde.saschedule;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.schedule.CityWithGroup;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.LessonType;
import org.sgu.oecde.schedule.dao.ILessonDao;


/**
 *
 * @author MalyginAV
 * ,бин для сохранения занятия
 */
@ManagedBean(name="FormEditLessonBean")
@SessionScoped
public class FormEditLessonBean implements Serializable {

   @ManagedProperty(value="#{teacherDao}")
   IBasicDao teacherDao;

   @ManagedProperty(value="#{curriculumDao}")
   ICurriculumDao curriculumDao;
   @ManagedProperty(value="#{lessonDao}")
   ILessonDao lessonDao;
   @ManagedProperty(value="#{EditLessonBean}")
   EditLessonBean lessonBean;
   @ManagedProperty(value="#{EditDayBean}")
   EditDayBean dayBean;
   @ManagedProperty(value="#{semesterGetter}")
   private SemesterGetter semesterGetter;

   private Set<Discipline> disciplines;

   
   private LessonType type= LessonType.consult;
   private List<CityWithGroup> css = new ArrayList<CityWithGroup>();
   private List<CityWithGroup>  cssResult = new ArrayList<CityWithGroup>();
   private List<Group> cssNotReady = new ArrayList<Group>();
  // private Map cities=new HashMap<City, HashSet<Group>>();
   //private List citiesView;
   private Set<CityWithGroup> cityGroupTemp= new HashSet<CityWithGroup>();
   private CityWithGroup cssSelect;
   private CityWithGroup cssSelectResult;
   
   private Lesson lessonEdit;

   private Teacher currentTeacher;
   private Discipline currentDiscipline;


   private boolean TeacherAttentionRender=false;
   private int countOfStudent=0;
   private int limitCountOfStudents=25;

    public void setTeacherDao(IBasicDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
    public Map getTypes() {
        return type.toMap();
    }
       public void  changeServiceTypes(){

        }

    public List<Teacher> autocomplete(Object suggest) {
        String pref = (String)suggest;
        ArrayList<Teacher> result = new ArrayList<Teacher>();
        if(suggest!=null && suggest.toString().length()>3){
            Iterator<Teacher> iterator = teacherDao.getAll().iterator();
            while (iterator.hasNext()) {
                Teacher elem = ((Teacher) iterator.next());
                if ((elem.getFio() != null && elem.getFio().toLowerCase().indexOf(pref.toLowerCase()) != -1) || "".equals(pref))
                {
                    Teacher t = new Teacher(elem.getId());
                    t.setName(elem.getName());
                    t.setSecondName(elem.getSecondName());
                    t.setSurname(elem.getSurname());
                    result.add(t);
                }
            }
        }
        return result;
    }
    
    public List<Discipline> autocompleteDisciplines(Object suggest) {
        String pref = (String)suggest;
        ArrayList<Discipline> result = new ArrayList<Discipline>();
        Iterator<Discipline> iterator = disciplines.iterator();
        while (iterator.hasNext()) {
            Discipline elem = ((Discipline) iterator.next());
            if ((elem.getName() != null && elem.getName().toLowerCase().indexOf(pref.toLowerCase()) != -1) || "".equals(pref))
            {
                Discipline d = new Discipline(elem.getId());
                d.setName(elem.getName());
                result.add(d);
            }
        }

        return result;
    }
      

    public void saveLesson() throws MalformedURLException, IOException{
      // System.out.println("save!!!");
        int number = 0;
//        if (lessonBean.getCurrentLessonId()!=null){            
//            lessonDao.deleteLesson(lessonBean.getLesson());
//        }
        Lesson l = new Lesson();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yy");
        l.setLessonDate(lessonBean.getCurrentTime());
        l.setLessonEndDate(lessonBean.getLesson().getLessonEndDate());
        l.setLessonType(type);
//        List<Group> cis;
//        cis= new ArrayList<CityWithGroup>(cssResult);
        l.setCitiesWithGroups(new HashSet(cssResult));
        l.setNumber(100);          
      //  l.setCity(currentCity);
       l.setRoom(Integer.parseInt(lessonBean.getCurrentRoom()));
        l.setUpdateDate(DateConverter.convert(System.currentTimeMillis()));
        l.setDiscipline(currentDiscipline);
        l.setTeacher(currentTeacher);
        l.setWinter(semesterGetter.getCurrentSemester()==1);
        l.setYear(semesterGetter.getCurrentYear());
        if (lessonBean.getLesson().getId()!=null){
          //    System.out.println("before delete");
      
                    Lesson l2=lessonDao.getById(lessonBean.getLesson().getId());
                    l2.setCitiesWithGroups(null);
                    lessonDao.deleteLesson(l2);
        }
        lessonDao.saveLesson(l);
        dayBean.ShowSelectDate();
    }

  public void deleteLesson() throws MalformedURLException, IOException{
     //   Lesson l = new Lesson();
      // l.setId(Long.parseLong(lessonBean.getCurrentLessonId()));
        Lesson l=lessonDao.getById(Long.parseLong(lessonBean.getCurrentLessonId()));
        l.setCitiesWithGroups(null);
        lessonDao.deleteLesson(l);
        dayBean.ShowSelectDate();
       System.out.println("l.id" +l.getId());
    }
    public Discipline getCurrentDiscipline() {
        return currentDiscipline;
    }


    public void setCurrentDiscipline(Discipline currentDiscipline) {
        if (currentDiscipline!=null){
            this.currentDiscipline = currentDiscipline;
            css=null;
            cssResult=null;
            cityGroupTemp=new HashSet<CityWithGroup>();
            this.TeacherAttentionRender=false;
            this.countOfStudent=0;
            cssNotReady = curriculumDao.getGroupsForTeacher(semesterGetter.getSemestersByInt(1), semesterGetter.getCurrentYear(),currentTeacher,currentDiscipline );
          //  cssNotReady.addAll(curriculumDao.getGroupsForTeacher(semesterGetter.getSemestersByInt(1), semesterGetter.getCurrentYear(),currentTeacher,currentDiscipline ));
            createCityGroup();
            css=new ArrayList<CityWithGroup>(cityGroupTemp);
            Collections.sort(css);
          
            if (lessonEdit != null){
                 cssResult=new ArrayList<CityWithGroup>(lessonEdit.getCitiesWithGroups());
                 this.css=ListUtils.RemoveExistCss(css, cssResult);
            }
            
            //  checkCssResult();
        }else{
             this.currentDiscipline=null;
             css=null;
             cssResult=null;
        }
    }

    public void createCityGroup(){
        for (Group gr:cssNotReady){
            for (Object st:gr.getPersons()){
                City city=new City();
                city.setId(((Student)st).getCity().getId());
                city.setName(((Student)st).getCity().getName());
                if (gr.getNumber()>0)
                    cityGroupTemp.add(new CityWithGroup(city, gr));
            }
        }



    }

    public void settItem(Teacher tItem) {
        System.out.println("werwe");
    }

    public Set<Discipline> getDisciplines() {

        return disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<CityWithGroup> getCss() {
        return css;
    }

    public void setCss(List<CityWithGroup> css) {
        this.css = css;
    }

 

    public List<CityWithGroup> getCssResult() {
        return cssResult;
    }

    public void setCssResult(List<CityWithGroup> obj) {
        System.out.println("set!!!");
        System.out.println(""+obj);
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public void setLessonBean(EditLessonBean lessonBean) {
        this.lessonBean = lessonBean;
    }

    public void setDayBean(EditDayBean dayBean) {
        this.dayBean = dayBean;
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        if (currentTeacher!=null){
            this.TeacherAttentionRender=false;
            this.countOfStudent=0;
            this.currentTeacher = currentTeacher;
            checkTeacherSameLesson();
            List<DeCurriculum>c = curriculumDao.getBySemesterYearAndParameters(semesterGetter.getSemestersByInt(1), semesterGetter.getCurrentYear(),currentTeacher);
//            c.addAll(curriculumDao.getBySemesterYearAndParameters(semesterGetter.getSemestersByInt(0), semesterGetter.getCurrentYear(),currentTeacher));

            disciplines = getDisciplines(c);
           // this.renderDisciplines=true;
        }else{
            this.TeacherAttentionRender=false;
            this.countOfStudent=0;
            this.currentTeacher=null;
            disciplines=new HashSet();
        }

    }
    public void checkTeacherSameLesson(){
        List <Lesson> list =dayBean.getAllLessons();
        for (Lesson c: list){
            if (c.getTeacher()!=null){
                if (c.getLessonDate().equals(lessonBean.getCurrentTime())&&(c.getTeacher().equals(this.currentTeacher))) {
                    if (c.getRoom()!=Integer.parseInt(lessonBean.getCurrentRoom())){
                        this.TeacherAttentionRender=true;
                        break;
                    }
                }
            }
        }
    }

    public boolean isTeacherAttentionRender() {
        return TeacherAttentionRender;
    }

    public void setTeacherAttentionRender(boolean TeacherAttentionRender) {
        this.TeacherAttentionRender = TeacherAttentionRender;

    }
    public void checkCssResult(){
        List <Lesson> list =dayBean.getAllLessons();
        for (Lesson c: list){
            if (c.getTeacher()!=null){
            if (c.getLessonDate().equals(lessonBean.getCurrentTime())) {
                this.css=ListUtils.MarkUsedCss(css, new ArrayList(c.getCitiesWithGroups()));
            }
            if (c.getLessonDate().equals(lessonBean.getCurrentTime())&&(c.getTeacher().equals(this.currentTeacher))&&(c.getDiscipline().equals(this.currentDiscipline))) {
                this.cssResult=null;
                this.cssResult=new ArrayList(c.getCitiesWithGroups());
                this.css=ListUtils.RemoveExistCss(css, cssResult);
                for(CityWithGroup c2:this.cssResult){
                    this.countOfStudent+=c2.getGroup().getNumber();
                }
                break;
            }}
          }
        
//      cssResult=new ArrayList<CityWithGroup>(lessonEdit.getCitiesWithGroups());
//        this.css=ListUtils.RemoveExistCss(css, cssResult);
      
    }

    public CityWithGroup getCssSelect() {
        return cssSelect;
    }

    public void setCssSelect(CityWithGroup cssSelect) {

        if (cssResult==null) cssResult=new ArrayList<CityWithGroup>();
        this.cssResult.add(cssSelect);
        this.css.remove(cssSelect);
        //System.out.println("!! " + cssSelect.getNumber());
        this.countOfStudent+=cssSelect.getGroup().getNumber();
    }

    public CityWithGroup getCssSelectResult() {
        return cssSelectResult;
    }

    public void setCssSelectResult(CityWithGroup cssSelectResult) {
        this.cssResult.remove(cssSelectResult);
        if (this.css==null) this.css=new ArrayList<CityWithGroup>();
        this.css.add(cssSelectResult);
        this.countOfStudent-=cssSelectResult.getGroup().getNumber();
    }

    public String getCountOfStudent() {
        if (this.countOfStudent<=this.limitCountOfStudents)
        return Integer.toString(countOfStudent);
        else
        return "<font color=red><b>"+Integer.toString(countOfStudent)+"</b></font>";
    }

    public void setCountOfStudent(int countOfStudent) {
        this.countOfStudent = countOfStudent;
    }

    public int getLimitCountOfStudents() {
        return limitCountOfStudents;
    }

    public void setLimitCountOfStudents(int limitCountOfStudents) {
        this.limitCountOfStudents = limitCountOfStudents;
    }

    private Set<Discipline>getDisciplines(List<DeCurriculum>dl){
        Set<Discipline> ds = new HashSet<Discipline>();
        for(DeCurriculum d:dl){
            ds.add(d.getDiscipline());
        }
        return ds;
    }

    public void setCurriculumDao(ICurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

    public Lesson getLessonEdit() {
        return lessonEdit;
    }

    public void setLessonEdit(Lesson lessonEdit) {
        this.lessonEdit = lessonEdit;
      
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }



}
