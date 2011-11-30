package org.sgu.oecde.core.education.work;

/**
 * соответсвие кода оценки в бд текстовому/цифровому эквиваленту
 * @author ShihovMY
 */
public enum PointToEstimate {
    /**
     * не оценено
     */
    notEstimated,
    /**
     * 2
     */
    two,
    /**
     * 3
     */
    three,
    /**
     * 4
     */
    four,
    /**
     * 5
     */
    five,
    /**
     * неявка
     */
    absence,
    /**
     * зачтено
     */
    passed,
    /**
     * не зачтено
     */
    failed;

    @Override
    public String toString() {
        switch(this){
            case four:
                return "4";
            case two:
                return "2";
            case three:
                return "3";
            case five:
                return "5";
            case absence:
                return "неявка";
            case passed:
                return "зачтено";
            case failed:
                return "не зачтено";
            case notEstimated:
            default:
                return "";
        }
    }

    public String toName(){
        switch(this){
            case four:
                return "4";
            case two:
                return "2";
            case three:
                return "3";
            case five:
                return "5";
            case absence:
                return "absence";
            case passed:
                return "passed";
            case failed:
                return "failed";
            case notEstimated:
            default:
                return "";
        }
    }

    public static PointToEstimate parse(String name){
        if("2".equals(name))
            return two;
        if("3".equals(name))
            return three;
        if("4".equals(name))
            return four;
        if("5".equals(name))
            return five;
        if("absence".equals(name))
            return absence;
        if("passed".equals(name))
            return passed;
        if("failed".equals(name))
            return failed;
        else
            return notEstimated;
    }

    public static PointToEstimate[] exameGrades(){
        PointToEstimate[] estimates = new PointToEstimate[6];
        estimates[0] = notEstimated;
        estimates[1] = two;
        estimates[2] = three;
        estimates[3] = four;
        estimates[4] = five;
        estimates[5] = absence;
        return estimates;
    }

    public static PointToEstimate[] testGrades(){
        PointToEstimate[] estimates = new PointToEstimate[4];
        estimates[0] = notEstimated;
        estimates[1] = passed;
        estimates[2] = failed;
        estimates[3] = absence;
        return estimates;
    }
    
     public static PointToEstimate[] emptyGrades(){
        PointToEstimate[] estimates = new PointToEstimate[4];
        estimates[0] = notEstimated;
        estimates[1] = passed;
        estimates[2] = failed;
        estimates[3] = absence;
        return estimates;
    }
}
