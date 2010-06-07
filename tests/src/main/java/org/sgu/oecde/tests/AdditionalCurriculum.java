package org.sgu.oecde.tests;

import java.io.Serializable;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.estimation.Points;

/**
 *
 * @author ShihovMY
 */
public class AdditionalCurriculum implements Serializable,Comparable<AdditionalCurriculum>{
    private Points points;
    private Curriculum curriculum;
    private int testsCount;
    private int passedTests;
    private static final long serialVersionUID = 76L;

    public AdditionalCurriculum() {
    }

    public int getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(int passedTests) {
        this.passedTests = passedTests;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public int getTestsCount() {
        return testsCount;
    }

    public void setTestsCount(int testsCount) {
        this.testsCount = testsCount;
    }

    public Curriculum getCurriculum() {
        return curriculum;
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
        final AdditionalCurriculum other = (AdditionalCurriculum) obj;
        if (this.curriculum != other.curriculum && (this.curriculum == null || !this.curriculum.equals(other.curriculum))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(AdditionalCurriculum o) {
       int curriculumInt = 0;
       if(o!=null)
           if(o.getCurriculum()!=null&&getCurriculum()!=null)
               curriculumInt = (Integer.valueOf(o.getCurriculum().getId()).compareTo(getCurriculum().getId()));
       return curriculumInt;
    }
}
