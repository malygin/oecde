package org.sgu.oecde.search;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.search.dao.ISearchDao;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
public class AppTest  extends BasicTest{
   
    @Ignore
    @Test
    public void search(){
        ISearchDao s = (ISearchDao) applicationContext.getBean("searchDao");
        List<Teacher>ts = s.search(SearchType.teacher, new String[]{"иванов","валер"});
        for(Teacher t:ts){
            System.out.println(t.getFio());
        }
    }
}
