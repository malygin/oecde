/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

/**
 *
 * @author ShihovMY
 */
public abstract class AbstractStudent extends AbstractPerson {

    private StudentGroup group;

    public AbstractStudent() {
    }

    public<T extends StudentGroup> T getGroup() {
        return (T) group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }
}
