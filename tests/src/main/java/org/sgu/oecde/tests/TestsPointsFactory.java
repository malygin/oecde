package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.List;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPoints;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPointsAbstractFactory;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class TestsPointsFactory extends EstimatedWorkPointsAbstractFactory{

    private TestsPointsFactory() {
    }

    @Override
    public EstimatedWorkPoints createEstimatedWorkPoint(AbstractSelfDependentWorkResult result) {
        EstimatedWorkPoints ewp = null;

        Assert.state(result != null,"result is null" );
        Assert.state((result instanceof TestAttempt),"result is not an insnance of TestAttempt");

        TestAttempt tResult = (TestAttempt) result;

        if(tResult.<TestEntity>getWork().getType().equals(TestType.concluding)&&tResult.getType().equals(TestAttemptType.regular)){
            ewp = new EstimatedWorkPoints(EstimateNames.CONCLUDING_TEST);
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.concluding)&&tResult.getType().equals(TestAttemptType.reTest)){
            ewp = new EstimatedWorkPoints(EstimateNames.CONCLUDING_RE_TEST);
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.regular)&&tResult.getType().equals(TestAttemptType.regular)){
            ewp = new EstimatedWorkPoints(EstimateNames.TEST);
        }else if(tResult.<TestEntity>getWork().getType().equals(TestType.regular)&&tResult.getType().equals(TestAttemptType.reTest)){
            ewp = new EstimatedWorkPoints(EstimateNames.RE_TEST);
        }
        ewp.setPoints(0);
        return ewp;
    }

    @Override
    public EstimatedWorkPoints createEstimatedWorkPoint(IEstimate name){
        return createEstimatedWorkPoint(name,0);
    }

    @Override
    public List<EstimatedWorkPoints> createEstimatedWorkPoint(){
        List<EstimatedWorkPoints> l = new ArrayList<EstimatedWorkPoints>(4);
        l.add(createEstimatedWorkPoint(EstimateNames.CONCLUDING_TEST));
        l.add(createEstimatedWorkPoint(EstimateNames.CONCLUDING_RE_TEST));
        l.add(createEstimatedWorkPoint(EstimateNames.TEST));
        l.add(createEstimatedWorkPoint(EstimateNames.RE_TEST));
        return l;
    }

    @Override
    public EstimatedWorkPoints createEstimatedWorkPoint(IEstimate name,int points){
        Assert.state(name != null,"name is null");
        EstimatedWorkPoints ewp = new EstimatedWorkPoints(name);
        ewp.setPoints(points);
        return ewp;
    }
}
