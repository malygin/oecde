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
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SemesterGetter;
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
        ControlWork w = getItem(3L);
        Set s = null;
        if(w==null){
            w = new ControlWork();
            w.setCurriculum(new DeCurriculum(200847634L));
            w.setStudent(new Student(324725L));
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
        l.add(new DeCurriculum(198326L));
        List l2 = new ArrayList();
        l.add(new Student(324725L));
        List<ControlWork>ls = this.<IControlWorkDao>getDao().getByStudentsAnsCurriculums( l, l2,null);
        for (ControlWork cw:ls){
            System.out.println(cw.getCwAttempt().size());
        }
    }

    @Ignore
    @Test
    public void getAttempts(){
        setDao("controlWorkAttemptDao");
        List l = new ArrayList();
        l.add(new DeCurriculum(198326L));
        List l2 = new ArrayList();
        l2.add(new Student(324725L));
        List<ControlWorkAttempt>ls = this.<IControlWorkAttemptDao>getDao().getAttemptsList(0,10,l2,l);
        System.out.println(ls);
        for (ControlWorkAttempt cw:ls){
            System.out.println(cw+"   "+cw.getWork());
        }
    }

    @Ignore
    @Test
    public void getStudentCw(){
        ControlWorkService s = getBean("controlWorkService");
        List l = new ArrayList();
        l.add(new DeCurriculum(198326L));
        l.add(new DeCurriculum(200327L));
        List l2 = new ArrayList();
        l2.add(new Student(324725L));
        System.out.println(s.getStudensControlWorks(new Student(324725L), l));
    }

//    @Ignore
    @Test
    public void constants(){
        StringConstantsGetter g = getBean("cwDatesGetter");
//        g.save(new CalendarConstants(ControlWorkCalendarConstantName.controlWorksBeginDate, "10"), "ControlWorkCalendarConstants");
    }
}
