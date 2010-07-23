package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.GradesService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentRatingBean")
@ViewScoped
public class StudentRatingBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<Points>rating;
    public List<Points>  getGroupRating() {
        if(rating==null){
            setSemester(0);
            rating = gradesService.getGrades(getCurriculums(), new ArrayList<Student>(student.<Group>getGroup().getPersons()));
            Collections.sort(rating, new ByRating());
        }
        return rating;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
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
