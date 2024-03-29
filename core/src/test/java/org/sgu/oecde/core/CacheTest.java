package org.sgu.oecde.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.HibernateSessionRegistrationTestExecutionListener;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UsersInCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 * проверка работы с кешем для пользователей он-лайн
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
@TestExecutionListeners({HibernateSessionRegistrationTestExecutionListener.class})
public class CacheTest extends AbstractJUnit4SpringContextTests{

    protected UsersInCache uic;
    
    @Before
    public void setUpClass() throws Exception {
        uic = (UsersInCache) applicationContext.getBean("usersInCache");
    }

    @Ignore
    @Test
    public void aputTeacherAndAdmin(){
        IUpdateDao<Teacher>t = (IUpdateDao<Teacher>) applicationContext.getBean("teacherDao");
        IUpdateDao<AbstractUser>d = (IUpdateDao<AbstractUser>) applicationContext.getBean("userDao");
        AbstractUser st = d.getById(1L);
        Teacher st2 = t.getById(44240L);
        uic.putUserInCache(st);
        uic.putUserInCache(st2);
        System.out.println(st.getOnline());
    }

    @Ignore
    @Test
    public void get(){
//        System.out.println(((net.sf.ehcache.Cache)applicationContext.getBean("teacherCache")).getKeys());
//        System.out.println(uic.getAdmins());
//        System.out.println("----");
//        System.out.println(uic.getTeachers());
//        System.out.println("----------");
        IUpdateDao<Teacher>t = (IUpdateDao<Teacher>) applicationContext.getBean("teacherDao");
        Teacher st = t.getById(44240L);
        st.setCellPhone(456456);
     //   System.out.println(st.getOnline()+"     "+st.isEnabled());
    }
    
    @Ignore
    @Test
    public void getUmkByPage(){
        System.out.println(((net.sf.ehcache.Cache)applicationContext.getBean("teacherCache")).getKeys());
        System.out.println(uic.getAdmins());
        System.out.println("----");
        System.out.println(uic.getTeachers());
        System.out.println("----------");
        IUpdateDao<Teacher>t = (IUpdateDao<Teacher>) applicationContext.getBean("teacherDao");
        Teacher st = t.getById(44240L);
        st.setCellPhone(456456);
        System.out.println(st.getOnline()+"     "+st.isEnabled());
    }
}
