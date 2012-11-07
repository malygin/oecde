package org.sgu.oecde.selfdependentwork.estimation;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 *
 * @author ShihovMY
 */
public enum SdwEstimateNames implements IEstimate{
    self_dependent_work_points,
    self_dependent_work_value;
    private static final long serialVersionUID = 95L;

    @Override
    public String toString() {
        switch (this) {
            case self_dependent_work_points:
                return "кр баллы";
            case self_dependent_work_value:
                return "з/нз";
            default:
                return "";
        }
    }
}
