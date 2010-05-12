package org.sgu.oecde.core;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.resourse.Image;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;
/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 12.05.2010
 *
 */
public class ResourseTest extends BasicTest {

   // @Ignore
    @Test
    public void saveImages(){
        setDao("imageDao");
       // int year = sg.getCalendarYear(0);
       Image img= new Image();
       img.setUrl("34");
       IUpdateDao<Image> Dao = this.<IUpdateDao>getDao();
       Dao.update(img);
        System.out.println("ок!");
    
    }
}
