/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.work;

/**
 *
 * @author ShihovMY
 */
public enum PointToEstimate {
    notEstimated,two,three,four,five,absence,passed,notPassed;

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
            case notPassed:
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
                return "6";
            case passed:
                return "7";
            case notPassed:
                return "8";
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
        if("6".equals(name))
            return absence;
        if("7".equals(name))
            return passed;
        if("8".equals(name))
            return notPassed;
        else
            return notEstimated;
    }
}
