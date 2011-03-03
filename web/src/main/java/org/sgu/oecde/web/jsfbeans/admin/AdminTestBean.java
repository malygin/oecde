package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.tests.TestType;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class AdminTestBean implements Serializable{

    private TestEntity test;

    private Long id;

    @ManagedProperty(value="#{testDao}")
    private IUpdateDao<TestEntity>testDao;

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
}
