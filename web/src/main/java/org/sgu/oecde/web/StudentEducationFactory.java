package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@Service
public class StudentEducationFactory extends EducationFactory{

    private StudentEducationFactory() {
    }

    public List<AdditionalSelfDependentWork> getTestsResultsByDisc() {
        DeCurriculum c = curriculumBuilder.getInstance(sg.getCurrentYear(), semester, student);
        return testAttemptService.getStudentsSingleCurriculumAttempts(c, null, student);
    }



    public Map<Student,ControlWork> getControlWorkInfo4Group() {
        List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstance(sg.getCurrentYear(), semester, student));
        return cwService.<Student,ControlWork>getAllControlWorks(new ArrayList(student.getGroup().getPersons()), c);
    }

    public List<AdditionalSelfDependentWork> getTestsResultsByKod() {
        return testAttemptService.getStudentAttempts(getCurriculums(), null, student);
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

    public List<Points> getOldGrades(){
        List<IResultFilter>filters = new LinkedList();
        filters.add(estimateFilter);
        List<Student>stl = new LinkedList<Student>();
        stl.add(student);
        List<DeCurriculum> c = getCurriculums(sg.getCalendarYear(student, semester));
        List<Estimate> l = estimateDao.getByStudentsAndCurriculums(c, stl, null);
        return preFilter.forEachResult(l, true,filters);
    }

    public List<Points> getLowGradedDisciplines() {
        List<Points> lp = getStudentGrades();
        Iterator<Points> i = lp.iterator();
        while(i.hasNext()){
            Points disciplineItem = i.next();
            boolean estimated = true;
            if(disciplineItem.getWorkPoints().containsKey(EstimateNames.estimate)){
                PointToEstimate pe = disciplineItem.<PointToEstimate>getWorkPoints().get(EstimateNames.estimate);
                estimated = PointToEstimate.five.equals(pe)
                        ||PointToEstimate.four.equals(pe)
                        ||PointToEstimate.three.equals(pe)
                        ||PointToEstimate.passed.equals(pe);
            }
            if(disciplineItem.getSum()>175&&estimated){
               i.remove();
            }
        }
        return lp;
    }

    public List<AdditionalCurriculum> getStudentsDisciplines(){
        List<AdditionalCurriculum>ac = testAttemptService.getStudentAttemptsCount(getCurriculums(), null, student);
        for(Points p:getStudentGrades()){
            ac.get(ac.indexOf(p.getCurriculum())).setPoints(p);
        }
        return ac;
    }

    public List<Points>  getGroupRating() {
        List<IResultFilter>filters = new LinkedList();
        filters.add(controlWorkFilter);
        filters.add(testFilter);
        List<Student> stl = new ArrayList(student.getGroup().getPersons());
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(getCurriculums(), stl, null);
        return preFilter.forEachResult(l, false,filters);
    }

    public List<Teacher> getTeachersList(){
        return curriculumDao.<Teacher>getTeachersByGroup(sg.getSemestersByInt(0), sg.getCalendarYear(0), student.getGroup());
    }

    public boolean isGotCourseWork(){
        boolean got=false;
        List<DeCurriculum> list = getCurriculums();
        for(DeCurriculum s:list){
            if(s.getTermPapersNumber()>0){
                got=true;
                break;
            }
        }
        return got;
    }

    public DeCurriculum getUmk(){
        return getDisciplineForStudent();
    }

    public TestEntity getTest(){
        return getResource(getDisciplineForStudent(),new TestEntity(id),TestEntity.class);
    }

    public Task getTask(){
        return getResource(getDisciplineForStudent(),new Task(id),Task.class);
    }

    public List<DeCurriculum> getStudentsDisciplinesForLibrary(){
        return getCurriculums(sg.getCalendarYear(student, semester));
    }

    public DeCurriculum getDisciplineForStudent() {
        DeCurriculum c = curriculumBuilder.getInstance(sg.getCurrentYear(), semester, student);
        c.setId(curriculumId);
        List<DeCurriculum> l = curriculumDao.getByExample(c);
        if(l.isEmpty())
            return null;
        return l.get(0);
    }

    public Map getStudentControlWorks(){
        List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstance(sg.getCurrentYear(), semester, student));
        return cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
    }

    public void getLowGradedControlWorks(){
        Map<DeCurriculum,ControlWork> m = getStudentControlWorks();
        Iterator specsI = m.entrySet().iterator();
        while(specsI.hasNext()){
            Map.Entry<DeCurriculum,ControlWork> v = (Map.Entry)specsI.next();
            ControlWork w = v.getValue();
            if(w!=null)
                if(w.getCwAttempt()!=null&&w.getCwAttempt().size() > 0&&w.getProgress().equals(ControlWorkProgress.passed)){
                    specsI.remove();
                }
        }
    }

    public void getBadPassedTests(){
        List<DeCurriculum> cl = getCurriculums(sg.getCalendarYear(student, semester));
        List<AdditionalSelfDependentWork>l = testAttemptService.getStudentsAllCurriculumAttempts(cl, null, student,id==2);
        Iterator<AdditionalSelfDependentWork> i = l.iterator();
        while(i.hasNext()){
            AdditionalSelfDependentWork passedTest = i.next();
            if(passedTest.getPointsForWork()>30)
                i.remove();
        }
    }

    public void getShedule(){
        getCurriculums();
    }

    private List<DeCurriculum> getCurriculums()  {
        return getCurriculums(sg.getCalendarYear(semester));
    }

    private List<DeCurriculum> getCurriculums(int year)  {
        return curriculumDao.getByExample(curriculumBuilder.getInstance(year, sg.getSemesterByStudentYear(student, semester), student));
    }
}
