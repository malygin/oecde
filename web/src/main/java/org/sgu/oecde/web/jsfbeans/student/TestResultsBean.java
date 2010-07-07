package org.sgu.oecde.web.jsfbeans.student;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestAttemptService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testResultsBean")
@ViewScoped
public class TestResultsBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    Long curriculumId;

    boolean reExame;
    
    List<AdditionalSelfDependentWork>attempts;

    private static final long serialVersionUID = 98L;

    public List<AdditionalSelfDependentWork>getAttempts(){
        if(attempts==null){
            if(curriculumId==null||curriculumId==0)
                attempts = testAttemptService.getStudentAttempts(getCurriculums(),student,reExame);
            else{
                List<DeCurriculum>curriculums = new LinkedList<DeCurriculum>();
                curriculums.add(curriculumBuilder.getInstance(curriculumId));
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
}
