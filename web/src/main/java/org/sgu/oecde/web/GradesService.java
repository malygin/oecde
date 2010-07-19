package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.Teacher;
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

    public List<PointsFacade>getStudentGrades(Map<DeCurriculum,Teacher> curriculums,Student student){
        List<Student>students = new LinkedList<Student>();
        students.add(student);
        return pointsToFacades(curriculums, students);
    }

    public List<PointsFacade>getCurriculumGrades(DeCurriculum curriculum,List<Student>students){
        List<DeCurriculum>curriculums = new LinkedList<DeCurriculum>();
        curriculums.add(curriculum);
        return getCurriculumsAndStudentsGrades(curriculums, students);
    }

    public List<PointsFacade>getCurriculumsAndStudentsGrades(List<DeCurriculum> curriculums,List<Student>students){
        List<Points>points = getGrades(curriculums,students);
        List<PointsFacade>facades = new LinkedList<PointsFacade>();
        for(Points p:points){
            facades.add(new PointsFacade(p));
        }
        return facades;
    }

    public static List<PointsFacade> pointsToFacades(List<Points>points,Map<DeCurriculum,Teacher> curriculums){
        List<PointsFacade>facades = new LinkedList<PointsFacade>();
        for(Points p:points){
            PointsFacade pf = new PointsFacade(p);
            if(curriculums.containsKey(p.<DeCurriculum>getCurriculum()))
                pf.setTeacher(curriculums.get(p.<DeCurriculum>getCurriculum()));
            facades.add(pf);
        }
        return facades;
    }

    private List<PointsFacade>pointsToFacades(Map<DeCurriculum,Teacher> curriculums,List<Student>students){
        List<Points>points = getGrades(new ArrayList(curriculums.keySet()),students);
        return pointsToFacades(points,curriculums);
    }
}
