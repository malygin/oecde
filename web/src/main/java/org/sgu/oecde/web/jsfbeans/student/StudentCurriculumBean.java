package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentCurriculumBean")
@ViewScoped
public class StudentCurriculumBean extends AbstractStudentBean{

    private List<DeCurriculum>curriculums;

    private Map<DeCurriculum,Teacher>curriculumAndTeacher;

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    private boolean sort = false;
    
    private static final long serialVersionUID = 104L;

    public List<DeCurriculum> getCurriculums() {
        if(curriculums==null){
            curriculums = new ArrayList(studentSessionBean.getCurriculumAndTeacher(semester).keySet());
            if(sort)
                Collections.sort(curriculums, new OrderByDisciplineName());
        }
        return curriculums;
    }

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher() {
        if(curriculumAndTeacher == null){
            curriculumAndTeacher = studentSessionBean.getCurriculumAndTeacher(semester);
        }
        return curriculumAndTeacher;
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    private class OrderByDisciplineName implements Comparator<DeCurriculum>{

        @Override
        public int compare(DeCurriculum o1, DeCurriculum o2) {
            int discipline = 0;
            if(o1!=null &&o2!=null &&o1.getDiscipline()!=null &o2.getDiscipline()!=null &&o1.getDiscipline().getName()!=null ){
                discipline = o1.getDiscipline().getName().compareTo(o2.getDiscipline().getName());
            }
            return discipline;
        }

    }
}
