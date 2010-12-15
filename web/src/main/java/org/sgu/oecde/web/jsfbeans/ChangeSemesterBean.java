package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.core.util.SemesterGetter;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ApplicationScoped
public class ChangeSemesterBean implements Serializable{

    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

    public String semesterName(int semester){
        return semesterGetter.getSemesterName(semester);
    }

    public String inversedSemesterName(int semester){
        return semesterGetter.getSemesterName(inversedSemesterValue(semester));
    }

    public int inversedSemesterValue(int semester){
        semesterGetter.validate(semester);
        return SemesterGetter.SUMMER_SEMESTER == semester?SemesterGetter.WINTER_SEMESTER:SemesterGetter.SUMMER_SEMESTER;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }
}
