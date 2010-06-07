package org.sgu.oecde.selfdependentwork;

import org.sgu.oecde.controlworks.ControlWork;

/**
 *
 * @author ShihovMY
 */
public class SelfDependentWork extends ControlWork{
    private Integer number;
    private static final long serialVersionUID = 93L;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SelfDependentWork other = (SelfDependentWork) obj;
        if (this.number == other.number) {
            return super.equals(obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.number + super.hashCode();
        return hash;
    }
}
