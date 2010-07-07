package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.jsfbeans.student.TestResultsBean;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentResults")
@ViewScoped
public class StudentResults extends AbstractTeacherBean{
    
    private Long id;

    private Student student;

    private Map<DeCurriculum,ControlWork>works;

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testResultsBean}")
    TestResultsBean testResultsBean;

    @ManagedProperty(value="#{controlWorkService}")
    ControlWorkService controlWorkService;

    private static final long serialVersionUID = 109L;

    public List<AdditionalSelfDependentWork> getResults() {
        return testResultsBean.getAttempts();
    }

    public Map<DeCurriculum,ControlWork> getControlWorks(){
        if(works==null){
            List<DeCurriculum> c =  controlWorkService.getCurriculumsWithControlWorks(getCurriculum());
            if(!CollectionUtils.isEmpty(c)&&c.size()==1)
                works = controlWorkService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
        }
        return works;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setStudentId(Long studentId) {
        testResultsBean.setStudent(new Student(studentId));
        works = null;
    }

    public Student getStudent() {
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        testResultsBean.setCurriculumId(id);
        works = null;
    }

    public void setControlWorkService(ControlWorkService controlWorkService) {
        this.controlWorkService = controlWorkService;
    }

    public void setTestResultsBean(TestResultsBean testResultsBean) {
        this.testResultsBean = testResultsBean;
    }
}
