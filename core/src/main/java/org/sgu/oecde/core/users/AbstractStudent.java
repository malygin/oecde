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

    /**
     * переведён/не переведён на следующий год
     */
    private Boolean transfered;
    
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

    /**
     * 
     * @return переведён/не переведён на следующий год
     */
    public Boolean getTransfered() {
        return transfered;
    }

    /**
     * переведён/не переведён на следующий год
     * @param transfered
     */
    public void setTransfered(Boolean transfered) {
        this.transfered = transfered;
    }
}
