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
    private Integer testsCount;
    private Integer concludingTestsCount;
    private Integer testsMaxPoints;
    private Integer concludingTestsMaxPoints;
    private Integer test;
    private Integer reTest;
    private Integer concludingReTest;
    private Integer concludingTest;
    private Integer controlWorkPoints;
    private ControlWorkProgress controlWorkValue;
    private PointToEstimate estimate;

    public PointsFacade(Points points) {
        this.points = points;
        testsCount = points.getWorkPoints(TestsCountEnum.TESTS_COUNT);
        concludingTestsCount = points.getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_COUNT);
        testsMaxPoints = points.getWorkPoints(TestsCountEnum.TESTS_MAXIMUM_POINTS);
        concludingTestsMaxPoints = points.getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_MAXIMUM_POINTS);
        test = points.getWorkPoints(TestEstimateNames.TEST);
        reTest = points.getWorkPoints(TestEstimateNames.RE_TEST);
        concludingReTest = points.getWorkPoints(TestEstimateNames.CONCLUDING_RE_TEST);
        concludingTest = points.getWorkPoints(TestEstimateNames.CONCLUDING_TEST);
        controlWorkPoints = points.getWorkPoints(CwEstimateNames.control_work_points);
        controlWorkValue = points.getWorkPoints(CwEstimateNames.control_work_value);
        estimate = points.getWorkPoints(EstimateNames.estimate);
    }

    public Integer getTestsCount(){
        return testsCount;
    }

    public Integer getConcludingTestsCount(){
        return concludingTestsCount;
    }
    
    public Integer getTestPoints(){
        return test;
    }

    public Integer getReTestPoints(){
        return reTest;
    }

    public Integer getConcludingReTestPoints(){
        return concludingReTest;
    }

    public Integer getConcludingTestPoints(){
        return concludingTest;
    }

    public Integer getControlWorksPoints(){
        return controlWorkPoints;
    }

    public ControlWorkProgress getControlWorksValue(){
        return controlWorkValue;
    }

    public PointToEstimate getGrade(){
        return estimate;
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

    public Integer getConcludingTestsMaxPoints() {
        return concludingTestsMaxPoints;
    }

    public Integer getTestsMaxPoints() {
        return testsMaxPoints;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return points!=null?points.toString():super.toString();
    }
}
