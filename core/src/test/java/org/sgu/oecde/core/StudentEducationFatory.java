/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.sgu.oecde.core.users.StudentGroup;
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
public class StudentEducationFatory extends BasicTest{

    private SemesterGetter sg;
    public StudentEducationFatory() {
    }

    @Before
    public void setUpClass() {
        sg = (SemesterGetter) applicationContext.getBean("semesterGetter");
    }

    public void getTestsResultsByDisc() {
//        testDao.getTestsReports(StudentI, null, disc, getSemestrByYear(),BY_KOD_AND_DISC,false);
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
        c.setSelected(true);
        c.setCalendarYear(2009);
        c.setSemester(sg.getSemesterByStudentYear(st, 0));
        c.setSpeciality(st.<Group>getGroup().getSpeciality());
        return c;
    }

    private DeCurriculum fillCurriculum(Discipline d){
        DeCurriculum c = fillCurriculum();
        c.setDiscipline(d);
        return c;
    }

//    @Ignore
    @Test
    public void getDisciplineForStudent() {
        Discipline d = new Discipline(15);
        DeCurriculum c = fillCurriculum(d);
        setDao("curriculumDao");
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(c);
        assertFalse(l.isEmpty());
        System.out.println(l.size());
        for(DeCurriculum dc:l){
            System.out.println(dc.getUmk());
        }
    }
/*
     public HashMap<DisciplineItem, List<StudentItem>> getControlWorkInfo4Group()  {
        HashMap studentsAndCws = null;
        List<StudentItem> studentsList = getStudentsList();
        List<DisciplineItem> list = discipline.getDiscsForGrades(StudentI.getGr(), getSemestrByYear(),getCurrentYear(), StudentI.getSpec().getId());
        if(!studentsList.isEmpty()&&list!=null&&!list.isEmpty()){
            studentsAndCws = studentDao.getControlWorkInfo4Group(studentsList,list, getSemestrByYear(),getCurrentYear(),StudentI.getSpec().getId());
        }
        studentsAndCws;
    }

     public HashMap<TestItem, List<TestItem>> getTestsResultsByKod() {
         testDao.getTestsReports(StudentI, null, null, getSemestrByYear(),getIntSemestr()==0?BY_KOD:RETESTS_BY_KOD,false);
    }
*/
    public void getStudentGrades() {
        Student st = getSt();
        DeCurriculum c = fillCurriculum();
        Points p = new Points();
        //PointsBuilder pF = new PointsBuilder(p);
        Set<IResultFilter> fs = (Set<IResultFilter>)applicationContext.getBean("resultFilters");
       // List<IRegularResult> res = new ArrayList<IRegularResult>();
       // pF.filterRegularResults(res, fs);
        p.setStudent(st);
        p.setCurriculum(c);
        setDao("curriculumDao");
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(c);
        setDao("estimateDao");
        IEstimateDao estDao = getDao();
        estDao.getByParametersCollection(l, "curriculum");
    }

    public void getGrades(){
        setDao("curriculumDao");
        DeCurriculum c = fillCurriculum();
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(c);
        Set<IResultFilter> fs = (Set<IResultFilter>)applicationContext.getBean("resultFilters");
        setDao("resultsDao");
       // BasicResult r = new RegularResult();
       // r.setStudent(getSt());
        IAdvancedDao<Umk> resDao = getDao();
        //List<RegularResult> bs = this.<RegularResult>getByExample(r);
        Set<Points> point = new HashSet<Points>();
       // PointsBuilder pf = new PointsBuilder();
        for(DeCurriculum dc:l){
      //      point.add(pf.createPoints().filterRegularResults(bs, fs).setCurriculum(dc).getPoints());
        }
    }

    public void getOldGrades()   {
        Student st = getSt();
        setDao("groupDao");
        Group oldGr = new Group();
        Set stud = new HashSet();
        stud.add(st);
        oldGr.setYear(3);
        oldGr.setPersons(stud);
        oldGr = this.<Group>getByExample(oldGr).get(0);
        sg = (SemesterGetter) applicationContext.getBean("semesterGetter");
        DeCurriculum c = new DeCurriculum();
        c.setSpeciality(oldGr.getSpeciality());
        c.setSemester(5);
        c.setCalendarYear(sg.getCalendarYear(st, 5));
        setDao("curriculumDao");
        List<DeCurriculum> list = this.<DeCurriculum>getByExample(c);
        setDao("estimateDao");
        IEstimateDao estDao = getDao();
        estDao.getByParametersCollection(list, "curriculum");
    }
/*
    public void getLowGradedDisciplines() {
        List list = getStudentGrades();
        Iterator<DisciplineItem> i = list.iterator();
        while(i.hasNext()){
            DisciplineItem disciplineItem =  i.next();
            if(disciplineItem.getRating()!=null
               &&disciplineItem.getRating().getSum()>175
               &&disciplineItem.getRating().getGrade()!=null
               &&!"0".equals(disciplineItem.getRating().getGrade())
               &&!"2".equals(disciplineItem.getRating().getGrade())
               &&disciplineItem.getRating().getVisits()>0
               ){
               i.remove();
            }
        }
        list;
    }
*/
    @Ignore
    @Test
    public void getStudentsDisciplines(){
        DeCurriculum cr = fillCurriculum();
        setDao("curriculumDao");
        List<DeCurriculum> l = this.<DeCurriculum>getByExample(cr);
        for(DeCurriculum c:l){
            System.out.println(c.getUmk().getName());
        }
       /* if(!list.isEmpty()){
             testDao.getTestsReports(StudentI, list, disc, getSemestrByYear(),BY_KOD,true);
             studentDao.getStudentGrades(StudentI.getId(), getSemestrByYear(),list);
             testDao.getTestsReports(StudentI, list, disc, getSemestrByYear(), TESTS_ATTEMPTS_AND_COUNT, false);
        }
        discipline.setTestsNumber(StudentI.getSpec().getId(), getSemestrByYear(),getCurrentYear(),list);*/
    }
/*
    public void getGroupRating() {
        List<StudentItem> studentsList = getStudentsList();
        if(!studentsList.isEmpty()){
           testDao.getTestsReports(StudentI,studentsList, null, getSemestrByYear(),BY_ALL_DISCS,true);

           studentDao.getPointsForRating(studentsList,getSemestrByYear());
        }
        studentsList;
    }
*/
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
        DeCurriculum c = this.<DeCurriculum>getItem(100337);
        c.getUmk();
        //assertTrue(list.contains(umk.<DeCurriculum>getCurriculum()));

    }
/*
    private void getDisciplinesList()  {
        if(super.getYear()==getCurrentYear())
            discipline.getDiscsForGrades(StudentI.getGr(), getSemestrByYear(),super.getYear(), StudentI.getSpec().getId());
        else
            discipline.getDiscsForOldGrades(StudentI.getId(), getSemestrByYear(),super.getYear(), StudentI.getSpec().getId());
    }*/
}