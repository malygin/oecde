package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.ExaminationType;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.activity.Activity;
import org.sgu.oecde.core.education.estimation.activity.ActivityEstimateNames;
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
   
    @ManagedProperty(value="#{activityDao}")
    private IUpdateDao<Activity> actDao;

    private boolean error = false;
    private boolean saved = false;
    private String  error_message = "Приозошла ошибка";

    private static final long serialVersionUID = 113L;

    public List<PointsFacade> getPoints() {
        if(points==null || saved)  {
            points = gradesService.getCurriculumGrades(getCurriculum(), getStudentsList());
            saved = false; }
        return points;
    }

    public int getStudentNumber(PointsFacade p){
        return points.indexOf(p)+1;
    }

    public void save(){
        error=false;
        saved = true;
        for(PointsFacade p:points){          
            Estimate e = p.getPoints().getWorkPoints(EstimateNames.estimate);
            if (e==null){
                e=new Estimate();
                e.setStudent(p.getPoints().getStudent());
                e.setCurriculum(p.getPoints().getCurriculum());
            }

            if (!p.getGrade().equals(e.getGradeCode())) {
                e.setGradeCode(p.getGrade());
                e.setDate(DateConverter.currentDate());
                try {
                    estimateDao.save(e);
                } catch (Exception ex) {
                    ex.fillInStackTrace();
                    error=true;
                }
            }


        if (p.getSamAudOutWorkPoints() <=40 && p.getActivityPoints() <=40 && p.getSamAudWorkPoints() <=40 &&
                p.getPublishPoints() <=40 && p.getLecPoints() <=40 && p.getPersonalCharPoints() <=40){

             if ((p.getSamAudOutWorkPoints()+ p.getActivityPoints()+ p.getSamAudWorkPoints()+
                    p.getPublishPoints()+ p.getLecPoints()+p.getPersonalCharPoints())<=100){


                Activity a=p.getPoints().getWorkPoints(ActivityEstimateNames.activity);
               if (a==null){
                    a=new Activity();
                    a.setCurriculum(p.getPoints().getCurriculum());
                    a.setDate(DateConverter.currentDate());
                    a.setStudent(p.getPoints().getStudent());
                }

                if (a.getPoints()== null || a.getLecpoints()== null || a.getPublishpoints()== null|| (!(a.getPoints().equals(p.getActivityPoints()) && a.getSamAudWorkpoints().equals(p.getSamAudWorkPoints())
                        && a.getLecpoints().equals(p.getLecPoints())   && a.getPublishpoints().equals(p.getPublishPoints())
                        && a.getSamOutAudWorkpoints().equals(p.getSamAudOutWorkPoints()) && a.getPersonalCharpoints().equals(p.getPersonalCharPoints()) ))) {

                        a.setPoints(p.getActivityPoints());
                        a.setSamAudWorkpoints(p.getSamAudWorkPoints());
                        a.setPersonalCharpoints(p.getPersonalCharPoints());
                        a.setSamOutAudWorkpoints(p.getSamAudOutWorkPoints());
                        a.setLecpoints(p.getLecPoints());
                        a.setPublishpoints(p.getPublishPoints());
                       actDao.update(a);

                }
           

                journalService.save(EventType.GRADING,teacher, getGroup(), getCurriculum().getDiscipline());
                saved=true;
                error=false;
             }
            else{
                 error_message = " сумма должна быть меньше ста!";
                 error=true;
             }
        }
        else{
            error_message = "значения в полях не могут быть больше 40";
            error=true;
        }
        }
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
            else if(ExaminationType.empty.equals(getCurriculum().getExaminationType()))
                return PointToEstimate.emptyGrades();
            else return PointToEstimate.emptyGrades();
        
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

    public IUpdateDao<Activity> getActDao() {
        return actDao;
    }

    public void setActDao(IUpdateDao<Activity> actDao) {
        this.actDao = actDao;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
