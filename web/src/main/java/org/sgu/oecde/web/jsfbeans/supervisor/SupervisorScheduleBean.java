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
    private ILessonDao lessonDao;

    @ManagedProperty(value="#{supervisorSessionBean}")
    private SupervisorSessionBean supervisorSessionBean;

    @ManagedProperty(value="#{semesterGetter}")
    private SemesterGetter semesterGetter;

    public List<Lesson>lessons;

    private int pageNumber = 1;

    private int maxResult = 1000;

    private String beginDate;

    private String endDate;

    private Long count;

    public List<Lesson> getLessons() {
        if(lessons == null){
            lessons = lessonDao.getLessonsByCity(supervisorSessionBean.getSupervisor().getCity(),semesterGetter.getCurrentSemester() == SemesterGetter.WINTER_SEMESTER,semesterGetter.getCurrentYear(),maxResult,pageNumber,beginDate,endDate);
        }
        return lessons;
    }

    public Long getCount() {
        if(count == null){
              lessons = lessonDao.getLessonsByCity(supervisorSessionBean.getSupervisor().getCity(),semesterGetter.getCurrentSemester() == SemesterGetter.WINTER_SEMESTER,semesterGetter.getCurrentYear(),maxResult,pageNumber,beginDate,endDate);
        }
        return count;
    }

    public int getMaxResult() {
        return maxResult;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
