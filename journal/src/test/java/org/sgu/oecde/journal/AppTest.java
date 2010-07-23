package org.sgu.oecde.journal;

import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.filter.AdminFilter;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class AppTest extends BasicTest{
    
    @Ignore
    @Test
    public void save(){
        setDao("userDao");
        AbstractUser user = this.<AbstractUser>getItem(324725L);
        Journal journal = (Journal) getBean("journalServise");
        journal.logTestGrading(user, 1L, 2748L);
    }

//    @Ignore
    @Test
    public void zget(){
        AdminFilter f = (AdminFilter) getBean("adminFilter");
        setDao("userDao");
        AbstractUser user = this.<AbstractUser>getItem(324725L);
        f.setUserItem(user);
        Journal journal = (Journal) getBean("journalServise");
        System.out.println(journal.getEvents(f));
    }
}