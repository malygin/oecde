package org.sgu.oecde.web.jsfbeans.teacher;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.web.ResourceService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupControlWorks")
@ViewScoped
public class TestForTeacherBean extends AbstractTeacherBean{

    private TestEntity test;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    public TestEntity getTest(){
        return test;
    }

    public void setTestId(Long testId) {
        test = resourceService.getResource(getCurriculum(), new TestEntity(testId), TestEntity.class);
        accessDenied = accessDenied?true:test==null;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
