/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

/**
 *
 * @author ShihovMY
 */
public enum TestEstimationType {
    max,middle;
    private static final long serialVersionUID = 81L;

    @Override
    public String toString() {
        switch(this){
            case max:
                return "максимальное";
            case middle:
                return "среднее";
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case max:
                return 2;
            case middle:
                return 3;
            default:
                throw new AssertionError();
        }
    }

    public static TestEstimationType parse(int type) {
        switch(type){
            case 2:
                return max;
            case 3:
                return middle;
            default:
                return max;
        }
    }
}
