package org.sgu.oecde.schedule;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public enum LessonType implements Serializable{
    practice("практика"),
    exam("экзамен/зачёт"),
    consult;
    private static final long serialVersionUID = 216L;

    private LessonType(String name) {
        this.name = name;
    }

    private LessonType() {
    }

    private String name = "консультация";

    @Override
    public String toString() {
        return name;
    }


}
