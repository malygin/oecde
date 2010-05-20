package org.sgu.oecde.core;

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
 *
 */
public class ResourceTest extends BasicTest {

    @Ignore
    @Test
    public void updateImages(){
        setDao("imageDao");
       // int year = sg.getCalendarYear(0);
       Image img= new Image();
       img.setId(111);
       img.setUrl("34");
       img.setTitle("title");
       IResourceDao<Image> Dao = this.<IResourceDao>getDao();
       Dao.update(img);  
    }
    @Ignore
    @Test
    public void saveImages(){
        setDao("imageDao");
       // int year = sg.getCalendarYear(0);
       Image img= new Image();
       //img.setId(111);
       img.setUrl("url");
       img.setTitle("title2");
       img.setAccessResource(AccessResource.notPublic);
       IResourceDao<Image> Dao = this.<IResourceDao>getDao();
       Dao.insert(img);
     }
     //@Ignore
    @Test
    public void deleteImages(){
        setDao("imageDao");
       // int year = sg.getCalendarYear(0);
       Image img= new Image();
       img.setId(111);
       IResourceDao<Image> Dao = this.<IResourceDao>getDao();
       Dao.delete(img);
     //  System.out.println("ок!");
    }
}
