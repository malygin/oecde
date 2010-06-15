package org.sgu.oecde.core.education.estimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class ResultPreFilter {

    private ResultPreFilter() {
    }

    public List<Points> forEachResult(List<? extends AbstractResult> results, boolean sumEachIteration,List<IResultFilter> resultFilters){
        Assert.state(!resultFilters.isEmpty(), "result filters Set can not be empty");
        List<Points> pointsList = new ArrayList<Points>();
        Points points = null;
        Curriculum cur = null;
        AbstractStudent st = null;
        Collections.sort(results);
        ListIterator<? extends AbstractResult>iterator = results.listIterator();
        while(iterator.hasNext()){
            AbstractResult result = iterator.next();
            
            if(!iterator.hasPrevious())
                points = new Points();

            for(IResultFilter filter:resultFilters){
                if(filter.getClass().getAnnotation(ResultType.class)!=null&&!filter.getClass().getAnnotation(ResultType.class).type().equals(result.getClass()))
                    continue;
                filter.check(result,points);
            }

            if(((sumEachIteration&&!result.getCurriculum().equals(cur))||!result.getStudent().equals(st))){
                if(iterator.hasPrevious())
                    points = new Points();
                points.setCurriculum(result.getCurriculum());
                points.setStudent(result.getStudent());
                pointsList.add(points);
            }

            cur = result.getCurriculum();
            st = result.getStudent();

            if(!iterator.hasNext())
                for(IResultFilter filter:resultFilters)
                    filter.setPoints(points);
        }
        return pointsList;
    }
}
