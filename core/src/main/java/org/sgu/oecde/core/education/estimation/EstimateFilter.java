package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@Service
@ResultType(type=Estimate.class)
public class EstimateFilter implements IResultFilter{

    public void check(AbstractResult result,Points points) {
        points.<PointToEstimate>getWorkPoints().put(EstimateNames.estimate, result!=null?((Estimate)result).getGradeCode():PointToEstimate.notEstimated);
    }

    @Override
    public void setPoints(Points point) {
    }

}
