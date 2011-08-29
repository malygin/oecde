package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
abstract class AbstractTeacherBean implements Serializable{

    protected Teacher teacher;
    protected int semester;
    private boolean accessDenied = true;

    @ManagedProperty(value="#{curriculumDao}")
    protected ICurriculumDao<DeCurriculum> curriculumDao;
    
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    public AbstractTeacherBean() {
        AbstractUser user = SecurityContextHandler.getUser();
        if(user!=null&&user instanceof Teacher)
            teacher = (Teacher) user;
    }

    protected Integer[]semesters(){
        return semesterGetter.getSemestersByInt(semester);
    }

    protected int year(){
        return semesterGetter.getCalendarYear(semester);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
        semester=this.semesterGetter.getCurrentSemester();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }

    public boolean getAccessDenied() {
        return accessDenied;
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }
}
