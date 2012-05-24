package org.sgu.oecde.search;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.search.dao.ISearchDao;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
public class AppTest  extends BasicTest{
   
    //@Ignore
    @Test
    public void search(){
        ISearchDao s = (ISearchDao) applicationContext.getBean("searchDao");
        SearchFiltersFields filter = SearchFiltersFields.message;
        String[] str = filter.getAvailbaleFields();
        for(String sr: str){
            System.out.println(""+sr);
        }
        IBasicDao<AbstractUser> userDao  = getBean("userDao");
        AbstractUser example = AbstractUser.getUserWithName("malyginav");
        List<AbstractUser> l = userDao.getByExample(example);
        //System.out.println(""+l.get(0));
        Admin admin=(Admin) l.get(0);
        filter.setSelectedFileds(new String[]{"theme", "fulltext"});
        System.out.println(filter.getSelectedFileds());
         List<Message>ts = s.search(filter, new String[]{"привет","тест"}, admin, true);
        for(Message t:ts){
            System.out.println(t.getTheme());
        }
    }
}
