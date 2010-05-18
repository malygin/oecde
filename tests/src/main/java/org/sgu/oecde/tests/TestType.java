/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

/**
 *
 * @author ShihovMY
 */
public enum TestType {
    concluding,regular,trial;
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
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case concluding:
                return 2;
            case regular:
                return 1;
            case trial:
                return 0;
            default:
                throw new AssertionError();
        }
    }

    public static TestType parse(int type) {
        switch(type){
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
