package org.sgu.oecde.web.jsfbeans.student;

import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestCalendarConstants;
import org.sgu.oecde.tests.TestType;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testResultsBean")
@ViewScoped
public class TestResultsBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testsDatesGetter}")
    private StringConstantsGetter testsDatesGetter;

    Long curriculumId;

    boolean reExame;
    
    private String winterConcludingTestReExameAttemtpsCount;
    private String summerRegularTestReExameAttemtpsCount;
    private String summerConcludingTestReExameAttemtpsCount;
    private String winterRegularTestReExameAttemtpsCount;
    
    List<AdditionalSelfDependentWork>attempts;

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

    public void setTestsDatesGetter(StringConstantsGetter testsDatesGetter) {
        this.testsDatesGetter = testsDatesGetter;
    }

    @PostConstruct
    public void postConstract(){
        summerConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerConcludingTestReExameAttemtpsCount).toString();
        summerRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerRegularTestReExameAttemtpsCount).toString();
        winterConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterConcludingTestReExameAttemtpsCount).toString();
        winterRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterRegularTestReExameAttemtpsCount).toString();
    }

    public String getRegularAttemtpsCount() {
        return getSemester()==1?summerRegularTestReExameAttemtpsCount:winterRegularTestReExameAttemtpsCount;
    }

    public String getConcludingAttemtpsCount() {
        return getSemester()==1?summerConcludingTestReExameAttemtpsCount:winterConcludingTestReExameAttemtpsCount;
    }

    public TestType getConcludingType(){
        return TestType.concluding;
    }
}
