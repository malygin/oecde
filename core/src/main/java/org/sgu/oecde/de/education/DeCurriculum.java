package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.AdvancedCurriculum;

/**
 *
 * @author ShihovMY
 */
public class DeCurriculum extends AdvancedCurriculum{
    private Integer controlWorksNumber;
    private Boolean controlWorksPaperOnly;
    private Integer labWorksNumber;
    private Integer termPapersNumber;
    private Boolean selected = true;
    private static final long serialVersionUID = 63L;

    public DeCurriculum() {
    }

    public DeCurriculum(Integer id) {
        super(id);
    }

    public Integer getControlWorksNumber() {
        return controlWorksNumber;
    }

    public void setControlWorksNumber(Integer controlWorksNumber) {
        this.controlWorksNumber = controlWorksNumber;
    }

    public Boolean isControlWorksPaperOnly() {
        return controlWorksPaperOnly;
    }

    public void setControlWorksPaperOnly(Boolean controlWorksPaperOnly) {
        this.controlWorksPaperOnly = controlWorksPaperOnly;
    }

    public Integer getLabWorksNumber() {
        return labWorksNumber;
    }

    public void setLabWorksNumber(Integer labWorksNumber) {
        this.labWorksNumber = labWorksNumber;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getTermPapersNumber() {
        return termPapersNumber;
    }

    public void setTermPapersNumber(Integer termPapersNumber) {
        this.termPapersNumber = termPapersNumber;
    }
    
}
