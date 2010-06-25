package org.sgu.oecde.web;

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
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/cwBeans.xml","../spring/testBeans.xml","../spring/journalBeans.xml","../spring/newsBeans.xml","../spring/discussionBeans.xml","../spring/searchBeans.xml","../spring/deBeans.xml"})
public class FilterTest extends BasicTest{

//    @Ignore
    @Test
    public void beans(){
        for(Object o:applicationContext.getBeanDefinitionNames()){
            System.out.println(o);
        }
    }

    @Ignore
    @Test
    public void save(){
        List<IResultFilter>filters = new LinkedList();
        IResultFilter f1 = (IResultFilter)getBean("cwFilter");
        IResultFilter f2 = (IResultFilter)getBean("testFilter");
        IResultFilter f3 = (IResultFilter)getBean("estimateFilter");
        ResultPreFilter pf = (ResultPreFilter) applicationContext.getBean("preFilter");
        filters.add(f2);
        filters.add(f1);
        filters.add(f3);
        setDao("resultDao");
        List q = new LinkedList();
        q.add(new DeCurriculum(200837533L));
        q.add(new DeCurriculum(2009633925L));
        q.add(new DeCurriculum(200957825L));
        q.add(new DeCurriculum(2009534225L));
        q.add(new DeCurriculum(2009518325L));
        List s = new LinkedList();
        s.add(new Student(321304L));
        List<AbstractResult> l = this.<IResultDao>getDao().getByStudentsAndCurriculums(q, s, null);
        System.out.println(l);
        for(Points p:pf.forEachResult(l, true,filters)){
            System.out.println(p.<DeCurriculum>getCurriculum().getDiscipline().getName());
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
}
