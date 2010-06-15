package org.sgu.oecde.core.education.work;

/**
 *
 * @author ShihovMY
 */
public class Estimate extends AbstractResult {
    private PointToEstimate gradeCode;
    private static final long serialVersionUID = 67L;

    public Estimate() {
    }

    public PointToEstimate getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(PointToEstimate gradeCode) {
        this.gradeCode = gradeCode;
    }
}
