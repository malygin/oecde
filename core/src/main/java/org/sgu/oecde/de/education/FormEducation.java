/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.de.education;

/**
 *
 * @author malygin
 */
public enum FormEducation {
    fullTime,
    correspondence,
    distanceCorrespondence;
    private static final long serialVersionUID = 89L;

    @Override
    public String toString() {
        switch(this){
            case fullTime:
                return "Очная форма обучения";
            case correspondence:
                return "Заочная форма обучения";
            case distanceCorrespondence:
                return "Заочно-дистанционная форма обучения ";                
            default:
                 return "Заочно-дистанционная форма обучения ";  
        }
    }

    public int toInt() {
        switch(this){
            case fullTime:
                return 3;
            case correspondence:
                return 2;
            case distanceCorrespondence:
                return 1;
            default:
                return 1;
        }
    }

    public static FormEducation parse(int type) {
        switch(type){
            case 3:
                return fullTime;
            case 2:
                return correspondence;
            case 1:
                return distanceCorrespondence;
            default:
                return distanceCorrespondence;
        }
    }
  

    
    
}
