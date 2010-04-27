/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.estimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.IBooleanResult;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.IRegularResult;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class PointsBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    private Points points;

    public PointsBuilder(Points points) {
        this.points = points;
    }

    public PointsBuilder() {
    }

    public PointsBuilder createPoints(){
        points = new Points();
        return this;
    }

    public PointsBuilder filterRegularResults(List<IRegularResult> results,Set<IResultFilter> filters){
        AssertNotNull();
        if(CollectionUtils.isEmpty(results)&&(CollectionUtils.isEmpty(filters)))
            return this;
        List<EstimatedWorkPoints> workPoints = null;
        int sum = 0;
        Curriculum cur = null;
        ArrayList<Points> pointsList = new ArrayList();
        Points points = null;
        for(IRegularResult result:results){
          //  if(!result.getCurriculum().equals(cur)){
                points = new Points();
                pointsList.add(points);
                points.setWorkPoints(workPoints);
                workPoints = new ArrayList<EstimatedWorkPoints>();
           // }
            for(IResultFilter filter:filters){
                if(!filter.getClass().getAnnotation(ResultType.class).type().equals(result.getClass()))
                    continue;
                workPoints.add(filter.check(result,sum));
            }
            //cur = result.getCurriculum();
        }
        return this;
    }

    public PointsBuilder filterBooleanResults(List<IBooleanResult> results,Set<IResultFilter> filters){
        AssertNotNull();
        if(CollectionUtils.isEmpty(results)&&(CollectionUtils.isEmpty(filters)))
            return this;

        List<EstimatedWorkPoints> workPoints = new ArrayList<EstimatedWorkPoints>();
        int sum = 0;
        for(IBooleanResult bR:results){
            for(IResultFilter filter:filters){
                //filter.check(bR,workPoints,sum);
            }
        }
        points.setWorkPoints(workPoints);
        return this;
    }

    public PointsBuilder setGrade(Estimate grade){
      /*  AssertNotNull();
        if(grade == null)
            return this;
        if(grade.getCurriculum().getExaminationType()==EstimateTypes.exame()){
            switch(grade.getGradeCode()){
                case 2: points.setEstimate("2");
                case 3: points.setEstimate("3");
                case 4: points.setEstimate("4");
                case 5: points.setEstimate("5");
                case 6: points.setEstimate("неявка");
                default:points.setEstimate("");
            }
        }else if(grade.getCurriculum().getExaminationType()==EstimateTypes.testPassing()){
            switch(grade.getGradeCode()){
                case 1: points.setEstimate("не зачёт");
                case 2: points.setEstimate("зачёт");
                case 6: points.setEstimate("неявка");
                default:points.setEstimate("");
            }
        }else{
            points.setEstimate("");
        }*/
        return this;
    }

    public PointsBuilder setCurriculum (Curriculum curriculum){
        AssertNotNull();
        if(curriculum!=null)
            points.setCurriculum(curriculum);
        return this;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Points getPoints() {
        return points;
    }

    private void AssertNotNull(){
        Assert.notNull(points,"points factory's paramenters is null or empty");
    }
}
