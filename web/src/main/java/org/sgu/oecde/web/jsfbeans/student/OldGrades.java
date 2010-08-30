package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;

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

    private static final long serialVersionUID = 100L;

    public List<PointsFacade> getOldGrades(){
        if(points==null){
            List<IResultFilter>filters = new LinkedList();
            filters.add(estimateFilter);
            List<Student>stl = ListUtil.<Student>oneItemList(student);
            List<Estimate> l = estimateDao.getByStudentsAndCurriculums(new ArrayList(getCurriculumAndTeacherByYear().keySet()), stl, null);
            List<Points> ps = preFilter.forEachResult(l, true,filters,ListUtil.<Student>oneItemList(student),getCurriculums());
            List<PointsFacade>facades = new ArrayList<PointsFacade>(ps.size());
            for(Points p:ps){
                facades.add(GradesService.putTeacherIntoFacade(p, getCurriculumAndTeacherByYear()));
            }
        }
        return points;
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
}
