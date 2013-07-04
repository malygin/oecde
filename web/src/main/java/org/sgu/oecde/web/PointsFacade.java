package org.sgu.oecde.web;

import java.io.Serializable;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.estimation.CwEstimateNames;
import org.sgu.oecde.core.education.estimation.EstimateNames;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.activity.ActivityEstimateNames;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.tests.estimation.TestEstimateNames;
import org.sgu.oecde.tests.estimation.TestsCountEnum;

/**
 *
 * @author ShihovMY
 */
public class PointsFacade implements Serializable{
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
    private Integer controlWorkMaxPoints;
    private Integer activityPoints;
    private Integer samAudWorkPoints;
    private Integer samAudOutWorkPoints;
    private Integer personalCharPoints;
    private Integer publishPoints;
    private Integer lecPoints;
    private ControlWorkProgress controlWorkValue;
    private PointToEstimate grade;

    private static final long serialVersionUID = 163L;

    public PointsFacade(Points points) {
        
        this.points = points;
      //  this.points.addNewWorkPoint(EstimateNames.estimate, "");
        testsCount = points.getWorkPoints(TestsCountEnum.TESTS_COUNT);
        concludingTestsCount = points.getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_COUNT);
        testsMaxPoints = points.getWorkPoints(TestsCountEnum.TESTS_MAXIMUM_POINTS);
        concludingTestsMaxPoints = points.getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_MAXIMUM_POINTS);
        test = points.getWorkPoints(TestEstimateNames.TEST);
        reTest = points.getWorkPoints(TestEstimateNames.RE_TEST);
        concludingReTest = points.getWorkPoints(TestEstimateNames.CONCLUDING_RE_TEST);
        concludingTest = points.getWorkPoints(TestEstimateNames.CONCLUDING_TEST);
        controlWorkPoints = points.getWorkPoints(CwEstimateNames.control_work_points);
        activityPoints = points.getWorkPoints(ActivityEstimateNames.activity_points);
        samAudWorkPoints = points.getWorkPoints(ActivityEstimateNames.samAud_points);
        samAudOutWorkPoints = points.getWorkPoints(ActivityEstimateNames.samOutAud_points);
        publishPoints = points.getWorkPoints(ActivityEstimateNames.publish_points);
        personalCharPoints = points.getWorkPoints(ActivityEstimateNames.personChar_points);
        lecPoints = points.getWorkPoints(ActivityEstimateNames.lec_points);
        controlWorkValue = points.getWorkPoints(CwEstimateNames.control_work_value);
        controlWorkMaxPoints=(controlWorkValue!=null)?100:0;
        Estimate e = points.getWorkPoints(EstimateNames.estimate);
        grade = e==null?PointToEstimate.notEstimated:e.getGradeCode();

    }

    public Integer getConcludingReTest() {
        return concludingReTest;
    }

    public void setConcludingReTest(Integer concludingReTest) {
        this.concludingReTest = concludingReTest;
    }

    public Integer getConcludingTest() {
        return concludingTest;
    }

    public void setConcludingTest(Integer concludingTest) {
        this.concludingTest = concludingTest;
    }

    public Integer getConcludingTestsCount() {
        return concludingTestsCount;
    }

    public void setConcludingTestsCount(Integer concludingTestsCount) {
        this.concludingTestsCount = concludingTestsCount;
    }

    public Integer getConcludingTestsMaxPoints() {
        return concludingTestsMaxPoints;
    }

    public void setConcludingTestsMaxPoints(Integer concludingTestsMaxPoints) {
        this.concludingTestsMaxPoints = concludingTestsMaxPoints;
    }

    public Integer getControlWorkPoints() {
        return controlWorkPoints;
    }

    public void setControlWorkPoints(Integer controlWorkPoints) {
        this.controlWorkPoints = controlWorkPoints;
    }

    public ControlWorkProgress getControlWorkValue() {
        return controlWorkValue;
    }

    public void setControlWorkValue(ControlWorkProgress controlWorkValue) {
        this.controlWorkValue = controlWorkValue;
    }

    public PointToEstimate getGrade() {
        return grade;
    }

    public void setGrade(PointToEstimate grade) {
        this.grade = grade;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Integer getReTest() {
        return reTest;
    }

    public void setReTest(Integer reTest) {
        this.reTest = reTest;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Integer getTestsCount() {
        return testsCount;
    }

    public void setTestsCount(Integer testsCount) {
        this.testsCount = testsCount;
    }

    public Integer getTestsMaxPoints() {
        return testsMaxPoints;
    }

    public void setTestsMaxPoints(Integer testsMaxPoints) {
        this.testsMaxPoints = testsMaxPoints;
    }

    @Override
    public String toString() {
        return points!=null?points.toString():super.toString();
    }

    public Integer getActivityPoints() {
        return activityPoints;
    }

    public Integer getSamAudWorkPoints() {
        return samAudWorkPoints;
    }

    public void setSamAudWorkPoints(Integer samAudWorkPoints) {
        this.samAudWorkPoints = samAudWorkPoints;
    }

    public Integer getPersonalCharPoints() {
        return personalCharPoints;
    }

    public void setPersonalCharPoints(Integer personalCharPoints) {
        this.personalCharPoints = personalCharPoints;
    }

    public Integer getSamAudOutWorkPoints() {
        return samAudOutWorkPoints;
    }

    public void setSamAudOutWorkPoints(Integer samAudOutWorkPoints) {
        this.samAudOutWorkPoints = samAudOutWorkPoints;
    }
   
    public void setActivityPoints(Integer activityPoints) {
        this.activityPoints = activityPoints;
    }

    public Integer getControlWorkMaxPoints() {
        return controlWorkMaxPoints;
    }

    public void setControlWorkMaxPoints(Integer controlWorkMaxPoints) {
        this.controlWorkMaxPoints = controlWorkMaxPoints;
    }

    public Integer getPublishPoints() {
        return publishPoints;
    }

    public void setPublishPoints(Integer publishPoints) {
        this.publishPoints = publishPoints;
    }

    public Integer getLecPoints() {
        return lecPoints;
    }

    public void setLecPoints(Integer lecPoints) {
        this.lecPoints = lecPoints;
    }
}
