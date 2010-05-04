/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.work;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ShihovMY
 */
public class AdditionalSelfDependentWork implements Serializable{

    private int estimateAttemptsUsedNumber;
    private int pointsForWork;
    private List<? extends AbstractSelfDependentWorkResult> results;
    private SelfDependentWork work;
    private static final long serialVersionUID = 66L;

    public AdditionalSelfDependentWork() {
    }

    public AdditionalSelfDependentWork(SelfDependentWork work) {
        this.work = work;
    }

    public int getEstimateAttemptsUsedNumber() {
        return estimateAttemptsUsedNumber;
    }

    public void setEstimateAttemptsUsedNumber(int estimateAttemptsUsedNumber) {
        this.estimateAttemptsUsedNumber = estimateAttemptsUsedNumber;
    }

    public <T extends AbstractSelfDependentWorkResult>List<T> getResults() {
        return (List<T>) results;
    }

    public void setResults(List<? extends AbstractSelfDependentWorkResult> results) {
        this.results = (List<AbstractSelfDependentWorkResult>) results;
    }

    public int getPointsForWork() {
        return pointsForWork;
    }

    public void setPointsForWork(int pointsForWork) {
        this.pointsForWork = pointsForWork;
    }

    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

    public void setWork(SelfDependentWork work) {
        this.work = work;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdditionalSelfDependentWork other = (AdditionalSelfDependentWork) obj;
        if (this.work != other.work && (this.work == null || !this.work.equals(other.work))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.work != null ? this.work.hashCode() : 0);
        return hash;
    }
}
