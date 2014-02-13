package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherScheduleBean")
@ViewScoped
public class TeacherScheduleBean extends AbstractTeacherBean{

    @ManagedProperty(value="#{lessonDao}")
    private ILessonDao lessonDao;

    private List<Lesson>lessons;

    private List<Lesson>allLessons;

    private int pageNumber = 1;

    private int maxResult = 30;
    
    private String endDate="2014.11.11";
      

    private static final long serialVersionUID = 151L;
    
    private String currentDate = null;

    public List<Lesson> getSchedule(){
        if(allLessons == null){
            allLessons = lessonDao.getLessonsForTeacher(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,teacher,maxResult,pageNumber,null,null);
     
        }
        return allLessons;
    }
    
     public List<Lesson> schedule(int maxResult, boolean byDate){
        if(lessons==null){
             lessons = lessonDao.getLessonsForTeacher(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,teacher,maxResult,pageNumber,byDate?semesterGetter.getCurrentDate():null,byDate?endDate:null);
        }
        return lessons;
    }
     
    public int getCount(){
        return getAllLessons().size();
    }
    
    private List<Lesson> getAllLessons(){        
        if(allLessons==null){
            Lesson ex = new Lesson();
            ex.setTeacher(teacher);
          
            ex.setWinter(semesterGetter.getCurrentSemester()==1);
            ex.setYear(semesterGetter.getCurrentYear());
            allLessons = lessonDao.getByExample(ex);
        }
        return allLessons;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public int getMaxResult() {
        return maxResult;
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