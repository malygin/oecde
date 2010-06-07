package org.sgu.oecde.core.education.estimation;

/**
 *
 * @author ShihovMY
 */
public enum EstimateNames implements IEstimate{
    estimate;

    @Override
    public String toString() {
        switch(this){
            case estimate:
            default:
                return "Оценка";
        }
    }
}
