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
    activity;
   private static final long serialVersionUID = 91L;
    @Override
    public String toString() {
        switch (this) {
            case activity_points:
                return "активность баллы";
            case activity:
                return "активность";
            default:
                return "";
        }
    }
}
