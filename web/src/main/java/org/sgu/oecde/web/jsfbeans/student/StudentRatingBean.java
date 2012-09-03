package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    
   @ManagedProperty(value="#{activityFilter}")
    private IResultFilter activityFilter;
    
    private List<Points>rating;
    private List<Points>leadersRating;
    private List<Points>otherRating;

    private Points points;
    
    private int maxpoints;

    public List<Points>  getGroupRating() {
        if(rating==null){
            rating = getStudentsRating(student.<Group>getGroup().getPersons());
          }
        return rating;
     }
           
    public List<Points> getLeadersRating(){
        try{
             if(leadersRating==null){
                otherRating = getStudentsRating(student.<Group>getGroup().getPersons());
                maxpoints=Math.round(otherRating.get(0).getSum());
                int i=0;
                leadersRating=new ArrayList<Points>();            
                for(Iterator<Points> p=otherRating.iterator();  p.hasNext();){
                    if (i++>=3) break; 
                    leadersRating.add(p.next());
                    p.remove();                     

                }

            }}
        catch(Exception e){
            return null;
        }
        return leadersRating;
    }
    
    public List<Points>getOtherRaiting(){
        return otherRating;
    }

    public Points getStudentRating(){
        if(points == null){
        //    System.out.println("points");
            List<Student>students = ListUtil.<Student>oneItemList(student);
            List<Points>rating = getStudentsRating(students);
            if(rating.size()==1)
                points = rating.get(0);
            else
                return new Points();
        }
        return points;
    }

    private List<Points> getStudentsRating(Collection<Student> students){
        setSemester(this.semesterGetter.getCurrentSemester());
        
        List<IResultFilter>filters = new ArrayList(3);
        filters.add(controlWorkFilter);
        filters.add(testFilter);  
        filters.add(activityFilter);
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(getCurriculums(), students, null);
      //  System.out.println("l"+l);
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
                sum = Integer.valueOf(Math.round(o2.getSum())).compareTo(Math.round(o1.getSum()));
            return sum;

        }
    }

    public int getMaxpoints() {
        return maxpoints;
    }

    public void setMaxpoints(int maxpoints) {
        this.maxpoints = maxpoints;
    }

    public IResultFilter getActivityFilter() {
        return activityFilter;
    }

    public void setActivityFilter(IResultFilter activityFilter) {
        this.activityFilter = activityFilter;
    }
    
    
}
