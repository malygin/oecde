package org.sgu.oecde.web.jsfbeans.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.TeacherToGroup;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.web.jsfbeans.UserViewBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherIndexBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherSessionBean;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.web.GradesService;
/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherViewBean")
@ViewScoped
public class TeacherViewBean extends UserViewBean{

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;
    
    @ManagedProperty(value="#{teacherIndexBean}")
    private TeacherIndexBean teacherIndexBean;
    
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;

   @ManagedProperty(value="#{gradesService}")
   private GradesService gradesService;
  
   
   private static final long serialVersionUID = 172L;
    
    private List<Group> groups;
    private  Integer semester;
    protected AbstractUser user;
    private  List<GroupViewFacade> groupsFacade;
     public int getCurrentSemester(){
        return semesterGetter.getCurrentSemester();
    }
    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
        semester = semesterGetter.getCurrentSemester();
    }
    public Integer getSemester() {
        return semester;
    }
    
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    
     public TeacherViewBean() {
         setType("TEACHER");
//         ;
    }
     
    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public void setTeacherIndexBean(TeacherIndexBean teacherIndexBean) {
        this.teacherIndexBean = teacherIndexBean;
    }


    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }
    @Override
    public AbstractUser getUser() {
        if(user==null){
            user = super.getUser();
            teacherSessionBean.setTeacher((Teacher) user);
            teacherIndexBean.setTeacher((Teacher) user);
        }
        return user;
    }
    //группы 
    
    public List<GroupViewFacade> getTeacherGroups(){
// не оценено
        int notEstimated;
        int two;
        int three;
        int four;
        int five;
//        неявка
        int absence;
//        зачтено
        int passed;
//         не зачтено
        int failed;
        getUser();
    //    if(groupsFacade!=null){
            List<DeCurriculum> l = teacherSessionBean.getDisciplines(semester);
            groupsFacade = new ArrayList<GroupViewFacade>();
            for(DeCurriculum dc: l){
                ArrayList<TeacherToGroup> teacherGroups = new ArrayList(dc.getTeacherToGroups());
                for(TeacherToGroup teacherToGroup: teacherGroups){ 
                    GroupViewFacade facade = new GroupViewFacade(teacherToGroup.getGroup().getName());


                    notEstimated=0;
                    two=0;
                    three=0;
                    four=0;
                    five=0;
                    absence=0;
                    failed=0;
                    passed=0;
                    List<Points> points= gradesService.getGrades(l, teacherToGroup.getGroup().getPersons());
                      for(Points p:points){
                         Estimate e = p.getWorkPoints(EstimateNames.estimate);
                         if (e!=null){
                             switch(e.getGradeCode()){
                                case notEstimated: notEstimated++; break;
                                case two: two++; break;
                                case three: three++; break;
                                case four: four++; break;
                                case five: five++; break;
                                case absence: absence++; break;
                                case passed: passed++; break;
                                case failed: failed++; break;                            
                             }
                          }
                      }
                      if(groupsFacade.contains(facade)){
                          groupsFacade.get(groupsFacade.indexOf(facade)).getDistiplines().add(new Discipline(dc.getDiscipline().getName(),notEstimated,two,three,four,five,absence,passed,failed));
                      }
                      else {
                        groupsFacade.add(facade);
                        facade.getDistiplines().add(new Discipline(dc.getDiscipline().getName(),notEstimated,two,three,four,five,absence,passed,failed));
                        facade.setPersons(teacherToGroup.getGroup().getPersons().size());
                    }
                }
            }
      //  }
//        groups = teacherSessionBean.getGroups(semester); 
        return groupsFacade;
    }
    public class GroupViewFacade{
        Group group;
        String name;
        int persons;
        List <Discipline> distiplines;
        
        @Override
        public boolean equals(Object o){
            if(o==null) return false;
            if(this.hashCode()!= o.hashCode()) return false;
            return this.name.equals(((GroupViewFacade) o).getName());
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
            return hash;
        }
        
        public GroupViewFacade(String name){
            this.name = name;
            distiplines = new ArrayList();
        }
        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPersons() {
            return persons;
        }

        public void setPersons(int persons) {
            this.persons = persons;
        }

        public List<Discipline> getDistiplines() {
            return distiplines;
        }

        public void setDistiplines(List<Discipline> distiplines) {
            this.distiplines = distiplines;
        }
        
    }
    public class Discipline{
        String discipline;
        int notEstimated;
        int two;
        int three;
        int four;
        int five;
        int absence;
        int passed;
        int failed;
        
        @Override
        public boolean equals(Object o){
            if(o==null) return false;
            if(this.hashCode()!= o.hashCode()) return false;
            return this.discipline.equals(((Discipline) o).getDiscipline());
        }
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + (this.discipline != null ? this.discipline.hashCode() : 0);
            return hash;
        }
        public Discipline(String discipline,int notEstimated,
        int two,
        int three,
        int four,
        int five,
        int absence,
        int passed,
        int failed){
            this.discipline=discipline;
            this.two = two;
            this.three = three;
            this.four = four;
            this.five = five;
            this.absence = absence;
            this.passed = passed;
            this.failed = failed;
        }
        public int getAbsence() {
            return absence;
        }

        public void setAbsence(int absence) {
            this.absence = absence;
        }

        public String getDiscipline() {
            return discipline;
        }

        public void setDiscipline(String discipline) {
            this.discipline = discipline;
        }

        public int getFailed() {
            return failed;
        }

        public void setFailed(int failed) {
            this.failed = failed;
        }

        public int getFive() {
            return five;
        }

        public void setFive(int five) {
            this.five = five;
        }

        public int getFour() {
            return four;
        }

        public void setFour(int four) {
            this.four = four;
        }

        public int getNotEstimated() {
            return notEstimated;
        }

        public void setNotEstimated(int notEstimated) {
            this.notEstimated = notEstimated;
        }

        public int getPassed() {
            return passed;
        }

        public void setPassed(int passed) {
            this.passed = passed;
        }

        public int getThree() {
            return three;
        }

        public void setThree(int three) {
            this.three = three;
        }

        public int getTwo() {
            return two;
        }

        public void setTwo(int two) {
            this.two = two;
        }
        
        
    }
    
    public int getGroupsCount(){
        return  groupsFacade.size();
    }
    
    public int getStudentsCount(){
        int count=0;
        groups = teacherSessionBean.getGroups(semester);
        for(Group group:  groups){
            count += group.getPersons().size();
        }
        return count;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }
}
