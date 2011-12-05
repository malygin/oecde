package org.sgu.oecde.core.education.estimation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * класс для обработки результатов фильтрами и формирования Points
 * @author ShihovMY
 */
@Service(value="preFilter")
public class ResultPreFilter implements Serializable{

    private ResultPreFilter() {
    }

    private static final long serialVersionUID = 139L;

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
    public List<Points> forEachResult(List<? extends AbstractResult> results, boolean sumEachIteration,List<IResultFilter> resultFilters, Collection<? extends AbstractStudent>students,Collection<? extends Curriculum>curriculums){
        Assert.state(!resultFilters.isEmpty(), "result filters Set can not be empty");
        
        List<Points> pointsList = new ArrayList<Points>();
        if(curriculums==null||students==null||results == null)
            return pointsList;

        List<AbstractStudent>studentsForRemove = new ArrayList<AbstractStudent>();
        List<Curriculum>curriculumsForRemove = new ArrayList<Curriculum>();
        Points points = null;
        Curriculum cur = null;
        AbstractStudent st = null;
        Collections.sort(results);
        
        Iterator<? extends AbstractResult>iterator = results.iterator();
        boolean breakPoint = false;
        while(iterator.hasNext()){
            AbstractResult result = iterator.next();

            breakPoint = ((sumEachIteration&&!result.getCurriculum().equals(cur))||!result.getStudent().equals(st));
 
            
            // убрал     if(breakPoint||!iterator.hasNext()){
            if(breakPoint){
                for(IResultFilter filter:resultFilters)
                    filter.setPoints(points);
                if(breakPoint){
                    points = new Points();
                    points.setCurriculum(result.getCurriculum());
                    curriculumsForRemove.add(result.getCurriculum());
                    studentsForRemove.add(result.getStudent());
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

        for(IResultFilter filter:resultFilters)
            filter.setPoints(points);

        if(pointsList.size()!=students.size()||pointsList.size()!=curriculums.size()){
            List<? extends AbstractStudent>newStudents = new ArrayList<AbstractStudent>(students);
            List<? extends Curriculum>newCurriculums = new ArrayList<Curriculum>(curriculums);
            if(students.size()>1||!sumEachIteration)
                newStudents.removeAll(studentsForRemove);
            if(curriculums.size()>1)
                newCurriculums.removeAll(curriculumsForRemove);
            for(Curriculum c:newCurriculums){
                for(AbstractStudent s:newStudents){
                    boolean exist = false;
                    points:
                    for(Points p:pointsList){
                        if(p.getStudent().equals(s)&&p.getCurriculum().equals(c)){
                            exist = true;
                            break points;
                        }
                    }
                    if(!exist)
                        pointsList.add(new Points(s, c));
                }
                if(!sumEachIteration)
                    break;
            }
        }
        return pointsList;
    }
}
