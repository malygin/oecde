/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import org.sgu.oecde.de.users.Student;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../applicationContext-security.xml"})
@TestExecutionListeners({HibernateSessionRegistrationTestExecutionListener.class})
public class CacheTest extends AbstractJUnit4SpringContextTests{

    UsersInCache uic;
    
    @Ignore
    @Before
    public void setUpClass() throws Exception {
        uic = (UsersInCache) applicationContext.getBean("userCache");
    }

//    @Ignore
    @Test
    public void aput(){
//        IUpdateDao<Student>d = (IUpdateDao<Student>) applicationContext.getBean("studentDao");
        IUpdateDao<AbstractUser>d = (IUpdateDao<AbstractUser>) applicationContext.getBean("userDao");
        AbstractUser st = d.getById(324725L);
        AbstractUser st2 = d.getById(44240L);
        System.out.println(st.isOnline());
        uic.putUserInCache(st);
        uic.putUserInCache(st2);
    }

//    @Ignore
    @Test
    public void get(){
        System.out.println(uic.getStudents());
        System.out.println(uic.getTeachers());
        IUpdateDao<AbstractUser>d = (IUpdateDao<AbstractUser>) applicationContext.getBean("userDao");
        System.out.println(d.getById(44240L).isOnline());

    }
}
