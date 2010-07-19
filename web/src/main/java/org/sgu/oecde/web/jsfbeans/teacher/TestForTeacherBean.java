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
@ManagedBean(name="testForTeacher")
@ViewScoped
public class TestForTeacherBean extends TeacherCurriculumBean{

    private TestEntity test;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;
    
    private static final long serialVersionUID = 115L;

    public TestEntity getTest(){
        return test;
    }

    public void setTestId(Long testId) {
        test = resourceService.getResource(getCurriculum(), new TestEntity(testId), TestEntity.class);
        setAccessDenied(isAccessDenied()?true:test==null);
    }

    public Long getTestId(){
        return test==null?0:test.getId();
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
