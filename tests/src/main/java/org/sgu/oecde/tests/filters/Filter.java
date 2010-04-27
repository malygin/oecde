package org.sgu.oecde.tests.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPoints;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.IRegularResult;
import org.sgu.oecde.tests.EstimateNames;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.tests.TestType;
import org.sgu.oecde.tests.TestsPointsFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public final class Filter implements InitializingBean{
    
    @Autowired
    private TestsPointsFactory pointsFactory;
    private List<Integer> points ;
    private List<Integer> rePoints ;
    private HashMap<IEstimate,EstimatedWorkPoints> estimatedWorkPoints = new HashMap<IEstimate, EstimatedWorkPoints>();
    private TestEntity test;
    private TestAttempt previousAttempt;
    private TestAttempt previousReAttempt;
    private int tempSum;

    public Filter() {
    }
    
    public void check(TestAttempt result, boolean last) {
        TestAttempt att = (TestAttempt) result;
        if(!att.<TestEntity>getWork().equals(test)){
            setPointIntoItem();
            points = new ArrayList();
            rePoints = new ArrayList();
        }
        if(att.getType().equals(TestAttemptType.reTest)){
            rePoints.add(att.getPoints());
            previousReAttempt = att;
        }else{
            points.add(att.getPoints());
            previousAttempt = att;
        }
        test = att.<TestEntity>getWork();
        if(last){
            setPointIntoItem();
        }
    }

    private void setPointIntoItem(){
        EstimatedWorkPoints point = null;
//        System.out.println(!CollectionUtils.isEmpty(points)&&CollectionUtils.isEmpty(rePoints));
        if(!CollectionUtils.isEmpty(points)&&CollectionUtils.isEmpty(rePoints)){
            point =  pointsFactory.createEstimatedWorkPoint(previousAttempt);
            point.setPoints(getPointsByEstiminationType(previousAttempt.<TestEntity>getWork().getEstimation(),points));
            System.out.println(previousAttempt.getCurriculum().getId()+"  "+estimatedWorkPoints.get(point.getName()).getPoints());
            tempSum+=point.getPoints();
            point.setPoints(estimatedWorkPoints.get(point.getName()).getPoints()+point.getPoints());
            estimatedWorkPoints.put(point.getName(), point);
        }
        if(!CollectionUtils.isEmpty(rePoints)&&!CollectionUtils.isEmpty(points)){
            point =  pointsFactory.createEstimatedWorkPoint(previousAttempt);
            point.setPoints(getPointsByEstiminationType(previousAttempt.<TestEntity>getWork().getEstimation(),points));
            point.setPoints(estimatedWorkPoints.get(point.getName()).getPoints()+point.getPoints());
            estimatedWorkPoints.put(point.getName(), point);
            point =  pointsFactory.createEstimatedWorkPoint(previousReAttempt);
            point.setPoints(getPointsByEstiminationType(previousReAttempt.<TestEntity>getWork().getEstimation(),rePoints));            
            tempSum+=point.getPoints();
            point.setPoints(estimatedWorkPoints.get(point.getName()).getPoints()+point.getPoints());
            estimatedWorkPoints.put(point.getName(), point);
        }
    }

    private int getPointsByEstiminationType(TestEstimationType type,List<Integer> points){
        int point = 0;
        switch(type){
            case middle:
                for(Integer ps:points){
                    point +=ps;
                }
                point  = point/points.size();
                break;
            case max:
            default:
                point = Collections.max(points);
                break;
        }
        return point;
    }

    public List<EstimatedWorkPoints> getEstimatedWorkPoints() {
        return new ArrayList<EstimatedWorkPoints>(estimatedWorkPoints.values());
    }

    public int getTempSum() {
        return tempSum;
    }
    
    public void clear(){
        System.out.println("sum   "+tempSum);
        for(EstimatedWorkPoints ewp:pointsFactory.createEstimatedWorkPoint()){
            estimatedWorkPoints.put(ewp.getName(), ewp);
        }
        tempSum = 0;        
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pointsFactory);
        Assert.notNull(estimatedWorkPoints);
        clear();
    }
}
