package org.sgu.oecde.web.jsfbeans.student;

import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="lowGradedDisciplinesBean")
@ViewScoped
public class LowGradedDisciplines extends StudentCurriculumBean{

    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<PointsFacade>pointsList;

    private static final long serialVersionUID = 100L;

    public List<PointsFacade> getLowGradedDisciplines() {
        if(pointsList==null){
            pointsList = gradesService.getStudentGrades(getCurriculumsByYear(), student);
            Iterator<PointsFacade> i = pointsList.iterator();
            while(i.hasNext()){
                PointsFacade points = i.next();
                boolean estimated = true;
                PointToEstimate pe = points.getGrade();
                if(pe!=null){
                    estimated = PointToEstimate.five.equals(pe)
                            ||PointToEstimate.four.equals(pe)
                            ||PointToEstimate.three.equals(pe)
                            ||PointToEstimate.passed.equals(pe);
                }
                if(points.getPoints().getSum()>175&&estimated){
                   i.remove();
                }
            }
        }
        return pointsList;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

}
