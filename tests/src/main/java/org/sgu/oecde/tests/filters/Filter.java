package org.sgu.oecde.tests.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPoints;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.tests.TestsPointsFactory;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ResultType(type=TestAttempt.class)
public final class Filter implements IResultFilter{
    
    @Autowired
    private TestsPointsFactory pointsFactory;
    private List<Integer> points ;
    private List<Integer> rePoints ;
    private HashMap<IEstimate,EstimatedWorkPoints> estimatedWorkPoints;
    private TestEntity test;
    private TestAttempt previousAttempt;
    private TestAttempt previousReAttempt;
    private int sum;
    private boolean first;

    private Filter() {
    }

    @Override
    public List<EstimatedWorkPoints> getEstimatedWorkPoints() {
        setPointIntoItem();
        return new ArrayList<EstimatedWorkPoints>(estimatedWorkPoints.values());
    }

    @Override
    public int getSum() {
        return sum;
    }

    @Override
    public void clear(){
        for(EstimatedWorkPoints ewp:pointsFactory.createEstimatedWorkPoint()){
            estimatedWorkPoints.put(ewp.getName(), ewp);
        }
        sum = 0;
        first = true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pointsFactory);
        estimatedWorkPoints = new HashMap<IEstimate, EstimatedWorkPoints>();
        clear();
    }
    
    @Override
    public void check(AbstractSelfDependentWorkResult result) {
        TestAttempt att = (TestAttempt) result;
        if(!att.<TestEntity>getWork().equals(test)){
            if(!first)
                setPointIntoItem();
            points = new ArrayList();
            rePoints = new ArrayList();
            first = false;
        }
        if(att.getType().equals(TestAttemptType.reTest)){
            rePoints.add(att.getPoints());
            previousReAttempt = att;
        }else if (att.getType().equals(TestAttemptType.regular)){
            points.add(att.getPoints());
            previousAttempt = att;
        }
        test = att.<TestEntity>getWork();
    }

    private void setPointIntoItem(){
        if(!CollectionUtils.isEmpty(points)&&CollectionUtils.isEmpty(rePoints)){
            fillMap(previousAttempt,points,true);
        }
        if(!CollectionUtils.isEmpty(rePoints)&&!CollectionUtils.isEmpty(points)){
            fillMap(previousAttempt,points,false);
            fillMap(previousReAttempt,rePoints,true);
        }
    }

    private void fillMap(TestAttempt attempt, List points,boolean doSum){
        EstimatedWorkPoints point = null;
        Assert.notNull(attempt);
        point =  pointsFactory.createEstimatedWorkPoint(attempt);
        point.setPoints(pointsCounter.count(attempt.<TestEntity>getWork().getEstimation(),points));
        if(doSum)
            sum+=point.getPoints();
        point.setPoints(estimatedWorkPoints.get(point.getName()).getPoints()+point.getPoints());
        estimatedWorkPoints.put(point.getName(), point);
    }
}
