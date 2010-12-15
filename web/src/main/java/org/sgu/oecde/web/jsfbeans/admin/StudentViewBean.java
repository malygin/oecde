package org.sgu.oecde.web.jsfbeans.admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.jsfbeans.UserViewBean;
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
public class StudentViewBean extends UserViewBean{

    @ManagedProperty(value="#{controlWorksBean}")
    private ControlWorksBean controlWorksBean;

    @ManagedProperty(value="#{gradesBean}")
    private PointsAndGradesBean gradesBean;

    @ManagedProperty(value="#{testResultsBean}")
    private TestResultsBean testResultsBean;

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;
    private  int semester;

    private static final long serialVersionUID = 171L;

    public StudentViewBean() {
        setType("STUDENT");
    }

    @Override
    public AbstractUser getUser() {
        if(user==null){
            user = super.getUser();
            controlWorksBean.setStudent((Student) user);
            gradesBean.setStudent((Student) user);
            testResultsBean.setStudent((Student) user);
            studentSessionBean.setStudent((Student) user);
        }
        return user;
    }

    public int getCurrentSemester(){
        return studentSessionBean.getCurrentSemester();
    }

    public void reExameSwitch(AjaxBehaviorEvent event){
        Boolean np = (Boolean) event.getComponent().getAttributes().get("reExame");
        testResultsBean.setReExame(np);
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

    public void setControlWorksBean(ControlWorksBean controlWorksBean) {
        this.controlWorksBean = controlWorksBean;
    }

    public void setGradesBean(PointsAndGradesBean gradesBean) {
        this.gradesBean = gradesBean;
    }

    public void setTestResultsBean(TestResultsBean testResultsBean) {
        this.testResultsBean = testResultsBean;
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }
}