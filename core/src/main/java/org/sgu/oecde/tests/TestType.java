package org.sgu.oecde.tests;

/**
 * тип теста
 * @author ShihovMY
 */
public enum TestType {
    /**
     * итоговый
     */
    concluding,
    /**
     * обычный
     */
    regular,
    /**
     * анкета
     */
     form,
    /**
     * пробный
     */
    trial;
    private static final long serialVersionUID = 82L;

    @Override
    public String toString() {
        switch(this){
            case concluding:
                return "Итоговый тест";
            case regular:
                return "Обычный Тест";
            case trial:
                return "Пробный тест";
            case form:
                return "Анкета";
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case form:
                return 3;
            case concluding:
                return 2;
            case regular:
                return 0;
            case trial:
                return 1;
            default:
                throw new AssertionError();
        }
    }

    public static TestType parse(int type) {
        switch(type){
            case 3:
                return form;
            case 2:
                return concluding;
            case 0:
                return regular;
            case 1:
                return trial;
            default:
                throw new AssertionError();
        }
    }
}
