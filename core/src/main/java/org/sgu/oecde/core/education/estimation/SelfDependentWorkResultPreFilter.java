package org.sgu.oecde.core.education.estimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class SelfDependentWorkResultPreFilter {

    Set<IResultFilter> resultFilters = new HashSet<IResultFilter>();
    @Autowired
    ResultComparator comparator;

    private SelfDependentWorkResultPreFilter() {
    }

    public List<Points> forEachResult(List<? extends AbstractSelfDependentWorkResult> results,List<Points>pointsList, boolean sumEachIteration){
        Assert.state(resultFilters.isEmpty(), "result filters Set can not be empty");

        if(pointsList==null)
            pointsList = new ArrayList<Points>();
        Points points = null;
        Curriculum cur = null;
        AbstractStudent st = null;
        Collections.sort(results, comparator);
        IResultFilter filter = null;
        Iterator<? extends AbstractSelfDependentWorkResult>iterator = results.iterator();
        Iterator<IResultFilter>filterator;
        while(iterator.hasNext()){
            AbstractSelfDependentWorkResult result = iterator.next();
            filterator = resultFilters.iterator();
            while(filterator.hasNext()){
                filter = filterator.next();
                if((!result.getStudent().equals(st)||!result.getCurriculum().equals(cur))&&points!=null){
                    points.setWorkPoints(filter.getEstimatedWorkPoints());
                    points.setSum(filter.getSum());
                    if(sumEachIteration)
                        filter.clear();
                }
                if(!filter.getClass().getAnnotation(ResultType.class).type().equals(result.getClass()))
                    continue;
                filter.check(result);
            }
            if(!result.getStudent().equals(st)||!result.getCurriculum().equals(cur)){
                points = new Points();
                points.setCurriculum(result.getCurriculum());
                points.setStudent(result.getStudent());
                if(pointsList.contains(points))
                    points = pointsList.get(pointsList.indexOf(points));
                else
                    pointsList.add(points);
            }
            cur = result.getCurriculum();
            st = result.getStudent();
            if(filter!=null&&points!=null&&!iterator.hasNext()){
                points.setWorkPoints(filter.getEstimatedWorkPoints());
                points.setSum(filter.getSum());
            }
        }
        return pointsList;
    }

    public void addResultFilter(IResultFilter resultFilter) {
        this.resultFilters.add(resultFilter);
    }

    public void removeResultFilter(IResultFilter resultFilter){
        this.resultFilters.remove(resultFilter);
    }

    public void clearHashSet(){
        resultFilters.clear();
    }
}
