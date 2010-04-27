package org.sgu.oecde.core.education.estimation;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public class EstimatedWorkPoints implements Serializable{
    private IEstimate name;
    private Integer points = 0;
    private static final long serialVersionUID = 77L;

    public EstimatedWorkPoints(IEstimate name) {
        this.name = name;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public IEstimate getName() {
        return name;
    }

    public void setName(IEstimate name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstimatedWorkPoints other = (EstimatedWorkPoints) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
}
