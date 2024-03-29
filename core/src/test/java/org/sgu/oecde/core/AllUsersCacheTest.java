package org.sgu.oecde.core;

import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.CacheTest;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;

/**
 *
 * @author ShihovMY
 */
public class AllUsersCacheTest extends CacheTest{

    @Ignore
    @Test
    public void aput(){
//        IUpdateDao<Student>d = (IUpdateDao<Student>) applicationContext.getBean("studentDao");
        IUpdateDao<AbstractUser>d = (IUpdateDao<AbstractUser>) applicationContext.getBean("userDao");
        AbstractUser st = d.getById(324725L);
        AbstractUser st2 = d.getById(44240L);
        System.out.println(st.getOnline());
        uic.putUserInCache(st);
        uic.putUserInCache(st2);
    }
}
