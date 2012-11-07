package org.sgu.oecde.selfdependentwork.estimation;

import java.util.LinkedList;
import java.util.List;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.estimation.CwPointsFactory;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.selfdependentwork.SelfDependentWork;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ResultType(type=SelfDependentWork.class)
public class SelfDependentWorkFilter implements IResultFilter,InitializingBean{

    @Autowired
    private CwPointsFactory pointsFactory;
    private SelfDependentWork previous;
    private List<Integer>pointslist;
    private List<ControlWorkProgress>progresses;

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pointsFactory);
    }

    public void check(AbstractResult result,Points points) {
        SelfDependentWork work = (SelfDependentWork)result;
        if(!work.equals(previous)){
            setPoints(points);
            progresses = new LinkedList<ControlWorkProgress>();
            pointslist = new LinkedList<Integer>();
        }
        previous = work;
    }

    @Override
    public void setPoints(Points points) {
        if(points!=null){
            ControlWorkProgress result = null;
            for(ControlWorkProgress p:progresses){
                if(ControlWorkProgress.passed.equals(p)){
                    result = ControlWorkProgress.passed;
                }else{
                    if (ControlWorkProgress.failed.equals(p))
                        result = ControlWorkProgress.failed;
                    break;
                }
            }
            Integer p = 0;
            for(Integer i:pointslist){
                p+=i;
            }
            points.addNewWorkPoint(SdwEstimateNames.self_dependent_work_value, result);
            points.addSum(p);
            points.addNewWorkPoint(SdwEstimateNames.self_dependent_work_points,p);
        }
    }
}