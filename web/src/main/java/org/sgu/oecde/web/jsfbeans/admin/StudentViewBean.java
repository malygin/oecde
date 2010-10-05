package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.jsfbeans.student.ControlWorksBean;
import org.sgu.oecde.web.jsfbeans.student.PointsAndGradesBean;
import org.sgu.oecde.web.jsfbeans.student.StudentSessionBean;
import org.sgu.oecde.web.jsfbeans.student.TestResultsBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentViewBean")
@ViewScoped
public class StudentViewBean implements Serializable{

    @ManagedProperty(value="#{studentDao}")
    IBasicDao<Student>studentDao;

    @ManagedProperty(value="#{controlWorksBean}")
    ControlWorksBean controlWorksBean;

    @ManagedProperty(value="#{gradesBean}")
    PointsAndGradesBean gradesBean;

    @ManagedProperty(value="#{testResultsBean}")
    TestResultsBean testResultsBean;

    @ManagedProperty(value="#{studentSessionBean}")
    StudentSessionBean studentSessionBean;

    Student student;
    int semester;
    Long id;

    private static final long serialVersionUID = 171L;

    public int getCurrentSemester(){
        return studentSessionBean.getCurrentSemester();
    }

    public void reExameSwitch(AjaxBehaviorEvent event){
        Boolean np = (Boolean) event.getComponent().getAttributes().get("reExame");
        testResultsBean.setReExame(np);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id!=null){
            student = studentDao.getById(id);
            controlWorksBean.setStudent(student);
            gradesBean.setStudent(student);
            testResultsBean.setStudent(student);
            studentSessionBean.setStudent(student);
        }
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        controlWorksBean.setSemester(semester);
        gradesBean.setSemester(semester);
        testResultsBean.setSemester(semester);
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setControlWorksBean(ControlWorksBean controlWorksBean) {
        this.controlWorksBean = controlWorksBean;
    }

    public void setGradesBean(PointsAndGradesBean gradesBean) {
        this.gradesBean = gradesBean;
    }

    public void setStudentDao(IBasicDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    public void setTestResultsBean(TestResultsBean testResultsBean) {
        this.testResultsBean = testResultsBean;
    }

    public ControlWorksBean getControlWorksBean() {
        return controlWorksBean;
    }

    public PointsAndGradesBean getGradesBean() {
        return gradesBean;
    }

    public TestResultsBean getTestResultsBean() {
        return testResultsBean;
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }
}
