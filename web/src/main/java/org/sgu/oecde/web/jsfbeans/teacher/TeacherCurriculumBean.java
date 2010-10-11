package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherCurriculumBean")
@ViewScoped
public class TeacherCurriculumBean extends AbstractTeacherCurriculumBean{

    List<TestEntity>tests;

    @ManagedProperty(value="#{testService}")
    protected TestService testService;

    public List<TestEntity> getTests() {
        if(tests == null){
            DeCurriculum c = getCurriculum();
            tests = testService.getCurriculumTestsMap(ListUtil.oneItemList(c)).get(c);
        }
        return tests;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}