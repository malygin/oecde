/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.util;

/**
 *
 * @author ShihovMY
 */
public class Semesters {

    private Semesters() {
        throw new AssertionError();
    }

    public static final Integer[] summer(){
         return new Integer[]{2,4,6,8,10};
    }

    public static final Integer[] winter(){
         return new Integer[]{1,3,5,7,9};
    }
}
