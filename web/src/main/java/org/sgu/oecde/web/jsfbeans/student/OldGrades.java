package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="oldGradesBean")
@ViewScoped
public class OldGrades extends AbstractStudentBean{

    @ManagedProperty(value="#{estimateDao}")
    private IResultDao<Estimate>estimateDao;

    @ManagedProperty(value="#{groupDao}")
    private IGroupDao groupDao;

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;
    
    private List<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>>points;

    private Group group;

    private boolean winter;

    private static final long serialVersionUID = 100L;

    public List<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>> getOldGrades(){
        if(points==null){
            List<Student>stl = ListUtil.<Student>oneItemList(student);
            Map<DeCurriculum,Teacher>map = null;
            points = new ArrayList<NewEntry<NewEntry<DeCurriculum,Teacher>,Estimate>>();

            if(semester != semesterGetter.getCurrentSemester()){
                int year = student.getGroup().getYear()-group.getYear();
                map = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semester, semesterGetter.getCurrentYear()-year, group);
            }else
                map = studentSessionBean.getCurriculumAndTeacher(semester%2);

            if(CollectionUtils.isEmpty(map))
                return points;
            else
                map = new HashMap<DeCurriculum, Teacher>(map);

            List<Estimate> l = estimateDao.getByStudentsAndCurriculums(new ArrayList(map.keySet()), stl, null);

            if(l==null)
                return points;

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
            setI = map.entrySet().iterator();
            if(setI.hasNext()){
                Map.Entry<DeCurriculum,Teacher> entry = setI.next();
                NewEntry<DeCurriculum,Teacher>ctE = new NewEntry<DeCurriculum, Teacher>(entry.getKey(), entry.getValue());
                points.add(new NewEntry<NewEntry<DeCurriculum, Teacher>, Estimate>(ctE, null));
            }
        }
        return points;
    }

    public int[][] getYears(){
        int end = 6;
        if(group.getSpeciality().getName().contains("ускор")
                                   ||group.getSpeciality().getName().contains("сокр")){
            end = 4;
        }
        int[][] l = new int[end][2];
        for(int k=1;k<=end;k++){
            l[k-1] = new int[]{k,k*2-(winter?1:0)};
        }
        return l;
    }

    @Override
    public void setSemester(int semester) {
        group = groupDao.getTeachersAndCurriculumsByOldGroup(semester/2+semester%2, student);
        if(group == null)
            group = student.getGroup();
        super.setSemester(semester);
    }

    @PostConstruct
    public void afterPropertiesSet() {
        Assert.notNull(student);
        group = student.getGroup();
        semester = student.<Group>getGroup().getYear()*2-semesterGetter.getCurrentSemester();
        winter=(semesterGetter.getCurrentSemester()==1);
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

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }
}
