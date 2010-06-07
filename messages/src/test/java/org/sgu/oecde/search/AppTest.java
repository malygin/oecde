package org.sgu.oecde.search;

import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.search.dao.ISearchDao;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/searchBeans.xml"})
public class AppTest  extends BasicTest{
    @Test
    public void search(){
        ISearchDao s = (ISearchDao) applicationContext.getBean("searchDao");
        System.out.println(s.search(Student.class, new String[]{"иванов"}));
    }
}
