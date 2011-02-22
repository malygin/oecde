package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentRatingBean")
@ViewScoped
public class StudentRatingBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{testFilter}")
    private IResultFilter testFilter;

    @ManagedProperty(value="#{controlWorkFilter}")
    private IResultFilter controlWorkFilter;

    @ManagedProperty(value="#{preFilter}")
    private ResultPreFilter preFilter;

    @ManagedProperty(value="#{resultDao}")
    private IResultDao<AbstractResult>resultDao;

    private List<Points>rating;

    private Points points;

    public List<Points>  getGroupRating() {
        if(rating==null){
            List<Student>students = new ArrayList<Student>(student.<Group>getGroup().getPersons());
            rating = getStudentsRating(students);
        }
        return rating;
    }

    public Points getStudentRating(){
        if(points == null){
            List<Student>students = ListUtil.<Student>oneItemList(student);
            List<Points>rating = getStudentsRating(students);
            if(rating.size()==1)
                points = rating.get(0);
            else
                return new Points();
        }
        return points;
    }

    private List<Points> getStudentsRating(List<Student>students){
        setSemester(0);
        List<IResultFilter>filters = new ArrayList(2);
        filters.add(controlWorkFilter);
        filters.add(testFilter);
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(getCurriculums(), students, null);
        List<Points>rating = preFilter.forEachResult(l, false,filters,students,getCurriculums());
        if(rating == null)
            return new ArrayList<Points>(0);
        Collections.sort(rating, new ByRating());
        return rating;
    }

    public void setControlWorkFilter(IResultFilter controlWorkFilter) {
        this.controlWorkFilter = controlWorkFilter;
    }

    public void setPreFilter(ResultPreFilter preFilter) {
        this.preFilter = preFilter;
    }

    public void setResultDao(IResultDao<AbstractResult> resultDao) {
        this.resultDao = resultDao;
    }

    public void setTestFilter(IResultFilter testFilter) {
        this.testFilter = testFilter;
    }

    private class ByRating implements Comparator<Points>{

        @Override
        public int compare(Points o1, Points o2) {
            int sum = 0;
            if(o1!= null && o2!=null)
                sum = Integer.valueOf(o2.getSum()).compareTo(o1.getSum());
            return sum;

        }
    }
}
