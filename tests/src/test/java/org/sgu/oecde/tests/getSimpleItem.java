package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.users.AbstractGroup;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.tests.dao.ITestAttemptDao;
import org.sgu.oecde.tests.estimation.TestFilter;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.dao.TestAttemptDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.CollectionUtils;
import static org.junit.Assert.*;
/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/testBeans.xml","../spring/deBeans.xml"})
public class getSimpleItem extends BasicTest{

    @Ignore
    @Test
    public void getT(){
        setDao("resourceDao");
        System.out.println(getItem(1L));
    }

    @Ignore
    @Test
    public void getF(){
        setDao("resourceDao");
        TestEntity t = new TestEntity();
        t.setEstimation(TestEstimationType.max);
        t.setQuantity(0);
        t.setWritable(true);
        t.setWeight(0);
        t.setType(TestType.trial);
        t.setTrialNumber(0);
        t.setTitle("1234234");
        t.setShuffle(true);
        t.setEstimateAttemptsNumber(0);
        t.setDuration(0);
        this.<IResourceDao>getDao().update(t);
    }

    @Ignore
    @Test
    public void getA(){
        setDao("resourceDao");
        TestEntity t = getItem(2L);
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        Student st = new Student(324725L);
        DeCurriculum c = new DeCurriculum();
        c.setId(213305L);
        a.setCurriculum(c);
        a.setStudent(st);
        Random r = new Random();
        a.setPoints(r.nextInt(100));
        a.setWork(t);
        a.setDate("26.04.2010");
        a.setType(TestAttemptType.regular);
        this.<ITestAttemptDao>getDao().saveAttempt(a);
    }

    @Ignore
    @Test
    public void filtResults(){
        List<IResultFilter>filters = new LinkedList();
        ResultPreFilter pf = (ResultPreFilter) applicationContext.getBean("preFilter");
        TestFilter f = (TestFilter) applicationContext.getBean("testFilter");
        filters.add(f);
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        setDao("testAttemptDao");
        List q = new LinkedList();
        q.add(new DeCurriculum(200837533L));
        q.add(new DeCurriculum(2009633925L));
        q.add(new DeCurriculum(200957825L));
        q.add(new DeCurriculum(2009534225L));
        q.add(new DeCurriculum(2009518325L));
        List s = new LinkedList();
        s.add(new Student(321304L));
        List<TestAttempt> l = this.<ITestAttemptDao>getDao().getByStudentsAndCurriculums(q, s, null);
        Collections.sort(l);
        l.remove(0);
        for(TestAttempt ta:l){
            System.out.println(ta.getCurriculum().getId()+"  "+ta.getStudent().getId()+"  "+ta.getWork().getId()+"   "+ta.getId()+"  "+ta.getDate()+"  "+ta.<DeCurriculum>getCurriculum().getDiscipline().getName()+"   "+ta.getPoints()+"  "+ta.<TestEntity>getWork().getType()+"   "+ta.getType());
        }
        List<Points> ps = pf.forEachResult(l,true,filters,s,q);
        for(Points p:ps){
            if(!CollectionUtils.isEmpty(p.getWorkPoints())){
                Map<IEstimate,Object> m = p.getWorkPoints();
                Iterator specsI = m.entrySet().iterator();
                while(specsI.hasNext()){
                   Map.Entry<IEstimate,Object> specsMap = (Map.Entry)specsI.next();
                   System.out.println(p.<DeCurriculum>getCurriculum().getDiscipline().getName()/*p.getCurriculum().getId()*/+"    "+specsMap.getKey()+"   "+specsMap.getValue()+"  "+p.getSum());
                }
            }
        }
    }
    
    @Ignore
    @Test
    public void getByExampleWithType(){
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        DeCurriculum c = new DeCurriculum();
        c.setId(213305L);
        a.setCurriculum(c);
//        List<TestAttempt> l = this.<ITestAttemptDao<TestAttempt>>getDao().getByExampleWithType(a, true);
        List<TestAttempt> l = this.<TestAttempt>getAllItems();
        Collections.sort(l);
  /*      Collections.sort(l,rcnew Comparator<TestAttempt>() {

            @Override
            public int compare(TestAttempt o1, TestAttempt o2) {
                return Integer.valueOf(o1.getWork().getId()).compareTo(o2.getWork().getId());
            }
        });*/
        for(TestAttempt ta:l){
            System.out.println(ta.<DeCurriculum>getCurriculum().getDiscipline().getName()+"  "+ta.getWork().getId()+"    "+ta.getDate()+"  "+ta.getPoints()+"  "+ta.getType().toString()+"  "+ta.<TestEntity>getWork().getType());
        }
    }

    @Ignore
    @Test
    public void getCurriculumAttempts(){
        TestAttemptService serv = (TestAttemptService) applicationContext.getBean("testAttemptService");
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        Student s = new Student(324725L);
        List<Student> sts = new ArrayList(1);
        sts.add(s);
        DeCurriculum c = new DeCurriculum();
        c.setId(205326L);
        a.setCurriculum(c);
        for(AdditionalSelfDependentWork w:serv.getCurriculumAttempts(c,  sts)){
            for(TestAttempt r:w.<TestAttempt>getResults()){
                System.out.println(r.getId()+"   "+r.getPoints()+"  "+r.getType());
            }
            //System.out.println(w.getWork()+"   "+w.getPointsForWork());
        }
    }

    @Ignore
    @Test
    public void getStudentsAttempts(){
        TestAttemptService serv = getBean("testAttemptService");
        TestAttempt a = new TestAttempt();
        Student s = new Student(324725L);
        DeCurriculum c = new DeCurriculum();
        List<DeCurriculum> sts = new ArrayList();
        c.setId(205326L);
        sts.add(c);
        c = new DeCurriculum();
        c.setId(198326L);
        sts.add(c);
        c = new DeCurriculum();
        c.setId(201327L);
        sts.add(c);
        c = new DeCurriculum();
        c.setId(213305L);
        sts.add(c);
        for(AdditionalSelfDependentWork w:serv.getStudentAttempts(sts, s,false)){
//        for(AdditionalSelfDependentWork w:serv.getStudentSingleCurriculumTestsWithAttempts(c, null, s)){
            System.out.println(w.<DeCurriculum>getCurriculum().getDiscipline().getName());
            System.out.println(w.getWork()+"   "+w.getPointsForWork()+" ");
            for(TestAttempt ta:w.<TestAttempt>getResults()){
                System.out.println("   "+ta.getType()+"    "+ta.getPoints());
            }
        }
    }

    @Ignore
    @Test
    public void testsByCur(){
        setDao("curriculumDao");
        setDao("resourceDao");
        DeCurriculum c = new DeCurriculum();
        c.setId(213305L);
        List<DeCurriculum> sts = new ArrayList(1);
        sts.add(c);
        System.out.println(this.<IResourceDao<TestEntity>>getDao().getResourceByCurriculums(sts,null, TestEntity.class));
//        List<TestEntity>tests = this.<ITestDao<TestEntity>>getDao().getByCurriculums(sts, null);
//
//        for(TestEntity t:tests){
//            System.out.println(t);
//        }
    }

    @Ignore
    @Test
    public void save(){
        setDao("resourceDao");
        TestEntity te = this.<TestEntity>getItem(1L);
        TestAttempt a = new TestAttempt();
        a.setDate(DateConverter.currentDate());
        a.setCurriculum(new DeCurriculum(205326L));
        a.setStudent(new Student(324725L));
        a.setDuration(1);
        a.setPoints(1);
        a.setQuantity(1);
        a.setRightAnswers(1);
        a.setWork(te);
        a.setType(TestAttemptType.regular);
        Set<AnsweredQuestion> set = new HashSet<AnsweredQuestion>();
        for(Question q:te.getQuestions()){
            AnsweredQuestion aq = new AnsweredQuestion();
            aq.setAttempt(a);
            aq.setQuestion(q);
            aq.setResultPoints(1);
            aq.setRight(true);
            set.add(aq);
            a.setAnsweredQuestions(set);
            for(Answer an:q.getAnswers()){
                Set<GivenAnswer> set2 = new HashSet<GivenAnswer>();
                GivenAnswer ga = new GivenAnswer();
                ga.setAnsweredQuestion(aq);
                ga.setGivenAnswer("1");
                ga.setRightAnswer(an);
                set2.add(ga);
                aq.setGivenAnswers(set2);
            }
        }
        setDao("testAttemptDao");
        this.<ITestAttemptDao>getDao().saveAttempt(a);
    }

    @Ignore
    @Test
    public void constants(){
        StringConstantsGetter g = getBean("testsDatesGetter");
//        g.save(new CalendarConstants(ControlWorkCalendarConstantName.controlWorksBeginDate, "10"), "ControlWorkCalendarConstants");
    }

//    @Ignore
    @Test
    public void getStudentsDisciplines(){
        setDao("studentDao");
        Student st = getItem(320269L);
        List<DeCurriculum> q = new ArrayList<DeCurriculum>();
        q.add(new DeCurriculum(2009636442L));
        q.add(new DeCurriculum(2009627542L));
        q.add(new DeCurriculum(2009634942L));
        setDao("testAttemptDao");
//        List<Student> s = new ArrayList<Student>(st.getGroup().getPersons());
        
        List<AdditionalCurriculum>ac = this.<TestAttemptService>getBean("testAttemptService").getStudentAttemptsCount(q,  st);
        for(AdditionalCurriculum a:ac){
            System.out.println(a.getCurriculum());
            System.out.println("pass  "+a.getPassedTests());
        }
    }
}
