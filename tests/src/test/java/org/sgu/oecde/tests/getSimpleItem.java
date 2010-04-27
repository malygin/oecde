/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPoints;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.users.AbstractGroup;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.dao.ITestAttemptDao;
import org.sgu.oecde.tests.dao.ITestDao;
import org.sgu.oecde.tests.dao.TestAttemptDao;
import org.sgu.oecde.tests.dao.TestDao;
import org.sgu.oecde.tests.filters.Filter;
import org.sgu.oecde.tests.filters.PreFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.CollectionUtils;
import static org.junit.Assert.*;
/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/testDaoBeans.xml"})
public class getSimpleItem extends BasicTest{

    @Ignore
    @Test
    public void getT(){
        setDao("testDao");
        System.out.println(getItem(1));
    }

    @Ignore
    @Test
    public void getF(){
        setDao("testDao");
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
        this.<ITestDao>getDao().update(t);
    }

    @Ignore
    @Test
    public void getA(){
        setDao("testDao");
        TestEntity t = getItem(2);
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        Student st = new Student(324725);
        DeCurriculum c = new DeCurriculum();
        c.setId(213305);
        a.setCurriculum(c);
        a.setStudent(st);
        Random r = new Random();
        a.setPoints(r.nextInt(100));
        a.setWork(t);
        a.setDate("26.04.2010");
        a.setType(TestAttemptType.regular);
        this.<ITestAttemptDao>getDao().saveAttempt(a);
    }

//    @Ignore
    @Test
    public void basd(){
        PreFilter pf = new PreFilter();
        Filter f = (Filter) applicationContext.getBean("filter");
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        DeCurriculum c = new DeCurriculum();
        c.setId(205326);
        a.setCurriculum(c);
//        List<TestAttempt> l = this.<TestAttempt>getByExample(a);
        List<TestAttempt> l = this.<TestAttempt>getAllItems();
        List<Points> ps = (pf.forEachResult(l, f));
        for(Points p:ps){
            if(!CollectionUtils.isEmpty(p.getWorkPoints()))
                for(EstimatedWorkPoints wp:p.getWorkPoints()){
                    System.out.println(p.getCurriculum()+"    "+wp.getName()+"   "+wp.getPoints()+"  "+p.getSum());
                }
        }
    }

//    @Ignore
    @Test
    public void ata(){
        setDao("testAttemptDao");
        TestAttempt a = new TestAttempt();
        DeCurriculum c = new DeCurriculum();
        c.setId(205326);
        a.setCurriculum(c);
        List<TestAttempt> l = this.<TestAttempt>getByExample(a);
//        List<TestAttempt> l = this.<TestAttempt>getAllItems();
        Collections.sort(l,new Comparator<TestAttempt>() {

            @Override
            public int compare(TestAttempt o1, TestAttempt o2) {
                return Integer.valueOf(o1.getWork().getId()).compareTo(o2.getWork().getId());
            }
        });
        for(TestAttempt ta:l){
            System.out.println(ta.getWork().getId()+"  "+ta.getPoints()+"  "+ta.getType().toString());
        }
    }
}
