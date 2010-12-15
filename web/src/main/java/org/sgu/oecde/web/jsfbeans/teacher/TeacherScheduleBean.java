package org.sgu.oecde.web.jsfbeans.teacher;

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

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    private static final long serialVersionUID = 151L;

    public List<Lesson> getSchedule(){
        if(lessons==null){
            Lesson ex = new Lesson();
            ex.setTeacher(teacher);
            ex.setWinter(semesterGetter.getCurrentSemester()==1);
            ex.setYear(semesterGetter.getCurrentYear());
            lessons = lessonDao.getByExample(ex);
        }
        return lessons;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }
}
