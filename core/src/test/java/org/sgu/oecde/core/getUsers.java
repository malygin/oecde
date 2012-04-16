package org.sgu.oecde.core;

import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;

/**
 *
 * @author ShihovMY
 */
//@ContextConfiguration(locations={"../applicationContext-security.xml"})
public class getUsers extends BasicTest{




//     @Ignore
    @Test
    public void getByName2(){
        IBasicDao<AbstractUser> userDao  = getBean("userDao");
        AbstractUser example = AbstractUser.getUserWithName("malyginav");
        List<AbstractUser> l = userDao.getByExample(example);
        assertTrue(11L==((Admin)l.get(0)).getId());
    }

   //@Ignore
    @Test
    public void getAllA(){
        this.setDao("adminDao");
        Admin a = new Admin();
        a.setUsername("shihovmy");
        List<Admin> l = getByExample(a);
        System.out.println(l.size());
    }

  
}
