package org.sgu.oecde.web;

import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.estimation.CwEstimateNames;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.tests.estimation.TestEstimateNames;
import org.sgu.oecde.tests.estimation.TestsCountEnum;

/**
 *
 * @author ShihovMY
 */
public class PointsFacade {
    private Points points;
    private Teacher teacher;

    public PointsFacade(Points points) {
        this.points = points;
    }

    public Integer getTestsCount(){
        return points.getWorkPoints(TestsCountEnum.TESTS_COUNT);
    }

    public Integer getConcludingTestsCount(){
        return points.getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_COUNT);
    }
    
    public Integer getTestPoints(){
        return points.getWorkPoints(TestEstimateNames.TEST);
    }

    public Integer getReTestPoints(){
        return points.getWorkPoints(TestEstimateNames.RE_TEST);
    }

    public Integer getConcludingReTestPoints(){
        return points.getWorkPoints(TestEstimateNames.CONCLUDING_RE_TEST);
    }

    public Integer getConcludingTestPoints(){
        return points.getWorkPoints(TestEstimateNames.CONCLUDING_TEST);
    }

    public Integer getControlWorksPoints(){
        return points.getWorkPoints(CwEstimateNames.control_work_points);
    }

    public ControlWorkProgress getControlWorksValue(){
        return points.getWorkPoints(CwEstimateNames.control_work_value);
    }

    public PointToEstimate getGrade(){
        return points.getWorkPoints(EstimateNames.estimate);
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Points getPoints() {
        return points;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return points!=null?points.toString():super.toString();
    }
}
