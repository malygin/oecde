package org.sgu.oecde.web.jsfbeans.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Student;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentCurriculumBean")
@ViewScoped
@Secured(value="ROLE_STUDENT,ROLE_ADMIN")
public class StudentCurriculumBean implements Serializable{

    protected int semester;

    protected Student student;

    private List<DeCurriculum>curriculums;

    private Map<DeCurriculum,Teacher>curriculumAndTeacher;

    @ManagedProperty(value="#{curriculumBuilder}")
    protected DeCurriculumBuilder curriculumBuilder;

    @ManagedProperty(value="#{curriculumDao}")
    protected ICurriculumDao<DeCurriculum> curriculumDao;

    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;
    
    private static final long serialVersionUID = 104L;

    public StudentCurriculumBean(){
        AbstractUser user = SecurityContextHandler.getUser();
        if(user!=null&&user instanceof Student)
            student = (Student) user;
    }

    public List<DeCurriculum> getCurriculums() {
        if(curriculums==null)
            curriculums = new ArrayList(curriculumDao.getTeachersByGroup(semesterGetter.getSemesterByStudentYear(student, semester).intValue(), semesterGetter.getCalendarYear(semester), student.getGroup()).keySet());
        return curriculums;
    }

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher() {
        if(curriculumAndTeacher == null){
            curriculumAndTeacher = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semesterGetter.getSemesterByStudentYear(student, semester).intValue(), semesterGetter.getCalendarYear(semester), student.getGroup());
        }
        return curriculumAndTeacher;
    }

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacherByYear() {
        if(curriculumAndTeacher == null){
            curriculumAndTeacher = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semester, semesterGetter.getCalendarYear(student, semester).intValue(), student.getGroup());
        }
        return curriculumAndTeacher;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCurriculumBuilder(DeCurriculumBuilder curriculumBuilder) {
        this.curriculumBuilder = curriculumBuilder;
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        Assert.notNull(student);
    }
}
