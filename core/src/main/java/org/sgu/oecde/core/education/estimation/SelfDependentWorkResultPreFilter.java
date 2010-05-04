package org.sgu.oecde.core.education.estimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;

/**
 *
 * @author ShihovMY
 */
public class SelfDependentWorkResultPreFilter {

    List<IResultFilter> resultFilters;

    public SelfDependentWorkResultPreFilter() {
    }

    public List<Points> forEachResult(List<? extends AbstractSelfDependentWorkResult> results,Comparator<AbstractSelfDependentWorkResult> comparator,List<Points>pointsList, boolean sumEachIteration){
        if(pointsList==null)
            pointsList = new ArrayList<Points>();
        Points points = null;
        Curriculum cur = null;
        Collections.sort(results, comparator);
        IResultFilter filter = null;
        Iterator<? extends AbstractSelfDependentWorkResult>iterator = results.iterator();
        Iterator<IResultFilter>filterator;
        while(iterator.hasNext()){
            AbstractSelfDependentWorkResult result = iterator.next();
            filterator = resultFilters.iterator();
            while(filterator.hasNext()){
                filter = filterator.next();
                if(!result.getCurriculum().equals(cur)&&points!=null){
                    points.setWorkPoints(filter.getEstimatedWorkPoints());
                    points.setSum(filter.getSum());
                    if(sumEachIteration)
                        filter.clear();
                }
                if(!filter.getClass().getAnnotation(ResultType.class).type().equals(result.getClass()))
                    continue;
                filter.check(result);
            }
            if(!result.getCurriculum().equals(cur)){
                points = new Points();
                points.setCurriculum(result.getCurriculum());
                points.setStudent(result.getStudent());
                if(pointsList.contains(points))
                    points = pointsList.get(pointsList.indexOf(points));
                else
                    pointsList.add(points);
            }
            cur = result.getCurriculum();
            if(filter!=null&&points!=null&&!iterator.hasNext()){
                points.setWorkPoints(filter.getEstimatedWorkPoints());
                points.setSum(filter.getSum());
            }
        }
        return pointsList;
    }

    public void setResultFilters(List<IResultFilter> resultFilters) {
        this.resultFilters = resultFilters;
    }
}
