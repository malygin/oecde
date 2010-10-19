package org.sgu.oecde.web.jsfbeans.supervisor;

import java.io.Serializable;
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
@ManagedBean(name="supervisorScheduleBean")
@ViewScoped
public class SupervisorScheduleBean implements Serializable{

    @ManagedProperty(value="#{lessonDao}")
    ILessonDao lessonDao;

    @ManagedProperty(value="#{supervisorSessionBean}")
    SupervisorSessionBean supervisorSessionBean;

    @ManagedProperty(value="#{semesterGetter}")
    private SemesterGetter semesterGetter;

    public List<Lesson>lessons;

    public List<Lesson> getLessons() {
        if(lessons == null){
            lessons = lessonDao.getByGroups(supervisorSessionBean.getGroups(),semesterGetter.getCurrentSemester() == SemesterGetter.WINTER_SEMESTER,semesterGetter.getCurrentYear());
        }
        return lessons;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public void setSupervisorSessionBean(SupervisorSessionBean supervisorSessionBean) {
        this.supervisorSessionBean = supervisorSessionBean;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }
}
