/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.education.estimation.activity;

import org.sgu.oecde.core.education.work.AbstractResult;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Nov 28, 2011
 * Активность, за которую можно добавить баллы
 */
public class Activity extends AbstractResult{
    
    /**
     * баллы
     */
    private Integer points;

    public Activity() {
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
    
    
    
}

    