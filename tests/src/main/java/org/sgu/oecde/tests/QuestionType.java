/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

/**
 *
 * @author ShihovMY
 */
public enum QuestionType {

    radio,check,text,comparison;

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
