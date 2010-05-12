///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package org.sgu.oecde.core;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.sgu.oecde.core.users.UsersInCache;
//import org.sgu.oecde.de.users.Student;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//
///**
// *
// * @author ShihovMY
// */
//@ContextConfiguration(locations={"../applicationContext.xml","../applicationContext-security.xml"})
//@TestExecutionListeners({HibernateSessionRegistrationTestExecutionListener.class})
//public class CacheTest extends AbstractJUnit4SpringContextTests{
//
//    UsersInCache uic;
//    @Ignore
//    @Before
//    public void setUpClass() throws Exception {
//        uic = (UsersInCache) applicationContext.getBean("userCache");
//    }
//   @Ignore
//    @Test
//    public void aput(){
//        Student st = new Student();
//        st.setName("student");
//        uic.putUserInCache(st);
//        for(String s:uic.getCache().getCacheManager().getCacheNames()){
//           System.out.println(s);
//        }
//    }
//
//    @Ignore
//    @Test
//    public void get(){
//        System.out.println(uic.getAllUsers(Student.class));
//    }
//}
