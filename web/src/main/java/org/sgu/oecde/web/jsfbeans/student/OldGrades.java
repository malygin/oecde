package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.dao.IGroupDao;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="oldGradesBean")
@ViewScoped
public class OldGrades extends StudentCurriculumBean{

    @ManagedProperty(value="#{estimateDao}")
    private IResultDao<Estimate>estimateDao;

    @ManagedProperty(value="#{groupDao}")
    private IGroupDao groupDao;
    
    private List<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>>points;

    private Group group;

    private boolean winter;

    private static final long serialVersionUID = 100L;

    public List<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>> getOldGrades(){
        if(points==null){
            List<Student>stl = ListUtil.<Student>oneItemList(student);
            Map<DeCurriculum,Teacher>map = null;
            if(group!=student.getGroup())
                map = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semester, (semesterGetter.getCurrentYear()-(student.getGroup().getYear()-group.getYear())), group);
            else
                map = getCurriculumAndTeacher();
            List<Estimate> l = estimateDao.getByStudentsAndCurriculums(new ArrayList(map.keySet()), stl, null);
            if(l==null)
                return points;
            points = new ArrayList<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>>();
            Iterator<Map.Entry<DeCurriculum,Teacher>>setI = map.entrySet().iterator();
            while(setI.hasNext()){
                Map.Entry<DeCurriculum,Teacher> entry = setI.next();
                Iterator<Estimate>i = l.iterator();
                while(i.hasNext()){
                    Estimate e = i.next();
                    if(e.getCurriculum().equals(entry.getKey())){
                        NewEntry<DeCurriculum,Teacher>ctE = new NewEntry<DeCurriculum, Teacher>(entry.getKey(), entry.getValue());
                        points.add(new NewEntry<NewEntry<DeCurriculum, Teacher>, Estimate>(ctE, e));
                        i.remove();
                        setI.remove();
                        continue;
                    }
                }
            }
            if(setI.hasNext()){
                Map.Entry<DeCurriculum,Teacher> entry = setI.next();
                NewEntry<DeCurriculum,Teacher>ctE = new NewEntry<DeCurriculum, Teacher>(entry.getKey(), entry.getValue());
                points.add(new NewEntry<NewEntry<DeCurriculum, Teacher>, Estimate>(ctE, null));
            }
        }
        return points;
    }

    public Integer[] getYears(){
        int end = 6;
        if(group.getSpeciality().getName().contains("ускор")
                                   ||group.getSpeciality().getName().contains("сокр")){
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

    @Override
    public void setSemester(int semester) {
        if(semester == 0)
            semester = student.<Group>getGroup().getYear()*2-semesterGetter.getCurrentSemester();
        super.setSemester(semester);
    }

    @PostConstruct
    public void afterPropertiesSet() {
        group = groupDao.getTeachersAndCurriculumsByOldGroup(semester/2, student);
        if(group == null)
            group = student.getGroup();
        winter=(semesterGetter.getCurrentSemester()==1);
        setSemester(0);
    }

    public void setEstimateDao(IResultDao<Estimate> estimateDao) {
        this.estimateDao = estimateDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public boolean isWinter() {
        return winter;
    }

    public void setWinter(boolean winter) {
        this.winter = winter;
    }
}
