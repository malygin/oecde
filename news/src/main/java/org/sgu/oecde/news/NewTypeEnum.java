/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.news;

/**
 *
 * @author malygin
 */
public enum NewTypeEnum {
    
    forStudent,
    forTeacher,
    forStudentAndTeacher,
    forAdmin,
    forAll;
    
   private static final long serialVersionUID = 93L;

     @Override
    public String toString() {
        switch(this){
            case forStudent:
                return "только для студентов";
            case forTeacher:
                return "только для преподавателей";
            case forStudentAndTeacher:
                return "для студентов и преподавателей";
            case forAdmin:
                return "только для админов";         
            default:
            case forAll:
                return "для всех";
        }
    }

    public String toName(){
        switch(this){
            case forStudent:
                return "forStudent";
            case forTeacher:
                return "forTeacher";
            case forStudentAndTeacher:
                return "forStudentAndTeacher";
            case forAdmin:
                return "forAdmin";         
            default:
            case forAll:
                return "forAll";
        }
    }

    public static NewTypeEnum parse(String name){
        if("forStudent".equals(name))
            return forStudent;
        else if("forTeacher".equals(name))
            return forTeacher;
        else if("forStudentAndTeacher".equals(name))
            return forStudentAndTeacher;
        else if("forAdmin".equals(name))
            return forAdmin;      
        else
            return forAll;
    }

    public String getName(){
        return toString();
    }
    
}
