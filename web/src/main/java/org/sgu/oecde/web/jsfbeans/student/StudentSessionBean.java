package org.sgu.oecde.web.jsfbeans.student;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSessionBean")
@SessionScoped
public class StudentSessionBean extends AbstractStudentBean{

    private Map<DeCurriculum,Teacher>currentCurriculums;

    private Map<DeCurriculum,Teacher>previousCurriculums;

    private Boolean switched;

    private static final long serialVersionUID = 150L;

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher(int semester) {
        if(((currentCurriculums==null&&semester==0)||(previousCurriculums==null&&semester==1))){
            setSemester(semester);
            int correctSemester = student.isTransfered()!=null&&student.isTransfered()?0:1;
            Map<DeCurriculum,Teacher> l = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semesterGetter.getSemesterByStudentYear(student, semester-correctSemester).intValue(), semesterGetter.getCalendarYear(correctSemester), student.getGroup());
            if(semester == 0)
                currentCurriculums=l;
            else
                previousCurriculums=l;
        }
        return semester == 0?currentCurriculums:previousCurriculums;
    }

    public boolean isSwitched(){
        if(switched == null){
            switched = false;
            for(GrantedAuthority a:student.getAuthorities()){
                if("ROLE_PREVIOUS_ADMINISTRATOR".equals(a.getAuthority())){
                    switched = true;
                }
            }
        }
        return switched;
    }
}
