package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherSessionBean")
@SessionScoped
public class TeacherSessionBean extends AbstractTeacherBean{


    private List<DeCurriculum>currentCurriculums;

    private List<DeCurriculum>previousCurriculums;

    private List<Group>currentGroups;

    private List<Group>previousGroups;
    private static final long serialVersionUID = 110L;

    public List<DeCurriculum> getDisciplines(int semester){
        if((currentCurriculums==null||previousCurriculums==null)){
            setSemester(semester);
            List<DeCurriculum> l = curriculumDao.getBySemesterYearAndParameters(semesters(), year(),teacher);
            if(semester == 0)
                currentCurriculums=l;
            else
                previousCurriculums=l;
        }
        return semester == 0?currentCurriculums:previousCurriculums;
    }

    public List<Group>getGroups(int semester){
        if(((currentGroups==null&&semester==0)||(previousGroups==null&&semester==1))){
            setSemester(semester);
            List<Group>l = curriculumDao.<Group>getGroupsForTeacher(semesters(), year(),teacher);
            if(semester == 0)
                currentGroups=l;
            else
                previousGroups=l;
        }
        return semester == 0?currentGroups:previousGroups;
    }
}