package org.sgu.oecde.web;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
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

    public List<Points> getGrades(List<DeCurriculum> curriculums,List<Student>students) {
        List<IResultFilter>filters = new LinkedList();
        filters.add(controlWorkFilter);
        filters.add(estimateFilter);
        filters.add(testFilter);
        List<AbstractResult> l = resultDao.getByStudentsAndCurriculums(curriculums, students, null);
        return preFilter.forEachResult(l, true,filters);
    }

    public List<PointsFacade>getStudentGrades(List<DeCurriculum> curriculums,Student student){
        List<Student>students = new LinkedList<Student>();
        students.add(student);
        return pointsToFacades(curriculums, students);
    }

    public List<PointsFacade>getCurriculumGrades(DeCurriculum curriculum,List<Student>students){
        List<DeCurriculum>curriculums = new LinkedList<DeCurriculum>();
        curriculums.add(curriculum);
        return pointsToFacades(curriculums, students);
    }

    public static List<PointsFacade> pointsToFacades(List<Points>points){
        List<PointsFacade>facades = new LinkedList<PointsFacade>();
        for(Points p:points){
            facades.add(new PointsFacade(p));
        }
        return facades;
    }

    private List<PointsFacade>pointsToFacades(List<DeCurriculum> curriculums,List<Student>students){
        List<Points>points = getGrades(curriculums,students);
        return pointsToFacades(points);
    }
}
