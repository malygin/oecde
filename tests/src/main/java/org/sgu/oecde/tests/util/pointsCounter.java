/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests.util;

import java.util.Collections;
import java.util.List;
import org.sgu.oecde.tests.TestEstimationType;

/**
 *
 * @author ShihovMY
 */
public final class pointsCounter {

    private pointsCounter() {
        new AssertionError();
    }

    public static int count(TestEstimationType type,List<Integer> points){
        int point = 0;
        switch(type){
            case middle:
                for(Integer ps:points){
                    point +=ps;
                }
                point  = point/points.size();
                break;
            case max:
            default:
                point = Collections.max(points);
                break;
        }
        return point;
    }
}
