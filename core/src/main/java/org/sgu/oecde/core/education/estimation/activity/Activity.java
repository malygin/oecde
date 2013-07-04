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
    private Integer samAudWorkpoints;
    private Integer samOutAudWorkpoints;
    private Integer personalCharpoints;
    private Integer publishpoints;
    private Integer lecpoints;



    public Activity() {
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
       if (points==null || points>100 || points<0)
           this.points = 0 ;
       else  this.points = points;
    }

    public Integer getSamAudWorkpoints() {
        return samAudWorkpoints;
    }

    public void setSamAudWorkpoints(Integer samAudWorkpoints) {
        if (samAudWorkpoints==null || samAudWorkpoints>100 || samAudWorkpoints<0)
            this.samAudWorkpoints=0;
        else  this.samAudWorkpoints = samAudWorkpoints;
    }

    public Integer getPersonalCharpoints() {
        return personalCharpoints;
    }

    public void setPersonalCharpoints(Integer personalCharpoints) {
       if (personalCharpoints==null || personalCharpoints>100 || personalCharpoints<0)
            this.personalCharpoints=0;
        else  this.personalCharpoints = personalCharpoints;
    }

    public Integer getSamOutAudWorkpoints() {
        return samOutAudWorkpoints;
    }

    public void setSamOutAudWorkpoints(Integer samOutAudWorkpoints) {
       if (samOutAudWorkpoints==null || samOutAudWorkpoints>100 || samOutAudWorkpoints<0)
            this.samOutAudWorkpoints=0;
        else  this.samOutAudWorkpoints = samOutAudWorkpoints;
    }

    public Integer getPublishpoints() {
        return publishpoints;
    }

    public void setPublishpoints(Integer publishpoints) {
        this.publishpoints = publishpoints;
    }

    public Integer getLecpoints() {
        return lecpoints;
    }

    public void setLecpoints(Integer lecpoints) {
        this.lecpoints = lecpoints;
    }
}

    