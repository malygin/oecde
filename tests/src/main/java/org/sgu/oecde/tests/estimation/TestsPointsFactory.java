package org.sgu.oecde.tests.estimation;

import org.sgu.oecde.core.education.estimation.EstimatedWorkPointsAbstractFactory;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestType;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class TestsPointsFactory extends EstimatedWorkPointsAbstractFactory{

    private TestsPointsFactory() {
    }

    public IEstimate createEstimatedWorkValue(AbstractResult result) {
        Assert.state(result != null,"result is null" );
        Assert.state((result instanceof TestAttempt),"result is not an insnance of TestAttempt");

        TestAttempt tResult = (TestAttempt) result;

        if(tResult.<TestEntity>getWork().getType().equals(TestType.concluding)&&tResult.getType().equals(TestAttemptType.regular)){
            return TestEstimateNames.CONCLUDING_TEST;
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.concluding)&&tResult.getType().equals(TestAttemptType.reTest)){
            return TestEstimateNames.CONCLUDING_RE_TEST;
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.regular)&&tResult.getType().equals(TestAttemptType.regular)){
            return TestEstimateNames.TEST;
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.regular)&&tResult.getType().equals(TestAttemptType.reTest)){
            return TestEstimateNames.RE_TEST;
        }
        return null;
    }
}
