package org.sgu.oecde.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
public class GradesService implements Serializable{

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

    private static final long serialVersionUID = 165L;

    public List<Points> getGrades(Collection<DeCurriculum> curriculums,Collection<Student>students) {
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
        Collections.sort(facades, new OrderByStudentName());
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
        Collections.sort(facades, new OrderByDisciplineName());
        return facades;
    }

    public static PointsFacade putTeacherIntoFacade(Points p,Map<DeCurriculum,Teacher> curriculums){
        PointsFacade pf = new PointsFacade(p);
        if(curriculums.containsKey(p.<DeCurriculum>getCurriculum()))
            pf.setTeacher(curriculums.get(p.<DeCurriculum>getCurriculum()));
        return pf;
    }

    private class OrderByDisciplineName implements Comparator<PointsFacade>{

        @Override
        public int compare(PointsFacade o1, PointsFacade o2) {
            int discipline = 0;
            if(o1!=null &&o2!=null &&
                    o1.getPoints()!=null&&o2.getPoints()!=null&&o1.getPoints().getCurriculum()!=null&&
                    o2.getPoints().getCurriculum()!=null&&
                    o1.getPoints().<DeCurriculum>getCurriculum().getDiscipline()!=null &o2.getPoints().<DeCurriculum>getCurriculum().getDiscipline()!=null &&
                    o1.getPoints().<DeCurriculum>getCurriculum().getDiscipline().getName()!=null
                ){
                discipline = o1.getPoints().<DeCurriculum>getCurriculum().getDiscipline().getName().compareTo(o2.getPoints().<DeCurriculum>getCurriculum().getDiscipline().getName());
            }
            return discipline;
        }

    }

    private class OrderByStudentName implements Comparator<PointsFacade>{

        @Override
        public int compare(PointsFacade o1, PointsFacade o2) {
            int st = 0;
            if(o1!=null &&o2!=null &&
                    o1.getPoints()!=null&&o2.getPoints()!=null&&o1.getPoints().getStudent()!=null&&
                    o2.getPoints().getStudent()!=null
                ){
                st = o1.getPoints().getStudent().getFio().compareTo(o2.getPoints().getStudent().getFio());
            }
            return st;
        }

    }
}
