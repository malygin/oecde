package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 *
 * @author ShihovMY
 */
 public class SelfDependentWork extends AbstractResource{
    private Integer weight;
    private Integer estimateAttemptsNumber;
    private Integer trialNumber;
    private Integer duration;

    public SelfDependentWork() {
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getEstimateAttemptsNumber() {
        return estimateAttemptsNumber;
    }

    public void setEstimateAttemptsNumber(Integer estimateAttemptsNumber) {
        this.estimateAttemptsNumber = estimateAttemptsNumber;
    }

    public Integer getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
