package org.sgu.oecde.tests;

/**
 * тип попытки прохождения теста
 * @author ShihovMY
 */
public enum TestAttemptType {
    /**
     * пробное
     */
    trial,
    /**
     * обычное
     */
    regular,
    /**
     * переэкзаменовка
     */
    reTest;
    private static final long serialVersionUID = 80L;

    @Override
    public String toString() {
        switch(this){
            case reTest:
                return "Переэкзаменовка";
            case regular:
                return "Обычный Тест";
            case trial:
                return "Пробный тест";
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case reTest:
                return 2;
            case regular:
                return 1;
            case trial:
                return 0;
            default:
                throw new AssertionError();
        }
    }

    public static TestAttemptType parse(int type) {
        switch(type){
            case 2:
                return reTest;
            case 1:
                return regular;
            case 0:
                return trial;
            default:
                throw new AssertionError();
        }
    }
}
