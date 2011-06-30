package org.sgu.oecde.tabs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ShihovMY
 */
public enum TabType implements Serializable{
    
    INDEX_PAGE("Главная страница"),
    STUDENT_INFORMATION("Информация для студентов",false),
    STUDENT_HELP("Студенческая помощь",false),
    TEACHER_INFORMATION("Информация для преподавателей"),
    TEACHER_HELP("Преподавательская помощь",false),
    ADMIN_HELP("Преподавательская помощь",false),
    SUPERVISOR_HELP("Преподавательская помощь",false),
    STUDENTS_TIPS("Подсказки студентам", false),
    TEACHERS_TIPS("Подсказки преподавателям", false),
    ADMIN_TIPS("Подсказки админам", false)
    ;

    private String name;
    private boolean singleton = true;
    private boolean allow = true;

    private TabType(String name, boolean singleton) {
        this.name = name;
        this.singleton = singleton;
    }

    private TabType(String name) {
        this.name = name;
    }

    protected void checkIntstantiation() {
        if(this.singleton)
            allow = false;
    }

    public void allow(){
        if(this.singleton&&!allow)
            allow = true;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public String getName() {
        return name;
    }

    public static List<TabType>getAllowedTypes(){
        List<TabType>l = new ArrayList<TabType>();
        for(TabType t:values()){
//            if(t.allow)
                l.add(t);
        }
        return l;
    }

    private static final long serialVersionUID = 160L;

    @Override
    public String toString() {
        switch(this){
            case INDEX_PAGE:
                return "INDEX_PAGE";
            case STUDENT_INFORMATION:
                return "STUDENT_INFORMATION";
            case STUDENT_HELP:
                return "STUDENT_HELP";
            case TEACHER_INFORMATION:
                return "TEACHER_INFORMATION";
            case TEACHER_HELP:
                return "TEACHER_HELP";
            case ADMIN_HELP:
                return "ADMIN_HELP";
            case SUPERVISOR_HELP:
                return "SUPERVISOR_HELP";
            case STUDENTS_TIPS:
                return "STUDENTS_TIPS";
            case TEACHERS_TIPS:
                return "TEACHERS_TIPS";
            case ADMIN_TIPS:
                return "ADMIN_TIPS";

            default:
                throw new AssertionError();
        }
    }
}
