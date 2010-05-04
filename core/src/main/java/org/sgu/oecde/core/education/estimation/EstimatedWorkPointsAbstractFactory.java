/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.estimation;

import java.util.List;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;

/**
 *
 * @author ShihovMY
 */
abstract public class EstimatedWorkPointsAbstractFactory {
    abstract public EstimatedWorkPoints createEstimatedWorkPoint(AbstractSelfDependentWorkResult result);
    abstract public List<EstimatedWorkPoints> createEstimatedWorkPoint();
    abstract public EstimatedWorkPoints createEstimatedWorkPoint(IEstimate name);
    abstract public EstimatedWorkPoints createEstimatedWorkPoint(IEstimate name,int points);
}
