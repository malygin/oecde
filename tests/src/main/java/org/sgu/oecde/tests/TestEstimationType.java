package org.sgu.oecde.tests;

/**
 * тип оценивания теста
 * @author ShihovMY
 */
public enum TestEstimationType {
    /**
     * максимальный балл
     */
    max,
    /**
     * средный балл
     */
    middle;
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
