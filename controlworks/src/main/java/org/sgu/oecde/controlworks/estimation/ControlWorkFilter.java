package org.sgu.oecde.controlworks.estimation;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@Service
@ResultType(type=ControlWork.class)
public class ControlWorkFilter implements IResultFilter{

    @Autowired
    private CwPointsFactory pointsFactory;
    
    private ControlWorkFilter() {
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
