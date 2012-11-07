package org.sgu.oecde.controlworks.estimation;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 * имена полей кр
 * @author ShihovMY
 */
public enum CwEstimateNames implements IEstimate{
    /**
     * баллы за кр
     */
    control_work_points,
      /**
     * баллы за кр
     */
    control_work_max_points,
    /**
     * статус кр
     */
    control_work_value;
    private static final long serialVersionUID = 95L;

    @Override
    public String toString() {
        switch (this) {
            case control_work_points:
                return "кр баллы";
            case control_work_value:
                return "з/нз";
            case control_work_max_points:
                return "максимум балов";
            default:
                return "";
        }
    }
}
