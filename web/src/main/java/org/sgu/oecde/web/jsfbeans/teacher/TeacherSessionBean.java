package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherSessionBean")
@SessionScoped
public class TeacherSessionBean extends AbstractTeacherBean{

    private List<DeCurriculum>summerCurriculums;

    private List<DeCurriculum>winterCurriculums;

    private List<Group>summerGroups;

    private List<Group>winterGroups;
    
    private static final long serialVersionUID = 110L;

    public List<DeCurriculum> getDisciplines(int semester){
        if(((summerCurriculums==null&&semester==SemesterGetter.SUMMER_SEMESTER)||(winterCurriculums==null&&semester==SemesterGetter.WINTER_SEMESTER))){
            setSemester(semester);
            List<DeCurriculum> l = curriculumDao.getBySemesterYearAndParameters(semesters(), year(),teacher);
            if(semester == SemesterGetter.SUMMER_SEMESTER)
                summerCurriculums=l;
            else
                winterCurriculums=l;
        }
        return semester == SemesterGetter.SUMMER_SEMESTER?summerCurriculums:winterCurriculums;
    }

    public List<Group>getGroups(int semester){
        if(((summerGroups==null&&semester==SemesterGetter.SUMMER_SEMESTER)||(winterGroups==null&&semester==SemesterGetter.WINTER_SEMESTER))){
            setSemester(semester);
            List<Group>l = curriculumDao.<Group>getGroupsForTeacher(semesters(), year(),teacher,null);
            if(semester == SemesterGetter.SUMMER_SEMESTER)
                summerGroups=l;
            else
                winterGroups=l;
        }
        return semester == SemesterGetter.SUMMER_SEMESTER?summerGroups:winterGroups;
    }

    @Override
    public void setTeacher(Teacher teacher) {
        summerCurriculums = null;
        winterCurriculums = null;
        summerGroups = null;
        winterGroups = null;
        super.setTeacher(teacher);
    }
}