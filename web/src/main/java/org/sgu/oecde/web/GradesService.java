package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestService;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@Service
public class GradesService {

    @Resource
    private ResultPreFilter preFilter;

    @Resource
    private IResultFilter controlWorkFilter;

    @Resource
    private IResultFilter estimateFilter;

    @Resource
    private IResultFilter testFilter;

    @Resource
    private IResultDao<AbstractResult>resultDao;

    @Resource
    private TestService testService;

    public List<Points> getGrades(List<DeCurriculum> curriculums,List<Student>students) {
        List<IResultFilter>filters = new ArrayList(3);
        filters.add(controlWorkFilter);
        filters.add(estimateFilter);
        filters.add(testFilter);
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(curriculums, students, null);
        return preFilter.forEachResult(l, true,filters,students,curriculums);
    }

    public List<Points>getStudentGrades(List<DeCurriculum> curriculums,Student student){
        return getGrades(curriculums,ListUtil.<Student>oneItemList(student));
    }

    public List<PointsFacade>getCurriculumGrades(DeCurriculum curriculum,List<Student>students){
        List<Points>points = getGrades(ListUtil.<DeCurriculum>oneItemList(curriculum),students);
        List<PointsFacade>facades = new ArrayList<PointsFacade>(points.size());
        Map<DeCurriculum,List<TestEntity>>m = testService.<DeCurriculum>getCurriculumTestsMap(ListUtil.<DeCurriculum>oneItemList(curriculum));
        for(Points p:points){
            testService.countTests(m, p);
            facades.add(new PointsFacade(p));
        }
        return facades;
    }

    public List<PointsFacade>getStudentGrades(Map<DeCurriculum,Teacher> curriculums,Student student){
        List<Points>points = getStudentGrades(new ArrayList(curriculums.keySet()),student);
        List<PointsFacade>facades = new ArrayList<PointsFacade>(points.size());
        Map<DeCurriculum,List<TestEntity>>m = testService.<DeCurriculum>getCurriculumTestsMap(new ArrayList(curriculums.keySet()));
        for(Points p:points){
            testService.countTests(m, p);
            facades.add(putTeacherIntoFacade(p, curriculums));
        }
        return facades;
    }

    public static PointsFacade putTeacherIntoFacade(Points p,Map<DeCurriculum,Teacher> curriculums){
        PointsFacade pf = new PointsFacade(p);
        if(curriculums.containsKey(p.<DeCurriculum>getCurriculum()))
            pf.setTeacher(curriculums.get(p.<DeCurriculum>getCurriculum()));
        return pf;
    }
}
