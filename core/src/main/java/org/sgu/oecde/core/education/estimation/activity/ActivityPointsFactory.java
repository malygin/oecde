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
            Activity ac = (Activity)result;
            values.put(ActivityEstimateNames.activity_points,ac.getPoints());
            values.put(ActivityEstimateNames.samAud_points,ac.getSamAudWorkpoints());
            values.put(ActivityEstimateNames.samOutAud_points,ac.getSamOutAudWorkpoints());
            values.put(ActivityEstimateNames.personChar_points,ac.getPersonalCharpoints());
            values.put(ActivityEstimateNames.publish_points,ac.getPublishpoints());
            values.put(ActivityEstimateNames.lec_points,ac.getLecpoints());
            values.put(ActivityEstimateNames.activity,ac);
        }
        return values;
    }
}
