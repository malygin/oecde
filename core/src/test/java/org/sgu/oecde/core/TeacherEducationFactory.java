package org.sgu.oecde.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.TeacherToGroup;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.de.users.Teacher;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
public class TeacherEducationFactory extends BasicTest{
    private SemesterGetter sg;
    
    @Before
    public void setUpClass() throws Exception {
        sg = (SemesterGetter) applicationContext.getBean("semesterGetter");
    }

     private Student getSt(){
        Speciality s = new Speciality(28);
        Student st = new Student();
        Group gr = new Group();
        gr.setYear(2);
        gr.setSpeciality(s);
        setDao("groupDao");
        st.setGroup(this.<Group>getByExample(gr).get(0));
        return st;
    }

    private DeCurriculum fillCurriculum(){
        Student st = getSt();
        DeCurriculum c = new DeCurriculum();
        c.setCalendarYear(sg.getConstants().getYear());
        c.setSemester(sg.getSemesterByStudentYear(st, 0));
        c.setSpeciality(st.<Group>getGroup().getSpeciality());
        return c;
    }

    private DeCurriculum fillCurriculum(Discipline d){
        DeCurriculum c = fillCurriculum();
        c.setDiscipline(d);
        return c;
    }

    public void getDisciplineForStudent() {
        setDao("curriculumDao");
        Discipline d = new Discipline(15);
        DeCurriculum c = fillCurriculum(d);
        c = this.<DeCurriculum>getByExample(c).get(0);
        c.getDiscipline();
        c.getExaminationType();
    }

   // @Ignore
    @Test
    public void getTeachersDisciplines(){
        setDao("curriculumDao");
        int year = sg.getCalendarYear(0);
        ICurriculumDao<DeCurriculum> cDao = this.<ICurriculumDao>getDao();
        List<DeCurriculum> l = cDao.getBySemesterYearAndParameters(sg.getSemestersByInt(0), year,new Teacher(44240));
        for(DeCurriculum c:l){
            System.out.println(c.getDiscipline().getName());
        }
    }

    @Ignore
    @Test
    public void getTeacherDisciplinesWithUmk(){
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        for(DeCurriculum cr:this.<DeCurriculum>getByExample(c)){
            System.out.println(cr.getUmk().getName());
        }
    }

    @Ignore
    @Test
    public void getTeachersGroups(){
        setDao("curriculumDao");
        int year = sg.getCalendarYear(0);
        ICurriculumDao<DeCurriculum> cDao = this.<ICurriculumDao>getDao();
        for(Group c:cDao.<Group>getGroupsForTeacher(sg.getSemestersByInt(0), year,new Teacher(44240))){
            System.out.println(c.getCity().getName());
        }
    }

}