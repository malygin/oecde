package org.sgu.oecde.core.education.estimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * класс для обработки результатов фильтрами и формирования Points
 * @author ShihovMY
 */
@Service(value="preFilter")
public class ResultPreFilter {

    private ResultPreFilter() {
    }

    /**
     * пробегается по листу результатов и для каждого результата вызывает все фильтры.
     * вначале и при каждом новом студенте/учебном плане создаётся Points,
     * в который фильтрами помещаются данные, полученные после обработки результатов.
     * @param results - лист результатов
     * @param sumEachIteration - суммировать ли баллы для каждого учебного плана
     * @param resultFilters - лист фильтров
     * @return - лист баллов
     * @see IResultFilter фильтр
     */
    public List<Points> forEachResult(List<? extends AbstractResult> results, boolean sumEachIteration,List<IResultFilter> resultFilters, List<? extends AbstractStudent>students,List<? extends Curriculum>curriculums){
        Assert.state(!resultFilters.isEmpty(), "result filters Set can not be empty");
        List<Points> pointsList = new ArrayList<Points>();
        if(CollectionUtils.isEmpty(curriculums)||CollectionUtils.isEmpty(students))
            return pointsList;
        List<? extends AbstractStudent>newStudents = new ArrayList<AbstractStudent>(students);
        ArrayList<? extends Curriculum>newCurriculums = new ArrayList<Curriculum>(curriculums);
        Points points = null;
        Curriculum cur = null;
        AbstractStudent st = null;
        if(CollectionUtils.isEmpty(results))
            return pointsList;
        Collections.sort(results);
        ListIterator<? extends AbstractResult>iterator = results.listIterator();
        boolean breakPoint = false;
        while(iterator.hasNext()){
            AbstractResult result = iterator.next();

            breakPoint = ((sumEachIteration&&!result.getCurriculum().equals(cur))||!result.getStudent().equals(st));

            if(breakPoint||!iterator.hasNext()){
                for(IResultFilter filter:resultFilters)
                    filter.setPoints(points);
                if(breakPoint){
                    points = new Points();
                    points.setCurriculum(result.getCurriculum());
                    newCurriculums.remove(result.getCurriculum());
                    newStudents.remove(result.getStudent());
                    points.setStudent(result.getStudent());
                    pointsList.add(points);
                }
            }

            for(IResultFilter filter:resultFilters){
                if(filter.getClass().getAnnotation(ResultType.class)!=null&&filter.getClass().getAnnotation(ResultType.class).type().equals(result.getClass()))                    
                    filter.check(result,points);
            }
            
            cur = result.getCurriculum();
            st = result.getStudent();

        }
        for(Curriculum c:newCurriculums){
            Points p = new Points();
            p.setCurriculum(c);
            for(AbstractStudent s:newStudents){
                p.setStudent(s);
            }
            pointsList.add(p);
        }
        return pointsList;
    }
}
