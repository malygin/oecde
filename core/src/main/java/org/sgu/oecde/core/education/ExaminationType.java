/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education;

/**
 *
 * @author ShihovMY
 */
public enum ExaminationType {
    exame,test,empty;
    private static final long serialVersionUID = 57L;

    @Override
    public String toString() {
        switch(this){
            case exame:
                return "экзамен";
            case test:
                return "зачёт";
            case empty:
            default:
                return "";
        }
    }

    public String toName(){
        switch(this){
            case exame:
                return "Э";
            case test:
                return "З";
            case empty:
            default:
                return "";
        }
    }

    public static ExaminationType parse(String name){
        if("З".equals(name))
            return test;
        else if("Э".equals(name))
            return exame;
        else
            return empty;
    }
}
