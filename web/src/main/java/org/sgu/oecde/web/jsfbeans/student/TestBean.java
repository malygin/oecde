package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.web.ResourceService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testBean")
@ViewScoped
public class TestBean extends StudentCurriculumBean{

    private Object[] test;

    private Long curriculumId;

    private Long testId;

    private boolean accessDenied = true;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    private static final long serialVersionUID = 103L;

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

    public void setTestId(Long testId) {
        this.testId = testId;
        DeCurriculum c = resourceService.getDisciplineForStudent(student, curriculumId);
        TestEntity t = resourceService.getResource(c,new TestEntity(testId),TestEntity.class);
        test = resourceService.getTestForStudent(testAttemptService.getStudentSingleTestWithAttempts(t, student),student);
        accessDenied = test==null;
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
}
