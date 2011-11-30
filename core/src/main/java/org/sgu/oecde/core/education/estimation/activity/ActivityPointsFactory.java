/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education.estimation.activity;

import java.util.HashMap;
import java.util.Map;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPointsAbstractFactory;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Nov 28, 2011
 */
@Service
public class ActivityPointsFactory extends EstimatedWorkPointsAbstractFactory{

    private ActivityPointsFactory() {
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
        if(result instanceof Activity){
            values = new HashMap<IEstimate, Object>();
            Activity cw = (Activity)result;
            values.put(ActivityEstimateNames.activity_points,cw.getPoints());
             values.put(ActivityEstimateNames.activity,cw);
        }
        return values;
    }
}
