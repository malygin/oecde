package org.sgu.oecde.tests.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.estimation.EstimatedWorkPoints;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.IRegularResult;
import org.sgu.oecde.core.education.work.ResultComparatorByCurriculum;
import org.sgu.oecde.tests.TestAttempt;

/**
 *
 * @author ShihovMY
 */
public class PreFilter {
    public List<Points> forEachResult(List<TestAttempt> results,Filter filter){
        List<Points>l = new ArrayList();
        Points points = null;
        Curriculum cur = null;
        Collections.sort(results, new ResultComparatorByCurriculum());
        for(int i = 0;i<results.size();i++){
            TestAttempt result = results.get(i);
            if(!result.getCurriculum().equals(cur)){
                if(points!=null){
                    points.setWorkPoints(filter.getEstimatedWorkPoints());
                    points.setSum(filter.getTempSum());
                }
                points = new Points();
                l.add(points);
                points.setCurriculum(result.getCurriculum());
                points.setStudent(result.getStudent());
                filter.clear();
            }
            filter.check(result, i==results.size()-1);
            cur = result.getCurriculum();
        }
                    points.setWorkPoints(filter.getEstimatedWorkPoints());
                    points.setSum(filter.getTempSum());
        return l;
    }
}
