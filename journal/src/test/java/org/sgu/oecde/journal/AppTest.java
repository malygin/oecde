package org.sgu.oecde.journal;

import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.filter.AdminFilter;
import org.sgu.oecde.journal.filter.StudentFilter;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/journalBeans.xml","../spring/testBeans.xml"})
public class AppTest extends BasicTest{
    
    @Ignore
    @Test
    public void save(){
        setDao("userDao");
        AbstractUser user = this.<AbstractUser>getItem(324725);
        Journal journal = (Journal) applicationContext.getBean("journalServise");
        journal.logTestGrading(user, 1, 2748);
    }

    @Ignore
    @Test
    public void zget(){
        AdminFilter f = (AdminFilter) applicationContext.getBean("adminFilter");
        setDao("userDao");
        AbstractUser user = this.<AbstractUser>getItem(324725);
        f.setUserItem(user);
        Journal journal = (Journal) applicationContext.getBean("journalServise");
        System.out.println(journal.getEvents(f));
    }
}
