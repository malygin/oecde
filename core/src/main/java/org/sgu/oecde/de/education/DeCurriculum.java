/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.AdvancedCurriculum;

/**
 *
 * @author ShihovMY
 */
public class DeCurriculum extends AdvancedCurriculum{
    private int controlWorksNumber;
    private int controlWorksPaperOnlyNumber;
    private int controlWorksInRealCurriculum;
    private int labWorksNumber;
    private int termPapersNumber;
    private boolean selected = true;
    private static final long serialVersionUID = 63L;

    public DeCurriculum() {
    }

    public int getControlWorksNumber() {
        return controlWorksNumber;
    }

    public void setControlWorksNumber(int controlWorksNumber) {
        this.controlWorksNumber = controlWorksNumber;
    }

    public int getControlWorksPaperOnlyNumber() {
        return controlWorksPaperOnlyNumber;
    }

    public void setControlWorksPaperOnlyNumber(int controlWorksPaperOnlyNumber) {
        this.controlWorksPaperOnlyNumber = controlWorksPaperOnlyNumber;
    }

    public int getLabWorksNumber() {
        return labWorksNumber;
    }

    public void setLabWorksNumber(int labWorksNumber) {
        this.labWorksNumber = labWorksNumber;
    }

    public int getControlWorksInRealCurriculum() {
        return controlWorksInRealCurriculum;
    }

    public void setControlWorksInRealCurriculum(int controlWorksInRealCurriculum) {
        this.controlWorksInRealCurriculum = controlWorksInRealCurriculum;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getTermPapersNumber() {
        return termPapersNumber;
    }

    public void setTermPapersNumber(int termPapersNumber) {
        this.termPapersNumber = termPapersNumber;
    }
    
}
