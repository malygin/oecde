package org.sgu.oecde.core.users;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 18.05.2010
 * Скины портала
 */
public enum UsersSkins {

    //дефолтный 
    defaultSkin,
    //темный скин
    darkSkin,
  ;

    private static final long serialVersionUID = 141L;

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        switch(this){
            case defaultSkin:
                return "обычный скин";
            case darkSkin:
                return "темный скин (тестовый)";
            
            default:
                return "";
        }
    }
    public String getFolder(){
         switch(this){
            case defaultSkin:
                return "";
            case darkSkin:
                return "dark_skin/";           
            default:
                return"";
        }
        
    } 
      public String getFormulaColor(){
         switch(this){
            case defaultSkin:
                return "black";
            case darkSkin:
                return "white";           
            default:
                return"";
        }
        
    } 
    public int toInt() {
        switch(this){
            case defaultSkin:
                return 0;
            case darkSkin:
                return 1;           
            default:
                throw new AssertionError();
        }
    }
     public static UsersSkins parse(int type) {
        switch(type){
           
            case 1:
                return darkSkin;
            case 0:
                return defaultSkin;
            default:
                throw new AssertionError();
        }
    }
      public Map toMap(){
        Map result = new LinkedHashMap();
        for(UsersSkins mt: UsersSkins.values())
            result.put(mt.toString(), mt);
        
        return result;
    }
}
