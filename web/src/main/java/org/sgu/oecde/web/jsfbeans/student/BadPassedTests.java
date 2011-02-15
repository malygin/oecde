package org.sgu.oecde.web.jsfbeans.student;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestType;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="badPassedTestsBean")
@ViewScoped
public class BadPassedTests extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;
    
    private List<AdditionalSelfDependentWork>tests;

    private List<AdditionalSelfDependentWork>concludingTests;

    private static final long serialVersionUID = 101L;

    public List<AdditionalSelfDependentWork> badPassedTests(boolean concluding){
        if(tests==null){
            tests = testAttemptService.getStudentTestsWithAttempts(getCurriculums(), student);
            concludingTests = new LinkedList();
            Iterator<AdditionalSelfDependentWork> i = tests.iterator();
            while(i.hasNext()){
                AdditionalSelfDependentWork passedTest = i.next();
                if(passedTest==null||passedTest.getPointsForWork()>30||TestType.concluding.equals(passedTest.<TestEntity>getWork().getType()))
                    i.remove();
                if(passedTest.getPointsForWork()<30&&TestType.concluding.equals(passedTest.<TestEntity>getWork().getType()))
                   concludingTests.add(passedTest);
            }
        }
        return concluding?concludingTests:tests;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

}
