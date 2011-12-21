package org.sgu.oecde.web.jsfbeans.student;

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
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;
import org.sgu.oecde.core.util.NumberUtil;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class StudentScheduleBean extends AbstractStudentBean{
    @ManagedProperty(value="#{lessonDao}")
    private ILessonDao lessonDao;
    

    private List<Lesson>lessons;
    private List<Lesson>lessonsAll;

    private int pageNumber = 1;

    private String beginDate;
    
    private String endDate;

    private Long count;

    private String currentDate = null;
    
    private static final long serialVersionUID = 151L;
    
    private String folderContent;

    public List<Lesson> schedule(int maxResult, boolean byDate){
        if(lessons==null){
             lessons = lessonDao.getLessonsForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,student.<Group>getGroup(),student.getCity(),maxResult,pageNumber,byDate?semesterGetter.getCurrentDate():null,byDate?endDate:null);
        }
        return lessons;
    }
    public List<Lesson> scheduleAll(int maxResult, boolean byDate){
        if(lessonsAll==null){
             lessonsAll = lessonDao.getLessonsForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,student.<Group>getGroup(),student.getCity(),maxResult,pageNumber,byDate?semesterGetter.getCurrentDate():null,byDate?endDate:null);
        }
        return lessonsAll;
    }

    public Long count(boolean byDate){
        if(count == null){
            count = lessonDao.getLessonsCountForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,student.<Group>getGroup(),student.getCity(),byDate?beginDate:null,byDate?endDate:null);
        }
        return count;
    }
   
     private void getContent() throws MalformedURLException, IOException{
        
            URL PATH = new URL("http://oec-nginx.main.sgu.ru/get_files/dir_read.php");
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
//    public List<Long> getIdsOfLessons(){
//        List<Long> result = new ArrayList();
//        for(Lesson item : lessons){
//            result.add(item.getId());
//        }
//        return result;
//    }
//      public List<Long> getIdsOfLessonsAll(){
//        List<Long> result = new ArrayList();
//        for(Lesson item : lessonsAll){
//            result.add(item.getId());
//        }
//        return result;
//    }
    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

  
    @PostConstruct
    public void postConstract(){
        Assert.notNull(semesterGetter);
        Calendar cal = Calendar.getInstance();
        
        beginDate = semesterGetter.getCurrentYear()+"."+NumberUtil.NumberToDateFormat(cal.get(Calendar.MONTH) +1)+".01";
        endDate = semesterGetter.getCurrentYear()+1+"."+NumberUtil.NumberToDateFormat(cal.get(Calendar.MONTH) +3)+".01";
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
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


    }
