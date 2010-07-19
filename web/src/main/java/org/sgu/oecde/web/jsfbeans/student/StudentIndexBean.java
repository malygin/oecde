package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentIndexBean")
@ViewScoped
public class StudentIndexBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    public List<PointsFacade>  getGroupRating() {
        setSemester(0);
        List<PointsFacade>points = gradesService.getCurriculumsAndStudentsGrades(getCurriculums(), new ArrayList<Student>(student.<Group>getGroup().getPersons()));
        Collections.sort(points, new ByRating());
        return points;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    private class ByRating implements Comparator<PointsFacade>{

        @Override
        public int compare(PointsFacade o1, PointsFacade o2) {
            int sum = 0;
            if(o1!= null && o2!=null&&o1.getPoints()!=null&&o2.getPoints()!=null)
                sum = Integer.valueOf(o2.getPoints().getSum()).compareTo(o1.getPoints().getSum());
            return sum;

        }
    }
}
