package org.sgu.oecde.web.jsfbeans.student;

import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.IBeanWithSemester;

/**
 *
 * @author ShihovMY
 */
abstract class AbstractStudentBean implements Serializable,IBeanWithSemester{

    protected int semester;

    protected Student student;

    @ManagedProperty(value="#{curriculumBuilder}")
    protected DeCurriculumBuilder curriculumBuilder;

    @ManagedProperty(value="#{curriculumDao}")
    protected ICurriculumDao<DeCurriculum> curriculumDao;

    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    public AbstractStudentBean(){
        AbstractUser user = SecurityContextHandler.getUser();
        if(user!=null&&user instanceof Student)
            student = (Student) user;
    }

    public int getSemester() {
        return semester;
    }

    public int getCurrentSemester(){
        return semesterGetter.getCurrentSemester();
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

    public Student getStudent() {
        return student;
    }
}
