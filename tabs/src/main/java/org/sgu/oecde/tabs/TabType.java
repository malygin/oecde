package org.sgu.oecde.tabs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ShihovMY
 */
public enum TabType implements Serializable{
    
    STUDENT_INFORMATION("Информация для студентов",true),
    STUDENT_HELP("Студенческая помощь",false);

    private String name;
    private boolean singleton;
    private boolean allow = true;

    private TabType(String name, boolean singleton) {
        this.name = name;
        this.singleton = singleton;
    }

    protected void checkIntstantiation() {
        if(this.singleton)
            allow = false;
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
