package org.sgu.oecde.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ShihovMY
 */
public class ListUtil {

    private ListUtil() {
        throw new AssertionError();
    }

    public static <T>List<T>oneItemList(T item){
        List<T>curriculums = new ArrayList<T>(1);
        curriculums.add(item);
        return curriculums;
    }
}
