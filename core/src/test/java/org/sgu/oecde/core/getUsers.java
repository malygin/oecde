package org.sgu.oecde.core;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
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
@ContextConfiguration(locations={"../applicationContext.xml","../applicationContext-security.xml"})
public class getUsers extends BasicTest{


    @Ignore
    @Test
    public void getByName(){
        UserDetailsService uds = getBean("UserDetailsServiceImpl");
        System.out.println(uds.loadUserByUsername("shihovmy"));
    }

//    @Ignore
    @Test
    public void getAllA(){
        this.setDao("adminDao");
        Admin a = new Admin();
        a.setUsername("shihovmy");
        List<Admin> l = getByExample(a);
        System.out.println(l.size());
    }

    @Ignore
    @Test
    public void getSpec(){
        this.setDao("specialityDao");
        List<Speciality> l = this.<Speciality>getAllItems();
        for(Speciality t:l){
            System.out.println(t.getName());
        }
    }
}
