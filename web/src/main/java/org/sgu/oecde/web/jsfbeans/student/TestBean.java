package org.sgu.oecde.web.jsfbeans.student;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.tests.TestPassingBean;

/**
 *
 * @author ShihovMY
 * 
 */
@ManagedBean(name="testBean")
@ViewScoped
public class TestBean extends StudentCurriculumBean {

    private Object[] test;

    private Long curriculumId;

    private Long testId;

    private boolean accessDenied = true;
    private boolean renderTestPassing=false;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testPassingBean}")
    private TestPassingBean testPassingBean;

    private static final long serialVersionUID = 103L;

    public TestBean() {

    }

    public Object[] getTest(){
        return test;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId==null?0:curriculumId;
        test = null;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setTestId(Long testId) throws MalformedURLException, IOException {
        this.testId = testId;
        DeCurriculum c = resourceService.getDisciplineForStudent(student, curriculumId);
        TestEntity t = resourceService.getResource(c,new TestEntity(testId),TestEntity.class);
        boolean available = resourceService.isConcludingTestAvailable(student, c);
        test = resourceService.getTestForStudent(testAttemptService.getStudentSingleTestWithAttempts(t, student,c),student,available);
        accessDenied = test==null;
        this.testPassingBean.startTest(test, c);
    }

    public void startTest(){
           renderTestPassing=true;
     }
    
    public Long getCurriculumId() {
        return curriculumId;
    }

    public Long getTestId() {
        return testId;
    }

    public boolean isAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }

    public boolean isRenderTestPassing() {
        return renderTestPassing;
    }

    public void setRenderTestPassing(boolean renderTestPassing) {
        this.renderTestPassing = renderTestPassing;
    }

    public TestPassingBean getTestPassingBean() {
        return testPassingBean;
    }
    
    public void setTestPassingBean(TestPassingBean testPassingBean) {
        this.testPassingBean = testPassingBean;
    }

}
