package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    private static final long serialVersionUID = 151L;

    public List<Lesson> getSchedule(){
        if(lessons == null){
            int endPoint = maxResult * (pageNumber-1)+maxResult;
            if(endPoint > getAllLessons().size())
                endPoint = getAllLessons().size();
            lessons = getAllLessons().subList(maxResult * (pageNumber-1), endPoint);
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
}