package org.sgu.oecde.core;

import java.util.List;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author shihovmy
 * супер класс для тестов
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
public abstract class BasicTest extends AbstractJUnit4SpringContextTests{
    public IBasicDao dao;

    public BasicTest() {
    }

    public<T extends BasicItem> void setDao(String daoName){
       dao = (IBasicDao<T>) applicationContext.getBean(daoName);
    }
    
    public<T extends BasicItem> T getItem(Long id){
       return (T) dao.getById(id);
    }

    public<T extends BasicItem> List<T> getAllItems(){
        return (List<T>) dao.getAll();
    }

    public<T extends BasicItem> List<T> getByExample(BasicItem item){
        return (List<T>) dao.getByExample(item);
    }

    public <T extends IBasicDao> T getDao() {
        return (T) dao;
    }

    public <T extends Object>T getBean(String name){
        return (T) applicationContext.getBean(name);
    }
}