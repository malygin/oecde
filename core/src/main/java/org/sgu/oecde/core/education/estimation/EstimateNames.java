package org.sgu.oecde.core.education.estimation;

/**
 * название поля с итоговой оценкой
 * @author ShihovMY
 */
public enum EstimateNames implements IEstimate{
    /**
     * оценка
     */
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
