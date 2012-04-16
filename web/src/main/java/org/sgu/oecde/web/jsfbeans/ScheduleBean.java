/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.NumberUtil;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;
import org.springframework.util.Assert;

/**
 *
 * @author malygin
 */
@ManagedBean
@ViewScoped
public class ScheduleBean {
    @ManagedProperty(value="#{lessonDao}")
    private ILessonDao lessonDao;
    
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;
    
    private AbstractUser currentUser;
    

    private List<Lesson>lessons;
    private List<Lesson>lessonsAll;

    private int pageNumber = 1;
    private int lessonsOnPage = 40;
    private boolean byDate=true;
    
    private String beginDate;    
    private String endDate;

    private Long count;
    private String currentDate = null;
    
    private static final long serialVersionUID = 151L;
    
    private String folderContent;

   
    
    public ScheduleBean(){
        currentUser = SecurityContextHandler.getUser(); 
      
   
    }
    @PostConstruct
    public void postConstract(){
        Assert.notNull(semesterGetter);
        Calendar cal = Calendar.getInstance();
        
        beginDate = semesterGetter.getCurrentYear()+"."+NumberUtil.NumberToDateFormat(cal.get(Calendar.MONTH) +1)+".01";
        endDate = semesterGetter.getCurrentYear()+1+"."+NumberUtil.NumberToDateFormat(cal.get(Calendar.MONTH) +3)+".01";
    }
    
    public List<Lesson> getScheduleForStudents(){
        if(lessons==null){             
             lessons = lessonDao.getLessonsForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Student)currentUser).<Group>getGroup(),((Student)currentUser).getCity(),lessonsOnPage,pageNumber,byDate?semesterGetter.getCurrentDate():null,byDate?endDate:null);
        }
        return lessons;
    }
    public List<Lesson> getScheduleAllForStudents(){
        if(lessonsAll==null){
             lessonsAll = lessonDao.getLessonsForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Student)currentUser).<Group>getGroup(),((Student)currentUser).getCity(),lessonsOnPage,pageNumber,null,null);
        }
        return lessonsAll;
    }    

    public Long getCountForStudents(){
        if(count == null){
            count = lessonDao.getLessonsCountForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Student)currentUser).<Group>getGroup(),((Student)currentUser).getCity(),byDate?beginDate:null,byDate?endDate:null);
        }
        return count;
    }
    //---------
     public List<Lesson> scheduleForTeachers(int maxResult, boolean byDate){
        if(lessonsAll == null){
            lessonsAll = lessonDao.getLessonsForTeacher(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Teacher)currentUser),maxResult,pageNumber,null,null);
        }
        return lessonsAll;
    }
    
     public List<Lesson> scheduleAllForTeachers(int maxResult, boolean byDate){
        if(lessons==null){
             lessons = lessonDao.getLessonsForTeacher(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Teacher)currentUser),maxResult,pageNumber,byDate?semesterGetter.getCurrentDate():null,byDate?endDate:null);
        }
        return lessons;
    }
     
    public Long getCountForTeacher(boolean byDate){
         if(count == null){
            count = lessonDao.getLessonsCountForTeacher(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,((Teacher)currentUser),byDate?beginDate:null,byDate?endDate:null);
        }
        return count;
    }
    
    private List<Lesson> scheduleAllForAdmins(){        
//        if(allLessons==null){
//            Lesson ex = new Lesson();
//            ex.setTeacher(teacher);
//          
//            ex.setWinter(semesterGetter.getCurrentSemester()==1);
//            ex.setYear(semesterGetter.getCurrentYear());
//            allLessons = lessonDao.getByExample(ex);
//        }
//        return allLessons;
       if(lessonsAll==null){
             lessonsAll = lessonDao.getAll();
       }
        return lessonsAll;
    }

   
     private void getContent() throws MalformedURLException, IOException{
        FacesContext ctx = FacesContext.getCurrentInstance();    
        URL PATH = new URL(ctx.getExternalContext().getInitParameter("scheduleRecordScriptUrl"));
        URLConnection connection = PATH.openConnection();
        connection.setDoInput(true);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line ="";
        while ((line = reader.readLine()) != null) {
            folderContent += line;
        }
    }   
     
     public String getFolderContent() throws MalformedURLException, IOException{
         if(folderContent==null) getContent();
         return folderContent;
     }
     
     public boolean fileExist(String id) throws MalformedURLException, IOException{
        if(folderContent==null) getContent();
        boolean exists=folderContent.contains(";"+id+"roomVideoStream");
        return exists;
    }
     public String getFileName(String id) throws MalformedURLException, IOException{
         if(folderContent==null) getContent();
         int startIndex = folderContent.indexOf(";"+id+"roomVideoStream")+1;
         int endIndex = folderContent.indexOf(";",startIndex+1);
         String name = folderContent.substring(startIndex, endIndex);
         return name;
     }

  
    
    public boolean IsCurrentDate(String lessonDate){
        if((currentDate!=null)&&(currentDate.equals(lessonDate))) currentDate=null;
        if(currentDate==null){
            currentDate =lessonDate;            
            return true;
        } else if(currentDate.substring(0, 11).equals(lessonDate.substring(0, 11))){
             return false;                    
             } else{
                currentDate =lessonDate;            
                return true;
             }   
   }
    
    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }
    

}
