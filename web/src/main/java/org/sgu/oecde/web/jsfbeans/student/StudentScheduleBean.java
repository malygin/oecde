package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;
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

    private int pageNumber = 1;

    private String beginDate;
    
    private String endDate;

    private Long count;

    private String currentDate = null;
    
    private static final long serialVersionUID = 151L;

    public List<Lesson> schedule(int maxResult, boolean byDate){
        if(lessons==null){
            lessons = lessonDao.getLessonsFroStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,student.<Group>getGroup(),student.getCity(),maxResult,pageNumber,byDate?beginDate:null,byDate?endDate:null);
        }
        return lessons;
    }

    public Long count(boolean byDate){
        if(count == null){
            count = lessonDao.getLessonsCountForStudent(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER,student.<Group>getGroup(),student.getCity(),byDate?beginDate:null,byDate?endDate:null);
        }
        return count;
    }

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
        beginDate = semesterGetter.getCurrentYear()+".09.01";
        endDate = semesterGetter.getCurrentYear()+1+".08.01";
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
