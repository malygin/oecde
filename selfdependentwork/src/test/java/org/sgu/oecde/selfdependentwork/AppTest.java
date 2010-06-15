package org.sgu.oecde.selfdependentwork;

import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.de.users.Student;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/cwBeans.xml"})
public class AppTest extends BasicTest{

    @Ignore
    @Test
    public void equal(){
        SelfDependentWork w = new SelfDependentWork();
        Curriculum c = new Curriculum();
        c.setId(2L);
        w.setCurriculum(c);
        w.setStudent(new Student(1L));
        w.setNumber(1);
        SelfDependentWork w2 = new SelfDependentWork();
        w2.setCurriculum(c);
        w2.setStudent(new Student(1L));
        w2.setNumber(1);
        assertTrue(w.equals(w2));
    }
}