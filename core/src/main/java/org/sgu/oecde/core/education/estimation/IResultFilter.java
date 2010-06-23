package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;

/**
 * интерфейс для фильтров результатов
 * @author ShihovMY
 */
public interface IResultFilter {

    /**
     * на основе результата помещает в баллы соответсвующие имя поля с баллами/оценкой и само значение
     * @param result
     * @param points
     */
    public void check(AbstractResult result,Points points);

    /**
     * помещает сформированные ранее баллы/оценки и имя поля, соответсвующего им
     * @param points
     */
    public void setPoints(Points points);
}
