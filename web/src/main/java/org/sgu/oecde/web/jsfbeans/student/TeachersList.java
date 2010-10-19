package org.sgu.oecde.web.jsfbeans.student;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teachersListBean")
@ViewScoped
public class TeachersList extends StudentCurriculumBean{

    private Map<DeCurriculum, Teacher>teachersList;

    private static final long serialVersionUID = 99L;

    public Map<DeCurriculum, Teacher> getTeachers(){
        if(teachersList==null)
            teachersList = getCurriculumAndTeacher();
        return teachersList;
    }
}
