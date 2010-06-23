package org.sgu.oecde.core.users;

/**
 * абстрактный студент. имеет ссылку на реальную студенческую группу, в которой он
 * учится, но при этом может находиться в других StudentGroup
 * @author ShihovMY
 */
public abstract class AbstractStudent extends AbstractPerson {

    /**
     * группа
     */
    private StudentGroup group;

    public AbstractStudent() {
    }

    /**
     *
     * @param <T> extends StudentGroup
     * @return группа
     */
    public<T extends StudentGroup> T getGroup() {
        return (T) group;
    }

    /**
     * группа
     * @param group
     */
    public void setGroup(StudentGroup group) {
        this.group = group;
    }
}
