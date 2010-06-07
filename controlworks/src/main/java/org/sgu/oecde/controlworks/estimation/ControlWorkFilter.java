package org.sgu.oecde.controlworks.estimation;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ResultType(type=ControlWork.class)
public class ControlWorkFilter implements IResultFilter,InitializingBean{

    @Autowired
    private CwPointsFactory pointsFactory;
    
    private ControlWorkFilter() {
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pointsFactory);
    }

    public void check(AbstractResult result,Points points) {
        if(points!=null){
            points.addSum(result!=null?((ControlWork)result).getPoints():0);
            points.addWorkPoints(pointsFactory.createEstimatedWorkValue(result));
        }
    }

    @Override
    public void setPoints(Points point) {
    }
}
