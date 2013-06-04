package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.web.jsfbeans.util.CryptoClassDES;

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
// тут
    public List<DeCurriculum> getDisciplines(int semester){
        //semester=this.semesterGetter.getCurrentSemester();
        if((((summerCurriculums==null||summerCurriculums.isEmpty())&&semester==SemesterGetter.SUMMER_SEMESTER)||((winterCurriculums==null||winterCurriculums.isEmpty())&&semester==SemesterGetter.WINTER_SEMESTER))){
            setSemester(semester);
            List<DeCurriculum> l = curriculumDao.getBySemesterYearAndParameters(semesters(), year(),teacher);
            if(semester == SemesterGetter.SUMMER_SEMESTER)
                summerCurriculums=l;
            else
                winterCurriculums=l;
        }
        return semester == SemesterGetter.SUMMER_SEMESTER?summerCurriculums:winterCurriculums;
    }

    public void reloadCur(){
        summerCurriculums = curriculumDao.getBySemesterYearAndParameters(semesterGetter.getSemestersByInt(0), year(),teacher);
        winterCurriculums = curriculumDao.getBySemesterYearAndParameters(semesterGetter.getSemestersByInt(1), year(),teacher);

    }
    
       public DeCurriculum getCurriculumById(Long cId){
            DeCurriculum d=null;
            for (DeCurriculum c: summerCurriculums)
                if (c.getId().equals(cId))  d=c;
           if (d==null)
               for (DeCurriculum c: winterCurriculums)
                   if (c.getId().equals(cId))  d=c;

//           for (DeCurriculum c:(summerCurriculums!=null&&!summerCurriculums.isEmpty())?summerCurriculums:winterCurriculums)
//                if (c.getId().equals(cId))  d=c;
            return d;
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
     public String getEncryptedUserNPass() throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String encryptMe = this.teacher.getUsername() +":"+ this.teacher.getPassword();
        String returnMe = CryptoClassDES.encrypt(encryptMe);
        return returnMe;
    }
}