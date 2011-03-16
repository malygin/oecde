package org.sgu.oecde.tabs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ShihovMY
 */
public enum TabType implements Serializable{
    
    STUDENT_INFORMATION("Информация для студентов",false),
    STUDENT_HELP("Студенческая помощь",false),
    INDEX_PAGE("Главная страница"),
    TEACHER_INFORMATION("Информация для преподавателей"),
    TEACHER_HELP("Преподавательская помощь",false);

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
            if(t.allow)
                l.add(t);
        }
        return l;
    }

    private static final long serialVersionUID = 160L;

    @Override
    public String toString() {
        return getName();
    }
}
