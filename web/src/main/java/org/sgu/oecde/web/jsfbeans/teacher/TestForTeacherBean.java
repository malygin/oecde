package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.tests.TestPassingBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testForTeacher")
@ViewScoped
public class TestForTeacherBean extends AbstractTeacherCurriculumBean{

    private TestEntity test;

    private Long id;
    private boolean renderTestPassing=false;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{testDao}")
    private IUpdateDao<TestEntity>testDao;

    @ManagedProperty(value="#{testPassingBean}")
    private TestPassingBean testPassingBean;
    
    private static final long serialVersionUID = 115L;

    public String save(){
        testDao.update(test);
        return "testEdit.xhtml.xhtml?faces-redirect=true&id="+id+"&c="+getCurriculumId()+"&s="+semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws MalformedURLException, IOException {
        this.id = id;
        this.testPassingBean.startTeacherTest(this.getTest());
    }

    public TestEntity getTest(){
        if(test == null && id!=null){
            test = resourceService.getResource(getCurriculum(), new TestEntity(id), TestEntity.class);
            setAccessDenied(getAccessDenied()?true:test==null);
        }
        return test;
    }

    public TestEstimationType[] getEstimation(){
        return TestEstimationType.values();
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public void setTestDao(IUpdateDao<TestEntity> testDao) {
        this.testDao = testDao;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public TestPassingBean getTestPassingBean() {
        return testPassingBean;
    }

    public void setTestPassingBean(TestPassingBean testPassingBean) {
        this.testPassingBean = testPassingBean;
    }

    
}
