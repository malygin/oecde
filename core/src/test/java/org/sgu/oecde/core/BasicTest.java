package org.sgu.oecde.core;

import java.util.List;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

@ContextConfiguration(locations={"../applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false,transactionManager="txManager")
@TestExecutionListeners({HibernateSessionRegistrationTestExecutionListener.class})

/**
 *
 * @author shihovmy
 */
public abstract class BasicTest extends AbstractJUnit4SpringContextTests{
    public IBasicDao dao;

    public BasicTest() {
    }

    public<T extends BasicItem> void setDao(String daoName){
       dao = (IBasicDao<T>) applicationContext.getBean(daoName);
    }

    public<T extends BasicItem> T getItem(int id){
       return (T) dao.getById(id);
    }

    public<T extends BasicItem> List<T> getAllItems(){
        return (List<T>) dao.getAll();
    }

    public<T extends BasicItem> List<T> getByExample(BasicItem item){
        return (List<T>) dao.getByExamlpeItem(item);
    }

    public <T extends IBasicDao> T getDao() {
        return (T) dao;
    }
}