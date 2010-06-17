package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.de.users.Teacher;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
public class StudentEducationFatory extends BasicTest{

    private SemesterGetter sg;
    private TestAttemptService s;
    private ControlWorkService cw;
    private DeCurriculumBuilder dcb;
    private ResultPreFilter pf;

    public StudentEducationFatory() {
    }

    @Before
    public void setUpClass() {
        sg =  getBean("semesterGetter");
        cw = getBean("controlWorkService");
        s = getBean("testAttemptService");
        dcb = getBean("curriculumBuilder");
        pf = getBean("preFilter");
    }

//    @Ignore
    @Test
    public void getDisciplineForStudent() {
        setDao("curriculumDao");
        getItem(1L);
    }

    @Ignore
    @Test
    public void getTestsResultsByDisc() {
        DeCurriculum c = fillCurriculum();
        System.out.println(s.getStudentsSingleCurriculumAttempts(c, null, new Student(324725L)));
    }
/*
    @Ignore
    @Test
    public void getControlWorkInfo4Group() {
        ControlWorkService s = getBean("controlWorkService");
        s.
        HashMap studentsAndCws = null;
        List<StudentItem> studentsList = getStudentsList();
        List<DisciplineItem> list = discipline.getDiscsForGrades(StudentI.getGr(), getSemestrByYear(),getCurrentYear(), StudentI.getSpec().getId());
        if(!studentsList.isEmpty()&&list!=null&&!list.isEmpty()){
            studentsAndCws = studentDao.getControlWorkInfo4Group(studentsList,list, getSemestrByYear(),getCurrentYear(),StudentI.getSpec().getId());
        }
        studentsAndCws;
    }
*/
    public void getTestsResultsByKod() {
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(c);
        Student st = getSt();
        s.getStudentAttempts(l, null, st);
    }

    public void getStudentGrades() {
        getGrades();
    }

    public void getOldGrades(){
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f3 = (IResultFilter)getBean("estimateFilter");
        filters.add(f3);
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> cr = this.<ICurriculumDao>getDao().getByExample(c);
        List<Student>stl = new ArrayList<Student>();
        Student st = getSt();
        stl.add(st);
        Estimate e = new Estimate();
        e.setStudent(st);
        e.setCurriculum(c);
        setDao("estimateDao");
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(cr, stl, null);
        pf.forEachResult(l, true,filters);
    }

    public void getLowGradedDisciplines() {
        List<Points> lp = getGrades();
        Iterator<Points> i = lp.iterator();
        while(i.hasNext()){
            Points disciplineItem = i.next();
            boolean estimated = true;
            if(disciplineItem.getWorkPoints().containsKey(EstimateNames.estimate)){
                PointToEstimate pe = disciplineItem.<PointToEstimate>getWorkPoints().get(EstimateNames.estimate);
                estimated = PointToEstimate.five.equals(pe)
                        ||PointToEstimate.four.equals(pe)
                        ||PointToEstimate.three.equals(pe)
                        ||PointToEstimate.passed.equals(pe);
            }
            if(disciplineItem.getSum()>175&&estimated){
               i.remove();
            }
        }
    }

    @Ignore
    @Test
    public void getStudentsDisciplines(){
        DeCurriculum cr = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(cr);
        for(DeCurriculum c:l){
            System.out.println(c.getUmk().getName());
        }
        List<AdditionalCurriculum>ac = s.getStudentAttemptsCount(l, null, getSt());
        for(Points p:getGrades()){
            ac.get(ac.indexOf(p.getCurriculum())).setPoints(p);
        }
    }

    public void getGroupRating() {
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f1 = (IResultFilter)getBean("cwFilter");
        IResultFilter f2 = (IResultFilter)getBean("testFilter");
        filters.add(f2);
        filters.add(f1);
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> cr = this.<ICurriculumDao>getDao().getByExample(c);
        List<Student> stl = new ArrayList(getSt().getGroup().getPersons());
        setDao("resultDao");
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(cr, stl, null);
        pf.forEachResult(l, false,filters);
    }

    @Ignore
    @Test
    public void getTeachersList(){
        Student st = getSt();
        setDao("studentDao");        
        st = this.<Student>getByExample(st).get(0);
        setDao("curriculumDao");
        System.out.println(st.getId()+"  "+st.getGroup().getName());
//        List<Teacher> tl = this.<ICurriculumDao>getDao().<Teacher>getTeachersByGroup(sg.getSemestersByInt(0), sg.getCalendarYear(0), st.<Group>getGroup());
//        for (Teacher t:tl){
//            System.out.println(t.getSurname());
//        }
   }

    public void isGotCourseWork(){
        DeCurriculum c = fillCurriculum();
        boolean got=false;
        setDao("curriculumDao");
        List<DeCurriculum> list =  this.<DeCurriculum>getByExample(c);
        for(DeCurriculum s:list){
            if(s.getTermPapersNumber()>0){
                got=true;
                break;
            }
        }
        assertTrue(got);
    }

    public void getUmk(){
        setDao("curriculumDao");
        DeCurriculum c = this.<DeCurriculum>getItem(100337L);
        c.getUmk();
        //assertTrue(list.contains(umk.<DeCurriculum>getCurriculum()));

    }


    public void getStudentControlWorks(){
        studentControlWorks();
    }

    public void getLowGradedControlWorks(){
        Map<DeCurriculum,ControlWork> m = studentControlWorks();
        Iterator specsI = m.entrySet().iterator();
        while(specsI.hasNext()){
            Map.Entry<DeCurriculum,ControlWork> v = (Map.Entry)specsI.next();
            ControlWork w = v.getValue();
            if(w!=null)
                if(w.getCwAttempt()!=null&&w.getCwAttempt().size() > 0&&w.getProgress().equals(ControlWorkProgress.passed)){
                    specsI.remove();
                }
        }
    }

    public void getBadPassedTests(){
        DeCurriculum cr = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> cl = this.<DeCurriculum>getByExample(cr);
        List<AdditionalSelfDependentWork>l = s.getStudentsAllCurriculumAttempts(cl, null, getSt(),false);
        Iterator<AdditionalSelfDependentWork> i = l.iterator();
        while(i.hasNext()){
            AdditionalSelfDependentWork passedTest = i.next();
            if(passedTest.getPointsForWork()>30)
                i.remove();
        }
    }

    public void getShedule(){
        getDisciplinesList();
    }

    private List<DeCurriculum> getDisciplinesList()  {
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        return getByExample(c);
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
        return dcb.getInstance(2009, 0, st);
    }

    private List<Points>getGrades(){
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f1 = getBean("cwFilter");
        IResultFilter f2 = getBean("testFilter");
        IResultFilter f3 = getBean("estimateFilter");
        filters.add(f2);
        filters.add(f1);
        filters.add(f3);
        DeCurriculum c = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> cr = this.<ICurriculumDao>getDao().getByExample(c);
        List<Student>stl = new ArrayList<Student>();
        Student st = getSt();
        stl.add(st);
        setDao("resultDao");
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(cr, stl, null);
        return pf.forEachResult(l, true,filters);
    }

    private Map<DeCurriculum,ControlWork> studentControlWorks(){
        List c = cw.getCurriculumsWithControlWorks(fillCurriculum());
        return  cw.getStudensControlWorks(getSt(), c);
    }

}