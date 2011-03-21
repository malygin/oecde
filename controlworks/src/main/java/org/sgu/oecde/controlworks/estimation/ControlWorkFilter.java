package org.sgu.oecde.controlworks.estimation;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * фильтр по обработке контрольных работ
 * @author ShihovMY
 */
@Service
@ResultType(type=ControlWork.class)
public class ControlWorkFilter implements IResultFilter{

    /**
     * фабрика формирования имён полей кр
     */
    @Autowired
    private CwPointsFactory pointsFactory;

    private static final long serialVersionUID = 149L;
    
    private ControlWorkFilter() {
    }

    /**
     * помещает в Points набранные баллы, статус кр и сумму
     * @param result кр
     * @param points баллы
     */
    public void check(AbstractResult result,Points points) {
        if(points!=null&&result!=null&&result.getCurriculum()!=null){
            ControlWork cw = ((ControlWork)result);
            points.addSum(cw!=null && cw.getPoints()!=null?cw.getPoints():0);
            points.addWorkPoints(pointsFactory.createEstimatedWorkValue(result));
        }
    }

    @Override
    public void setPoints(Points point) {
    }
}
