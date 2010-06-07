package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;

/**
 *
 * @author ShihovMY
 */
abstract public class EstimatedWorkPointsAbstractFactory {
    abstract public Object createEstimatedWorkValue(AbstractResult result);
}
