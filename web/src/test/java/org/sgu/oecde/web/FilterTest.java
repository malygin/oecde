package org.sgu.oecde.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.io.*;
import java.net.*;
import org.sgu.oecde.web.jsfbeans.util.HTMLSanitiser;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/cwBeans.xml","../spring/testBeans.xml","../spring/deBeans.xml"})
public class FilterTest extends BasicTest{

    @Ignore
    @Test
    public void beans(){
        for(Object o:applicationContext.getBeanDefinitionNames()){
            System.out.println(o);
        }
    }

    @Ignore
    @Test
    public void groupCount(){
        setDao("estimateDao");
        List q = new ArrayList();
        q.add(new DeCurriculum(2009627542L));
        q.add(new DeCurriculum(2009634942L));
        q.add(new DeCurriculum(2009636442L));
        int t = this.<IEstimateDao>getDao().getEstimatedGroupsCount(q,new Teacher(44240L));
        System.out.println(t);
    }

 @Ignore
    @Test
    public void save(){
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f1 = (IResultFilter)getBean("controlWorkFilter");
        IResultFilter f2 = (IResultFilter)getBean("testFilter");
        IResultFilter f3 = (IResultFilter)getBean("estimateFilter");
        ResultPreFilter pf = (ResultPreFilter) applicationContext.getBean("preFilter");
        filters.add(f2);
        filters.add(f1);
        filters.add(f3);
        setDao("resultDao");
        List q = new LinkedList();
        List s = new LinkedList();
        s.add(new Student(320269L));
        q.add(new DeCurriculum(2009627542L));
        q.add(new DeCurriculum(2009634942L));
        q.add(new DeCurriculum(2009636442L));


//        q.add(new DeCurriculum(200947525L));
//        s.add(new Student(324607L));
//        s.add(new Student(324718L));
//        s.add(new Student(324744L));
//        s.add(new Student(324824L));
//        s.add(new Student(324748L));
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(q, s, null);
        Collections.sort(l);
        for(AbstractResult r:l){
            if(r instanceof TestAttempt && !((TestAttempt)r).getType().equals(TestAttemptType.trial))
                System.out.println(r.getStudent().getFio()+"    "+((TestAttempt)r).getWork().getTitle()+"   "+((TestAttempt)r).getPoints());
        }
        for(Points p:pf.forEachResult(l, true,filters,s,q)){
            System.out.println(p.<DeCurriculum>getCurriculum().getDiscipline().getName());
            System.out.println(p.<Student>getStudent().getFio());
            if(!CollectionUtils.isEmpty(p.getWorkPoints())){
                Map<IEstimate,Object> m = p.getWorkPoints();
                Iterator specsI = m.entrySet().iterator();
                while(specsI.hasNext()){
                   Map.Entry<IEstimate,Object> v = (Map.Entry)specsI.next();
                    System.out.println(v.getKey()+"   "+v.getValue());
                }
            }
            System.out.println(" ----------  "+p.getSum());
        }
    }

    @Ignore
    @Test
    public void getR(){
        setDao("resultDao");
        System.out.println(getItem(42L));
    }
    @Ignore
    @Test
    public void htmlbyurl() throws MalformedURLException, UnsupportedEncodingException, IOException{
      String str="";
      URL url = new URL("http://oecdo.sgu.ru/textbooks/avtomat_offise/test.html");
      StringBuffer strbuf = new StringBuffer();
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
      String str2;
      while ((str2 = in.readLine()) != null) {strbuf.append(str2);}
      str=strbuf.toString();
      System.out.println("" +str);
        System.out.println("ispr "+HTMLSanitiser.encodeInvalidMarkup(str));   

    }
      @Ignore
    @Test
    public void getResourses(){
        setDao("estimateDao");
        List q = new ArrayList();
        q.add(new DeCurriculum(2009627542L));
        q.add(new DeCurriculum(2009634942L));
        q.add(new DeCurriculum(2009636442L));
        int t = this.<IEstimateDao>getDao().getEstimatedGroupsCount(q,new Teacher(44240L));
        System.out.println(t);
    }

}
