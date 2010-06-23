package org.sgu.oecde.tests;

/**
 * тип вопроса
 * @author ShihovMY
 */
public enum QuestionType {
    /**
     * один из многих. радиобаттон
     */
    radio,
    /**
     * многое из многих. чекбокс
     */
    check,
    /**
     * текст для ввода
     */
    text,
    /**
     * сопоставление
     */
    comparison;
    private static final long serialVersionUID = 79L;

    @Override
    public String toString() {
        switch(this){
            case radio:
                return "Один вариант";
            case check:
                return "Много вариантов";
            case text:
                return "текст";
            case comparison:
                return "сопоставление";
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case radio:
                return 1;
            case check:
                return 2;
            case text:
                return 3;
            case comparison:
                return 4;
            default:
                throw new AssertionError();
        }
    }

    public static QuestionType parse(int type) {
        switch(type){
            case 2:
                return check;
            case 3:
                return text;
            case 1:
                return radio;
            case 4:
                return comparison;
            default:
                throw new AssertionError();
        }
    }
}
