package org.sgu.oecde.search;

import java.lang.reflect.Field;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.Teacher;

/**
 *
 * @author ShihovMY
 */
public enum SearchType {
    teacher,student,umk;
    public String toClass(){
        switch(this){
            case student:
                return AbstractStudent.class.getName();
            case teacher:
                return Teacher.class.getName();
            case umk:
                return Umk.class.getName();
            default:
                return null;
        }
    }

    public String[] getSearchableFields(){
        switch(this){
            case student:
            case teacher:
                String[] str = new String[3];
                Field[] fields = AbstractPerson.class.getDeclaredFields();
                for(int i=0;i<3;i++){
                    str[i] = fields[i].getName();
                }
                return str;
            case umk:
                return new String[]{"name"};
            default:
                return null;
        }
    }
}
