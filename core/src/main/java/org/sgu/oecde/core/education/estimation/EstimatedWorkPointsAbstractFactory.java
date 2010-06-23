package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;

/**
 * абстрактная фабрика по формированию балла/оценки на основании результата
 * @author ShihovMY
 */
abstract public class EstimatedWorkPointsAbstractFactory {

    /**
     * формирует балл/оценку на основании результата
     * @param result
     * @return
     */
    abstract public Object createEstimatedWorkValue(AbstractResult result);
}
