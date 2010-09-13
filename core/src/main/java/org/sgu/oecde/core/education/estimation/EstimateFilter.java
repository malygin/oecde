package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.springframework.stereotype.Service;

/**
 * Спринг-бин. Фильтр результатов - итоговых оценок
 * @author ShihovMY
 * @see IResultFilter
 */
@Service
@ResultType(type=Estimate.class)
public class EstimateFilter implements IResultFilter{


    private static final long serialVersionUID = 138L;
    /**
     * получает результат и в зависимости от него помещает соответсвующую итоговую оценку
     * @param result - результат
     * @param points - баллы
     * @see org.sgu.oecde.core.education.work.PointToEstimate - парсер оценки в результате
     */
    public void check(AbstractResult result,Points points) {
        points.<Estimate>getWorkPoints().put(EstimateNames.estimate, (Estimate) result);
    }

    /**
     * не используется
     * @param point
     */
    @Override
    public void setPoints(Points point) {
    }
}
