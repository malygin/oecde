package org.sgu.oecde.web.jsfbeans.teacher;

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
@ManagedBean(name="groupPoints")
@ViewScoped
public class GroupPoints extends AbstractStudentsListBean{

    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;
    private List<PointsFacade>points;

    private static final long serialVersionUID = 113L;

    public List<PointsFacade> getGroupPointsAndGrades() {
        if(points==null)
            points = gradesService.getCurriculumGrades(getCurriculum(), getStudentsList());
        return points;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

}
