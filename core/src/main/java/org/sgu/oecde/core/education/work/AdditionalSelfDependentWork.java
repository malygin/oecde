/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.work;

import java.io.Serializable;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;

/**
 *
 * @author ShihovMY
 */
public class AdditionalSelfDependentWork implements Serializable{

    private Integer estimateAttemptsUsedNumber;
    private Integer pointsForWork;
    private List<? extends AbstractSelfDependentWorkResult> results;
    private Curriculum curriculum;
    private SelfDependentWork work;
    private static final long serialVersionUID = 54L;

    public AdditionalSelfDependentWork() {
    }

    public AdditionalSelfDependentWork(SelfDependentWork work) {
        this.work = work;
    }

    public Integer getEstimateAttemptsUsedNumber() {
        return estimateAttemptsUsedNumber;
    }

    public void setEstimateAttemptsUsedNumber(Integer estimateAttemptsUsedNumber) {
        this.estimateAttemptsUsedNumber = estimateAttemptsUsedNumber;
    }

    public <T extends AbstractSelfDependentWorkResult>List<T> getResults() {
        return (List<T>) results;
    }

    public void setResults(List<? extends AbstractSelfDependentWorkResult> results) {
        this.results = (List<AbstractSelfDependentWorkResult>) results;
    }

    public Integer getPointsForWork() {
        return pointsForWork;
    }

    public void setPointsForWork(Integer pointsForWork) {
        this.pointsForWork = pointsForWork;
    }

    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

    public void setWork(SelfDependentWork work) {
        this.work = work;
    }

    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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
