/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.util;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 22.06.2011
 * 
 */
public enum LangEnum {
    ru, en;
  
    
    
    private static final long serialVersionUID = 97L;
    @Override
    public String toString() {
        switch(this){
            case en:
                return "english";
            default:   
            case ru:           
                return "русский";
        }
    }

    public String toName(){
        switch(this){
            case en:
                return "en";             
            default:
            case ru:
                return "ru";
        }
    }

    public static LangEnum parse(String name){
        if("en".equals(name))
            return en;      
        else
            return ru;
    }

    public String getName(){
        return toString();
    }
    
}
