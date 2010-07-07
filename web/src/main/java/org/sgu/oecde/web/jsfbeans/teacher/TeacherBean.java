package org.sgu.oecde.web.jsfbeans.teacher;

import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.springframework.security.annotation.Secured;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@Secured(value="ROLE_TEACHER,ROLE_ADMIN")
abstract class TeacherBean {

    protected Teacher teacher;
    protected int semester;
    
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    @ManagedProperty(value="#{curriculumDao}")
    protected ICurriculumDao<DeCurriculum> curriculumDao;

    private static final long serialVersionUID = 108L;

    public TeacherBean() {
        AbstractUser user = SecurityContextHandler.getUser();
        Assert.isInstanceOf(Teacher.class, user);
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

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }
}
