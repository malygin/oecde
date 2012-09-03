/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education;

/**
 *
 * @author malygin
 */
public enum LevelTypeSpeciality {
    bachelor,
    magistracy,
    specialitet,
    undefined;
    private static final long serialVersionUID = 89L;

     @Override
    public String toString() {
        switch(this){
            case magistracy:
                return "Магистратура";
            case bachelor:
                return "Бакалавриат";
            case specialitet:
                return "Специалитет";
            case undefined:
                return "";                  
            default:
                 return "";  
        }
    }

    public int toInt() {
        switch(this){
            case magistracy:
                return 3;
            case bachelor:
                return 2;
            case specialitet:
                return 1;
            case undefined:
                return 0;
            default:
                return 0;
        }
    }

    public static LevelTypeSpeciality parse(int type) {
        switch(type){
            case 3:
                return magistracy;
            case 2:
                return bachelor;
            case 1:
                return specialitet;
            case 0:
                return undefined;
            default:
                return undefined;
        }
    }
}
