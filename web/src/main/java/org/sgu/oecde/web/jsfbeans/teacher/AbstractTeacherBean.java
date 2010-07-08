package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@Secured(value="ROLE_TEACHER,ROLE_ADMIN")
abstract class AbstractTeacherBean implements Serializable{

    private DeCurriculum curriculum;
    protected boolean accessDenied = true;
    protected Teacher teacher;
    protected int semester;
    
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    @ManagedProperty(value="#{curriculumDao}")
    protected ICurriculumDao<DeCurriculum> curriculumDao;

    @ManagedProperty(value="#{curriculumBuilder}")
    protected DeCurriculumBuilder curriculumBuilder;

    @ManagedProperty(value="#{teacherDisciplineBean}")
    protected TeacherDisciplineBean teacherDisciplineBean;

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

    public DeCurriculum getCurriculum(){
        return curriculum;
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

    public void setCurriculumBuilder(DeCurriculumBuilder curriculumBuilder) {
        this.curriculumBuilder = curriculumBuilder;
    }

    public void setCurriculumId(Long curriculumId) {
        curriculum = null;
        DeCurriculum c = curriculumBuilder.getInstance(curriculumId);
        List<DeCurriculum>l = teacherDisciplineBean.getDisciplines();
        for(DeCurriculum d:l){
            if(d.equals(c)){
                curriculum = d;
            }
        }
        accessDenied = curriculum==null;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        Assert.notNull(teacher);
    }
}
