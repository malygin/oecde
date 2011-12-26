package org.sgu.oecde.schedule;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ShihovMY
 */
public enum LessonType implements Serializable{
    practice("практика"),
    exam("экзамен"),
    lightExam("зачет"),
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
  public Map toMap(){
        Map result = new LinkedHashMap();
        for(LessonType mt: LessonType.values())
            result.put(mt.toString(), mt);
        
        return result;
    }

}
