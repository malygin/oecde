package org.sgu.oecde.controlworks.estimation;

import java.util.HashMap;
import java.util.Map;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPointsAbstractFactory;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.work.AbstractResult;

/**
 *
 * @author ShihovMY
 */
public class CwPointsFactory extends EstimatedWorkPointsAbstractFactory{

    private CwPointsFactory() {
    }

    @Override
    public Map<IEstimate,Object> createEstimatedWorkValue(AbstractResult result) {
        Map<IEstimate,Object> values = null;
        if(result instanceof ControlWork){
            values = new HashMap<IEstimate, Object>();
            ControlWork cw = (ControlWork)result;
            values.put(CwEstimateNames.control_work_points,cw.getPoints());
            values.put(CwEstimateNames.control_work_value,cw.getProgress());
        }
        return values;
    }
}
