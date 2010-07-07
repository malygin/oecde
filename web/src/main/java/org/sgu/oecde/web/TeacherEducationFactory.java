package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.tests.TestEntity;

/**
 *
 * @author ShihovMY
 */
public class TeacherEducationFactory extends EducationFactory{

    private Teacher teacher;

    private TeacherEducationFactory() {
    }
 
    public TestEntity getTask(){
        return getResource(getUmk(), new Task(id), Task.class);
    }
    
/*******************************************************************************/

    public TestEntity getTest(){
        return getResource(getUmk(), new TestEntity(id), TestEntity.class);
    }

    public Map<Student,ControlWork> getGroupControlWorks() {
        List sl = getStudentsList(id);
        DeCurriculum c = curriculum();
        return cwService.<Student,ControlWork>getCurriculumControlWorks(sl, c);
    }

    public List<Points> getGroupPointsAndGrades() {
        List<IResultFilter>filters = new LinkedList();
        filters.add(controlWorkFilter);
        filters.add(estimateFilter);
        filters.add(testFilter);
        List<DeCurriculum> c = new LinkedList<DeCurriculum>();
        List sl = getStudentsList(id);
        List<AbstractResult> l =resultDao.getByStudentsAndCurriculums(c, sl, null);
        return preFilter.forEachResult(l, true,filters);
    }
    
    public List<AdditionalSelfDependentWork>  getGroupTestResults() {
        DeCurriculum c = curriculum();
        List l = getStudentsList(id);
        return testAttemptService.getCurriculumAttempts(c, l);
    }

    public DeCurriculum getUmk(){
        DeCurriculum c = curriculumBuilder.getInstance(curriculumId);
        List<DeCurriculum>l = getTeachersDisciplines();
        for(DeCurriculum d:l){
            if(d.equals(c))
                return d;
        }
        return null;
    }

    public List<Group> getTeachersGroups(){
        return curriculumDao.<Group>getGroupsForTeacher(semesters(), sg.getCalendarYear(semester),teacher);
    }
    
    public  Map<DeCurriculum,ControlWork>getStudentControlWorks() {
        List<DeCurriculum> c = new ArrayList<DeCurriculum>();
        c.add(curriculum());
        return cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
    }

    public List<AdditionalSelfDependentWork> getTestsResultsByDisc() {
        DeCurriculum c = curriculum();
        return testAttemptService.getStudentSingleCurriculumTestsWithAttempts(c,  student);
    }

    private List<Student> getStudentsList(Long id){
        Group gr = new Group(id);
        List<Group>l = getTeachersGroups();
        for(Group g:l){
            if(gr.equals(g))
                return new ArrayList(g.getPersons());
        }
        return null;
    }

    public DeCurriculum getDisciplineForStudent() {
        return curriculumDao.getById(curriculumId);
    }
    
    public List<DeCurriculum> getTeachersDisciplines(){
        return curriculumDao.getBySemesterYearAndParameters(semesters(), year(),teacher);
    }

    private int year(){
        return sg.getCalendarYear(semester);
    }

    private Integer[]semesters(){
        return sg.getSemestersByInt(semester);
    }

    private DeCurriculum curriculum(){
        return curriculumDao.getById(curriculumId);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
