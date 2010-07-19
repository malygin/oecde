package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="gradesBean")
@ViewScoped
public class PointsAndGradesBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<PointsFacade>points;

    private static final long serialVersionUID = 97L;

    public List<PointsFacade>getPoints(){
        if(points==null){
            points = gradesService.getStudentGrades(getCurriculumAndTeacher(), student);
        }
        return points;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }
}