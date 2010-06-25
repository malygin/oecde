package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.TeacherToGroup;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.core.util.Semesters;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.tests.TestAttemptService;
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
        Speciality s = new Speciality(28L);
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
        c.setCalendarYear(sg.getCurrentYear());
        c.setSemester(sg.getSemesterByStudentYear(st, 0));
        c.setSpeciality(st.<Group>getGroup().getSpeciality());
        return c;
    }

    private DeCurriculum fillCurriculum(Discipline d){
        DeCurriculum c = fillCurriculum();
        c.setDiscipline(d);
        return c;
    }

    private List<Student> getStudentsList(Long id){
        setDao("groupDao");
        Group g = getItem(id);
        return new ArrayList(g.getPersons());
    }

    @Ignore
    @Test
    public void getDisciplineForStudent() {
        setDao("curriculumDao");
        Discipline d = new Discipline(15L);
        DeCurriculum c = fillCurriculum(d);
        c = this.<DeCurriculum>getByExample(c).get(0);
        c.getDiscipline();
        c.getExaminationType();
    }

    @Ignore
    @Test
    public void getTeachersDisciplines(){
        setDao("curriculumDao");
        int year = sg.getCalendarYear(0);
        ICurriculumDao<DeCurriculum> cDao = this.<ICurriculumDao>getDao();
        List<DeCurriculum> l = cDao.getBySemesterYearAndParameters(sg.getSemestersByInt(0), year,new Teacher(44240L));
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
        for(Group c:cDao.<Group>getGroupsForTeacher(sg.getSemestersByInt(0), year,new Teacher(44240L))){
            System.out.println(c.getCity().getName());
        }
    }

    @Ignore
    @Test
    public void getTestsResultsByDisc() {
        TestAttemptService s = getBean("testAttemptService");
        DeCurriculum c = fillCurriculum();
        System.out.println(s.getStudentsSingleCurriculumAttempts(c, null, new Student(324725L)));
    }

    @Ignore
    @Test
    public void getGroupTestResults() {
        TestAttemptService s = getBean("testAttemptService");
        DeCurriculum c = fillCurriculum();
        List l = getStudentsList(22510L);
        System.out.println(s.getCurriculumAttempts(c, null, l));
    }
    @Ignore
    @Test
    public void getGroupPointsAndGrades() {
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f1 = (IResultFilter)getBean("cwFilter");
        IResultFilter f2 = (IResultFilter)getBean("testFilter");
        IResultFilter f3 = (IResultFilter)getBean("estimateFilter");
        ResultPreFilter pf = (ResultPreFilter) applicationContext.getBean("preFilter");
        filters.add(f2);
        filters.add(f1);
        filters.add(f3);
        List<DeCurriculum> c = new ArrayList<DeCurriculum>();
        c.add(fillCurriculum());
        List sl = getStudentsList(22510L);
        setDao("resultDao");
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(c, sl, null);
        pf.forEachResult(l, true,filters);
    }

    @Ignore
    @Test
    public void getGroupControlWorks() {
        ControlWorkService sv = getBean("controlWorkService");
        List sl = getStudentsList(22510L);
        DeCurriculum c = fillCurriculum();
        sv.getCurriculumControlWorks(sl, c);
    }

    @Ignore
    @Test
    public void getStudentControlWorks() {
        ControlWorkService sv = getBean("controlWorkService");
        List<DeCurriculum> c = new ArrayList<DeCurriculum>();
        c.add(fillCurriculum());
        sv.getStudensControlWorks(new Student(324725L), c);
    }

    @Ignore
    @Test
    public void getUmk(){
        fillCurriculum().getUmk();
    }
/*
    @Ignore
    @Test
    public void getStatisctic() throws Exception{
        StatisticDAO dao = new StatisticDAO();
        dao.getCountsBySemestrAndYear(teacher, getCurrentSemestr(), getCurrentYear());
        dao.getEventsCount(teacher);
        return teacher;
    }
*/
}