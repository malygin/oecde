package org.sgu.oecde.tests.estimation;

import java.util.LinkedList;
import java.util.List;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ResultType(type=TestAttempt.class)
public final class TestFilter implements IResultFilter,InitializingBean{
    
    @Autowired
    private TestsPointsFactory pointsFactory;
    private List<Integer> points ;
    private List<Integer> rePoints ;
    private TestEntity test;
    private TestAttempt previousAttempt;
    private TestAttempt previousReAttempt;

    private TestFilter() {
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pointsFactory);
    }

    @Override
    public void check(AbstractResult result,Points point) {
        TestAttempt att = (TestAttempt) result;
        if(!att.<TestEntity>getWork().equals(test)){
            setPoints(point);
            points = new LinkedList<Integer>();
            rePoints = new LinkedList<Integer>();
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

    public void setPoints(Points point){
        if(point == null)
            return;
        if(!CollectionUtils.isEmpty(points)&&CollectionUtils.isEmpty(rePoints)){
            fillMap(previousAttempt,points,true,point);
        }
        if(!CollectionUtils.isEmpty(rePoints)){
            if(!CollectionUtils.isEmpty(points))
                fillMap(previousAttempt,points,false,point);
            fillMap(previousReAttempt,rePoints,true,point);
        }
    }

    private void fillMap(TestAttempt attempt, List pointsList,boolean doSum, Points points){
        Assert.notNull(attempt);
        IEstimate name = pointsFactory.createEstimatedWorkValue(attempt);
        int p = pointsCounter.count(attempt.<TestEntity>getWork().getEstimation(),pointsList);
        if(doSum)
            points.addSum(p);
        points.addIntegerWorkPoints(name, p);
    }
}
