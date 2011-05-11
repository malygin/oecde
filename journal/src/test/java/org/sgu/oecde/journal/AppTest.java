package org.sgu.oecde.journal;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml","../spring/testBeans.xml"})
public class AppTest extends BasicTest{
    
    @Ignore
    @Test
    public void save(){
        EventType e = EventType.UMK_VIEW;
        System.out.println(e.isChosen());
        System.out.println(EventType.SYSTEM_LOGIN.isChosen());
         EventType e2 = EventType.SYSTEM_LOGIN;
         


    }

    @Ignore
    @Test
    public void doStudentEvents(){
        setDao("teacherDao");
        Teacher t = this.<Teacher>getItem(44240L);
        setDao("studentDao");
        Student s = this.<Student>getItem(324725L);
        setDao("curriculumDao");
        DeCurriculum c = this.<DeCurriculum>getItem(2009627542L);
        setDao("testDao");
        TestEntity e = this.<TestEntity>getItem(6738L);
        this.<JournalService>getBean("journalService").save(EventType.HAND_WRITTEN_CONTROL_WORK, t, c.getDiscipline(),s.getGroup().getPersons().iterator().next());
        this.<JournalService>getBean("journalService").save(EventType.GRADING, t, c.getDiscipline(),s.getGroup());
        this.<JournalService>getBean("journalService").save(EventType.TASK_HAS_BEEN_SEND_TO_PREP, s, c);
        this.<JournalService>getBean("journalService").save(EventType.TEST_END, s, c.getUmk(),e);
    }

    @Ignore
    @Test
    public void showStudentEvents(){
        setDao("studentDao");
        Student s = this.<Student>getItem(324725L);
        FilterType.studentEvents.setObject(s);
        setDao("journalDao");
        List<EventItem>l = this.<IJournalDao>getDao().getEvents(FilterType.studentEvents, 0);
        System.out.println(l.size());
        for(EventItem e:l){
            System.out.println(123123+"   "+e.getBody().length);
            for(EventBodyElement b:e.getBody()){
                System.out.println(b);
            }
        }
    }


    @Ignore
    @Test
    public void zowStudentEvents2(){
        FilterType.studentEvents.getArray();

    }
}