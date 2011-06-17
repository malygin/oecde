package org.sgu.oecde.core;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Supervisor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
//@ContextConfiguration(locations={"../applicationContext-security.xml"})
public class getUsers extends BasicTest{


    @Ignore
    @Test
    public void getByName(){
        UserDetailsService uds = getBean("UserDetailsServiceImpl");
        System.out.println(uds.loadUserByUsername("malyginav"));
    }

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
