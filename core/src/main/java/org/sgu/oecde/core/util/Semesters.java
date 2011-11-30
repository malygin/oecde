package org.sgu.oecde.core.util;

/**
 * массивы с зимним либо летним семестрами
 * @author ShihovMY
 */
public class Semesters {

    private Semesters() {
        throw new AssertionError();
    }

    /**
     *
     * @return летние семестры
     */
    public static final Integer[] summer(){
         return new Integer[]{2,4,6,8,10,12,14};
    }

    /**
     *
     * @return зимние семестры
     */
    public static final Integer[] winter(){
         return new Integer[]{1,3,5,7,9,11,13};
    }
}
