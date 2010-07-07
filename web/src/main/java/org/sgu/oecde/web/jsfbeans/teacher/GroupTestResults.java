package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.tests.TestAttemptService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupTestResults")
@ViewScoped
public class GroupTestResults extends AbstractStudentsListBean{

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    private List<AdditionalSelfDependentWork>tests;

    private static final long serialVersionUID = 112L;

    public List<AdditionalSelfDependentWork>  getGroupTestResults() {
        if(tests==null)
            tests = testAttemptService.getCurriculumAttempts(getCurriculum(),getStudentsList());
        return tests;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

}
