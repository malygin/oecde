/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education;

/**
 *
 * @author malygin
 */
public enum SpeedTypeSpeciality {
    
    uskor,
    sokrSP,
    sokrVPO,
    undefined;
    private static final long serialVersionUID = 89L;

     @Override
    public String toString() {
        switch(this){
            case uskor:
                return "ускоренная";
            case sokrSP:
                return "на базе СПО";
            case sokrVPO:
                return "на базе ВПО";
            case undefined:
                return "";                  
            default:
                 return "";  
        }
    }

    public int toInt() {
        switch(this){
            case uskor:
                return 3;
            case sokrSP:
                return 2;
            case sokrVPO:
                return 1;
            case undefined:
                return 0;
            default:
                return 0;
        }
    }

    public static SpeedTypeSpeciality parse(int type) {
        switch(type){
            case 3:
                return uskor;
            case 2:
                return sokrSP;
            case 1:
                return sokrVPO;
            case 0:
                return undefined;
            default:
                return undefined;
        }
    }
}
