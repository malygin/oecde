package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.ExaminationType;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
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

    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

    private List<PointsFacade>points;

    @ManagedProperty(value="#{estimateDao}")
    private IEstimateDao estimateDao;

    private boolean error = false;
    private boolean saved = false;

    private static final long serialVersionUID = 113L;

    public List<PointsFacade> getPoints() {
        if(points==null)
            points = gradesService.getCurriculumGrades(getCurriculum(), getStudentsList());
        return points;
    }

    public void save(){
        for(PointsFacade p:points){
            Estimate e = p.getPoints().getWorkPoints(EstimateNames.estimate);
            e.setGradeCode(p.getGrade());
            e.setDate(DateConverter.currentDate());
            try {
                estimateDao.save(e);
            } catch (Exception ex) {
                ex.fillInStackTrace();
                error=true;
            }
        }
        journalService.save(EventType.GRADING,teacher, getGroup(), getCurriculum());
        saved=true;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    public PointToEstimate[] getEnum(){
        if(getCurriculum()!=null)
            if(ExaminationType.exame.equals(getCurriculum().getExaminationType()))
                return PointToEstimate.exameGrades();
            else if(ExaminationType.test.equals(getCurriculum().getExaminationType()))
                return PointToEstimate.testGrades();
        return new PointToEstimate[0];
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setPoints(List<PointsFacade> points) {
        this.points = points;
    }

    public void setEstimateDao(IEstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }
}
