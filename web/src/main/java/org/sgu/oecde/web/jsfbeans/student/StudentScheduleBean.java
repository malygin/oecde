package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class StudentScheduleBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{lessonDao}")
    private ILessonDao lessonDao;

    private List<Lesson>lessons;

    private static final long serialVersionUID = 151L;

    public List<Lesson> getSchedule(){
        if(lessons==null){
            Lesson ex = new Lesson();
            ex.setWinter(semesterGetter.getCurrentSemester()==SemesterGetter.WINTER_SEMESTER);
            ex.setYear(semesterGetter.getCurrentYear());
            ex.setGroups(ListUtil.oneItemList(student.getGroup()));
            lessons = lessonDao.getByFullExample(ex);
        }
        return lessons;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }
}