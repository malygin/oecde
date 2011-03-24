package org.sgu.oecde.web.jsfbeans.admin;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.tests.TestType;
import org.sgu.oecde.web.jsfbeans.tests.TestPassingBean;

/**
 *
 * @author ShihovMY
 * бин для работы с тестами у админа
 */
@ManagedBean(name="adminTestBean")
@ViewScoped
public class AdminTestBean implements Serializable{

    private TestEntity test;

    private Long id;
    private String action;
    private boolean renderTestPassing=true;

    @ManagedProperty(value="#{testDao}")
    private IUpdateDao<TestEntity>testDao;

    @ManagedProperty(value="#{testPassingBean}")
    private TestPassingBean testPassingBean;


    private static final long serialVersionUID = 192L;

    public String save(){
        testDao.update(test);
        return "testEdit.xhtml.xhtml?faces-redirect=true&id="+id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        test = testDao.getById(id);
        this.id = id;     
    
    }

    public  void startTest() throws MalformedURLException, IOException{
            renderTestPassing=true;
            this.testPassingBean.startAdminTest(test);
    }

    public TestType[] getTypes(){
        return TestType.values();
    }

    public TestEstimationType[] getEstimation(){
        return TestEstimationType.values();
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTestDao(IUpdateDao<TestEntity> testDao) {
        this.testDao = testDao;
    }

    public TestPassingBean getTestPassingBean() {
        return testPassingBean;
    }

    public void setTestPassingBean(TestPassingBean testPassingBean) {
        this.testPassingBean = testPassingBean;
    }

    public boolean isRenderTestPassing()  {
   
        return renderTestPassing;
    }

    public void setRenderTestPassing(boolean renderTestPassing)  {
        this.renderTestPassing = renderTestPassing;
      
    }

    public void setAction(String action) throws MalformedURLException, IOException{
       startTest();
    }

    public String getAction() {
        return action;
    }

    
    public void setTest(TestEntity test)  {
        this.test = test;
    }
    
}
