/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education.estimation.activity;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Nov 28, 2011
 * 
 */
public enum ActivityEstimateNames implements IEstimate {
    activity_points,
    samAud_points,
    samOutAud_points,
    personChar_points,
    publish_points,
    lec_points,
    activity;
    
   private static final long serialVersionUID = 91L;
    @Override
    public String toString() {
        switch (this) {
            case activity_points:
                return "активность баллы";
            case samAud_points:
                return "Самостоятельная аудиторная работа баллы";
            case samOutAud_points:
                return "Самостоятельная внеаудиторная работа баллы";
            case personChar_points:
                return "Личностная характеристика баллы";
            case publish_points:
                return "Научные публикации";
            case lec_points:
                return "Лекции";
            case activity:
                return "активность";
            default:
                return "";
        }
    }
}
