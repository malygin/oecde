package org.sgu.oecde.controlworks.estimation;

import java.util.HashMap;
import java.util.Map;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPointsAbstractFactory;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.springframework.stereotype.Service;

/**
 * фабрика имён полей.
 * @author ShihovMY
 */
@Service
public class CwPointsFactory extends EstimatedWorkPointsAbstractFactory{

    private CwPointsFactory() {
    }

    private static final long serialVersionUID = 150L;

    /**
     *
     * @param result
     * @return имя и значение для каждого поля, полученные из результата
     */
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
