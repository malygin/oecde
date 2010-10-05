package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="oldGradesBean")
@ViewScoped
public class OldGrades extends StudentCurriculumBean{

    @ManagedProperty(value="#{estimateFilter}")
    private IResultFilter estimateFilter;
    
    @ManagedProperty(value="#{preFilter}")
    private ResultPreFilter preFilter;

    @ManagedProperty(value="#{estimateDao}")
    private IResultDao<Estimate>estimateDao;
    
    private List<PointsFacade>points;

    private boolean winter;

    private static final long serialVersionUID = 100L;

    public List<PointsFacade> getOldGrades(){
        if(points==null){
            List<IResultFilter>filters = new LinkedList();
            filters.add(estimateFilter);
            List<Student>stl = ListUtil.<Student>oneItemList(student);
            List<DeCurriculum>cr = new ArrayList(getCurriculumAndTeacherByYear().keySet());
            List<Estimate> l = estimateDao.getByStudentsAndCurriculums(cr, stl, null);
            List<Points> ps = preFilter.forEachResult(l, true,filters,ListUtil.<Student>oneItemList(student),cr);
            points = new ArrayList<PointsFacade>(ps.size());
            for(Points p:ps){
                points.add(GradesService.putTeacherIntoFacade(p, getCurriculumAndTeacherByYear()));
            }
        }
        return points;
    }

    public Integer[] getYears(){
        int end = 6;
        if(student.<Group>getGroup().getSpeciality().getName().contains("ускор")
                                   ||student.<Group>getGroup().getSpeciality().getName().contains("сокр")){
            end = 4;
        }
        Integer[] l = new Integer[end];
        for(int k=1;k<=end;k++){
            l[k-1] = k*2-(winter?1:0);
        }
        return l;
    }

    public void changeIsWinter(AjaxBehaviorEvent event){
        Boolean w = (Boolean) event.getComponent().getAttributes().get("winter");
        winter = w;
        points = null;
    }

    public void setEstimateDao(IResultDao<Estimate> estimateDao) {
        this.estimateDao = estimateDao;
    }

    public void setEstimateFilter(IResultFilter estimateFilter) {
        this.estimateFilter = estimateFilter;
    }

    public void setPreFilter(ResultPreFilter preFilter) {
        this.preFilter = preFilter;
    }

    public boolean isWinter() {
        return winter;
    }

    public void setWinter(boolean winter) {
        this.winter = winter;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        winter=(semesterGetter.getCurrentSemester()==1);
        setSemester(0);
    }

    @Override
    public void setSemester(int semester) {
        if(semester == 0)
            semester = student.<Group>getGroup().getYear()*2-semesterGetter.getCurrentSemester();
        super.setSemester(semester);
    }
}
