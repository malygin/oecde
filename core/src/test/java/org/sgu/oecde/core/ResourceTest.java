package org.sgu.oecde.core;

import org.sgu.oecde.core.education.estimation.activity.Activity;
import java.util.ArrayList;
import org.sgu.oecde.core.education.Umk;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AccessResource;
import org.sgu.oecde.core.education.resource.Image;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 12.05.2010
 * проверка работоспособности ресурсов умк - пока не используется
 */
public class ResourceTest extends BasicTest {

    @Ignore
    @Test
    public void updateImages(){
        setDao("resourceDao");
       // int year = sg.getCalendarYear(0);
       Image img= new Image();
       img.setId(111L);
       img.setUrl("34");
       img.setTitle("title");
       IResourceDao<Image> Dao = this.<IResourceDao>getDao();
       Dao.update(img);       
       Image img2=Dao.getById(111L);
       assertEquals(img.getId(), img2.getId());
    }
    
    
     @Ignore
    @Test
    public void getUmkByPage(){
       setDao("umkDao");
       IBasicDao<Umk>umkDao = this.<IBasicDao>getDao();
       List<Umk> list= dao.getByPage(10, 1,"name");
       for (Umk u: list)
         System.out.println(""+u.getName());
    }

}
