package org.sgu.oecde.controlworks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.controlworks.dao.IControlWorkAttemptDao;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.springframework.test.context.ContextConfiguration;


/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/cwBeans.xml"})
public class AppTest extends BasicTest{
    
    @Ignore
    @Test
    public void save(){
        setDao("controlWorkDao");
        ControlWork w = getItem(3);
        Set s = null;
        if(w==null){
            w = new ControlWork();
            w.setCurriculum(new DeCurriculum(198326));
            w.setStudent(new Student(324725));
            s = new HashSet<ControlWorkAttempt>();
        }else{
            s = w.getCwAttempt();
        }
        ControlWorkAttempt a = new ControlWorkAttempt();
        s.add(a);
        w.setCwAttempt(s);
        a.setAttemptDate(DateConverter.convert(System.currentTimeMillis()));
        a.setWork(w);
        this.<IControlWorkDao>getDao().save(w);
    }

    @Ignore
    @Test
    public void get(){
        setDao("controlWorkDao");
        List l = new ArrayList();
        l.add(new DeCurriculum(198326));
        List<ControlWork>ls = this.<IControlWorkDao>getDao().getByStudentAndCurriculums( l, new Student(324725));
        for (ControlWork cw:ls){
            System.out.println(cw.getCwAttempt().size());
        }
    }

    @Ignore
    @Test
    public void getAttempts(){
        setDao("controlWorkAttemptDao");
        List l = new ArrayList();
        l.add(new DeCurriculum(198326));
        List l2 = new ArrayList();
        l2.add(new Student(324725));
        List<ControlWorkAttempt>ls = this.<IControlWorkAttemptDao>getDao().getAttemptsList(0,10,l2,l);
        System.out.println(ls);
        for (ControlWorkAttempt cw:ls){
            System.out.println(cw+"   "+cw.getWork());
        }
    }

//    @Ignore
    @Test
    public void getStudentCw(){
        ControlWorkService s = getBean("controlWorkService");
        List l = new ArrayList();
        l.add(new DeCurriculum(198326));
        l.add(new DeCurriculum(200327));
        List l2 = new ArrayList();
        l2.add(new Student(324725));
        System.out.println(s.getStudensControlWorks(new Student(324725), l));
    }
}
