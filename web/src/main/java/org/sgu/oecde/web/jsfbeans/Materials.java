package org.sgu.oecde.web.jsfbeans;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.sgu.oecde.controlworks.estimation.ControlWorkFilter;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.EstimateFilter;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.estimation.TestFilter;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="MaterialsBean")
@RequestScoped
public class Materials {

    @ManagedProperty(value="#{curriculumBuilder}")
    private DeCurriculumBuilder curriculumBuilder;

    @ManagedProperty(value="#{semesterGetter}")
    private SemesterGetter sg;

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{preFilter}")
    private ResultPreFilter preFilter;

    @ManagedProperty(value="#{estimateFilter}")
    private EstimateFilter estimateFilter;

    @ManagedProperty(value="#{testFilter}")
    private TestFilter testFilter;

    @ManagedProperty(value="#{controlWorkFilter}")
    private ControlWorkFilter controlWorkFilter;

    @ManagedProperty(value="#{resultDao}")
    private IResultDao<AbstractResult>resultDao;

    @ManagedProperty(value="#{curriculumDao}")
    private ICurriculumDao<DeCurriculum> curriculumDao;

    private int semester;
    
    private Student student;

    public Materials() throws Exception  {
        AbstractUser user = SecurityContextHandler.getUser();
        if(user instanceof Student && user!=null)
            student = (Student) user;
        else
            throw new Exception("user is null");
    }

    public List<AdditionalCurriculum> getStudentsDisciplines(){
        List<AdditionalCurriculum>ac = testAttemptService.getStudentAttemptsCount(getCurriculums(), null, student);
        for(Points p:getStudentGrades()){
            ac.get(ac.indexOf(p.getCurriculum())).setPoints(p);
        }
        System.out.println(getStudentGrades());
        return ac;
    }

    public List<Points> getStudentGrades() {
        List<IResultFilter>filters = new LinkedList();
        filters.add(controlWorkFilter);
        filters.add(estimateFilter);
        filters.add(testFilter);
        List<Student>stl = new LinkedList<Student>();
        stl.add(student);
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(getCurriculums(), stl, null);
        return preFilter.forEachResult(l, true,filters);
    }

    private List<DeCurriculum> getCurriculums()  {
        return getCurriculums(sg.getCalendarYear(semester));
    }

    private List<DeCurriculum> getCurriculums(int year)  {
        return curriculumDao.getByExample(curriculumBuilder.getInstance(year, semester, student));
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester==null?0:semester;
    }

    public void setControlWorkFilter(ControlWorkFilter controlWorkFilter) {
        this.controlWorkFilter = controlWorkFilter;
    }

    public void setCurriculumBuilder(DeCurriculumBuilder curriculumBuilder) {
        this.curriculumBuilder = curriculumBuilder;
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setEstimateFilter(EstimateFilter estimateFilter) {
        this.estimateFilter = estimateFilter;
    }

    public void setPreFilter(ResultPreFilter preFilter) {
        this.preFilter = preFilter;
    }

    public void setResultDao(IResultDao<AbstractResult> resultDao) {
        this.resultDao = resultDao;
    }

    public void setSg(SemesterGetter sg) {
        this.sg = sg;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setTestFilter(TestFilter testFilter) {
        this.testFilter = testFilter;
    }
}
