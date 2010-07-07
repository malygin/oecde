package org.sgu.oecde.tests.util;

import java.util.Collections;
import java.util.List;
import org.sgu.oecde.tests.TestEstimationType;
import org.springframework.util.CollectionUtils;

/**
 * подсчитывает баллы коллекции в соответсвии с типом оценивания прохождения теста
 * @author ShihovMY
 */
public final class pointsCounter {

    private pointsCounter() {
        new AssertionError();
    }

    /**
     * подсчитывает баллы коллекции в соответсвии с типом оценивания прохождения теста
     * @param type
     * @param points
     * @return
     */
    public static int count(TestEstimationType type,List<Integer> points){
        int point = 0;
        if(CollectionUtils.isEmpty(points))
            return 0;
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
