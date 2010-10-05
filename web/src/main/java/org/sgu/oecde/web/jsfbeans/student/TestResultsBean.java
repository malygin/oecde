package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.TestConstantsServise;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testResultsBean")
@ViewScoped
public class TestResultsBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testConstantsServise}")
    private TestConstantsServise testConstantsServise;

    private Long curriculumId;

    boolean reExame;
    
    private List<AdditionalSelfDependentWork>attempts;

    private static final long serialVersionUID = 98L;

    public List<AdditionalSelfDependentWork>getAttempts(){
        if(attempts==null){
            if(curriculumId==null||curriculumId==0)
                attempts = testAttemptService.getStudentAttempts(getCurriculums(),student,reExame);
            else{
                attempts = testAttemptService.getStudentSingleCurriculumTestsWithAttempts(curriculumBuilder.getInstance(curriculumId),student);
            }
        }
        return attempts;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
        attempts = null;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public boolean isReExame() {
        return reExame;
    }

    public void setReExame(boolean reExame) {
        this.reExame = reExame;
        attempts = null;
    }

    public String getRegularAttemtpsCount() {
        return testConstantsServise.getRegularAttemtpsCount(getSemester());
    }

    public String getConcludingAttemtpsCount() {
        return testConstantsServise.getConcludingAttemtpsCount(getSemester());
    }

    public void setTestConstantsServise(TestConstantsServise testConstantsServise) {
        this.testConstantsServise = testConstantsServise;
    }
}
