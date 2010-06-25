package org.sgu.oecde.core;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Supervisor;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../applicationContext-security.xml"})
public class getUsers extends BasicTest{


//    @Ignore
    @Test
    public void getByName(){
        UserDetailsService uds = getBean("UserDetailsServiceImpl");
        System.out.println(uds.loadUserByUsername("shihovmy"));
    }

//    @Ignore
    @Test
    public void getAllA(){
        this.setDao("adminDao");
        List<Admin> l = this.<Admin>getAllItems();
        for(Admin t:l){
            System.out.println(t.getName());
        }
    }

    @Ignore
    @Test
    public void getAllSv(){
        this.setDao("supervisorDao");
        List<Supervisor> l = this.<Supervisor>getAllItems();
        for(Supervisor t:l){
            System.out.println(t.getDescription());
        }
    }


}
