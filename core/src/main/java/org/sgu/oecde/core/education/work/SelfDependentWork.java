/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.education.resourse.AbstractResource;

/**
 *
 * @author ShihovMY
 */
abstract public class SelfDependentWork extends AbstractResource{
    private int weight;
    private int estimateAttemptsNumber;
    private int trialNumber;
    private int duration;

    public SelfDependentWork() {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getEstimateAttemptsNumber() {
        return estimateAttemptsNumber;
    }

    public void setEstimateAttemptsNumber(int estimateAttemptsNumber) {
        this.estimateAttemptsNumber = estimateAttemptsNumber;
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(int trialNumber) {
        this.trialNumber = trialNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
