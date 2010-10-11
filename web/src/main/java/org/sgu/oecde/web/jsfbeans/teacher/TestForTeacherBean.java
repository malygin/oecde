package org.sgu.oecde.web.jsfbeans.teacher;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestEstimationType;
import org.sgu.oecde.tests.TestType;
import org.sgu.oecde.web.ResourceService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testForTeacher")
@ViewScoped
public class TestForTeacherBean extends AbstractTeacherCurriculumBean{

    private TestEntity test;

    private Long id;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{testDao}")
    private IUpdateDao<TestEntity>testDao;
    
    private static final long serialVersionUID = 115L;

    public String save(){
        testDao.update(test);
        return "testEdit.xhtml.xhtml?faces-redirect=true&id="+id+"&c="+getCurriculumId()+"&s="+semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestEntity getTest(){
        if(test == null && id!=null){
            test = resourceService.getResource(getCurriculum(), new TestEntity(id), TestEntity.class);
            setAccessDenied(getAccessDenied()?true:test==null);
        }
        return test;
    }

    public TestType[] getTypes(){
        return TestType.values();
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
}
