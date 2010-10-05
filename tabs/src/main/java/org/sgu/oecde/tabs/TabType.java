package org.sgu.oecde.tabs;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public enum TabType implements Serializable{
    STUDENT_INFORMATION,
    STUDENT_HELP;
    private static final long serialVersionUID = 160L;

    @Override
    public String toString() {
        switch(this){
            case STUDENT_HELP:
                return "Студенческая помощь";
            case STUDENT_INFORMATION:
                return "Информация для студентов";
            default:
                throw new AssertionError();
        }
    }
}
