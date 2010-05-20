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
    private boolean controlWorksPaperOnly;
    private int controlWorksInRealCurriculum;
    private int labWorksNumber;
    private int termPapersNumber;
    private boolean selected = true;
    private static final long serialVersionUID = 63L;

    public DeCurriculum() {
    }

    public DeCurriculum(int id) {
        setId(id);
    }

    public int getControlWorksNumber() {
        return controlWorksNumber;
    }

    public void setControlWorksNumber(int controlWorksNumber) {
        this.controlWorksNumber = controlWorksNumber;
    }

    public boolean isControlWorksPaperOnly() {
        return controlWorksPaperOnly;
    }

    public void setControlWorksPaperOnly(boolean controlWorksPaperOnly) {
        this.controlWorksPaperOnly = controlWorksPaperOnly;
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
