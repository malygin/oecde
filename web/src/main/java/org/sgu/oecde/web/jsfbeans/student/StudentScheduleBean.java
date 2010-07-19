package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.shedule.LessonService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="sheduleBean")
@ViewScoped
public class StudentScheduleBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{lessonService}")
    LessonService lessonService;

    public List getSchedule(){
        return lessonService.getStudentLessons(student.getGroup(), getCurriculums());
    }
}
