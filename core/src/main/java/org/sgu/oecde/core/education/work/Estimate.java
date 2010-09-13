package org.sgu.oecde.core.education.work;

/**
 * итоговая оценка
 * @author ShihovMY
 */
public class Estimate extends AbstractResult {
    /**
     * код оценки
     */
    private PointToEstimate gradeCode;
    private static final long serialVersionUID = 67L;

    public Estimate() {
    }

    /**
     * код оценки
     * @return
     */
    public PointToEstimate getGradeCode() {
        return gradeCode;
    }

    /**
     * код оценки
     * @param gradeCode
     */
    public void setGradeCode(PointToEstimate gradeCode) {
        this.gradeCode = gradeCode;
    }

    @Override
    public String toString() {
        return this.gradeCode!=null?(gradeCode.toString()):(PointToEstimate.notEstimated.toString());
    }
}
