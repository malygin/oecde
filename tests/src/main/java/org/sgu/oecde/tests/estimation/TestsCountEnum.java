package org.sgu.oecde.tests.estimation;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 *
 * @author ShihovMY
 */
public enum TestsCountEnum implements IEstimate{
    /**
     * количество обычных тестов
     */
    TESTS_COUNT,
    /**
     * итоговый тест
     */
    CONCLUDING_TESTS_COUNT,
    /**
     * максимум баллов обычных тестов
     */
    TESTS_MAXIMUM_POINTS,
    /**
     * максимум баллов итоговых тестов
     */
    CONCLUDING_TESTS_MAXIMUM_POINTS;

    public int getDedault(){
        switch(this){
            case CONCLUDING_TESTS_COUNT:
                return 0;
            case CONCLUDING_TESTS_MAXIMUM_POINTS:
                return 100;
            case TESTS_COUNT:
                return 0;
            case TESTS_MAXIMUM_POINTS:
                return 100;
            default:
                throw new AssertionError();
        }
    }
}
