package org.sgu.oecde.controlworks.estimation;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 *
 * @author ShihovMY
 */
public enum CwEstimateNames implements IEstimate{
    control_work_points,
    control_work_value;
    private static final long serialVersionUID = 95L;

    @Override
    public String toString() {
        switch (this) {
            case control_work_points:
                return "кр баллы";
            case control_work_value:
                return "з/нз";
            default:
                return "";
        }
    }
}
