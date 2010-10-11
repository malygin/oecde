package org.sgu.oecde.web.jsfbeans.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.IBasicDao;
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
    ControlWorksBean controlWorksBean;

    @ManagedProperty(value="#{gradesBean}")
    PointsAndGradesBean gradesBean;

    @ManagedProperty(value="#{testResultsBean}")
    TestResultsBean testResultsBean;

    @ManagedProperty(value="#{studentSessionBean}")
    StudentSessionBean studentSessionBean;
    int semester;

    private static final long serialVersionUID = 171L;

    public StudentViewBean() {
        setType("student");
    }

    public int getCurrentSemester(){
        return studentSessionBean.getCurrentSemester();
    }

    public void reExameSwitch(AjaxBehaviorEvent event){
        Boolean np = (Boolean) event.getComponent().getAttributes().get("reExame");
        testResultsBean.setReExame(np);
    }

    public void setId(Long id) {
        if(id!=null){
            super.setId(id);
            controlWorksBean.setStudent((Student) user);
            gradesBean.setStudent((Student) user);
            testResultsBean.setStudent((Student) user);
            studentSessionBean.setStudent((Student) user);
        }
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
