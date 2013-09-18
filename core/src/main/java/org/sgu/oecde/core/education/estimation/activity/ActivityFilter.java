/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education.estimation.activity;

import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.de.education.DeCurriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Nov 28, 2011
 */
@Service
@ResultType(type=Activity.class)
public class ActivityFilter implements IResultFilter{

    /**
     * фабрика формирования имён полей кр
     */
    @Autowired
    private ActivityPointsFactory pointsFactory;

    private static final long serialVersionUID = 149L;
    
    private ActivityFilter() {
    }

    /**
     * помещает в Points набранные баллы, статус кр и сумму
     * @param result кр
     * @param points баллы
     */
    @Override
    public void check(AbstractResult result,Points points) {
        if(points!=null&&result!=null&&result.getCurriculum()!=null){
            Activity a = ((Activity)result);            
            points.addSum(a!=null && a.getPoints()!=null?a.getPoints():0f);
            points.addSum(a!=null && a.getSamAudWorkpoints() !=null?a.getSamAudWorkpoints():0f);
            points.addSum(a!=null && a.getSamOutAudWorkpoints() !=null?a.getSamOutAudWorkpoints():0f);
            points.addSum(a!=null && a.getPersonalCharpoints() !=null?a.getPersonalCharpoints():0f);
            points.addSum(a!=null && a.getPublishpoints() !=null?a.getPublishpoints():0f);
            points.addSum(a!=null && a.getLecpoints() !=null?a.getLecpoints():0f);

            points.addWorkPoints(pointsFactory.createEstimatedWorkValue(result));
//            points.addWorkPoints(pointsFactory.createEstimatedWorkValue(result));
        }
    }

    @Override
    public void setPoints(Points point) {
    }
}
