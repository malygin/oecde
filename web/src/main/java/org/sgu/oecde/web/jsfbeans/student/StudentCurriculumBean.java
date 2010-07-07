package org.sgu.oecde.web.jsfbeans.student;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Student;
import org.springframework.security.annotation.Secured;
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
            curriculums = curriculumDao.getByExample(curriculumBuilder.getInstanceByCurrentDate(student, semester));
        return curriculums;
    }

    public List<DeCurriculum> getCurriculumsByYear()  {
        if(curriculums==null)
            curriculums = curriculumDao.getByExample(curriculumBuilder.getInstance(semester, student));
        return curriculums;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
        curriculums = null;
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
