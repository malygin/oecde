package org.sgu.oecde.search;

import java.lang.reflect.Field;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.messages.Message;

/**
 *
 * @author ShihovMY
 */
public enum SearchType {
    teacher(Teacher.class,"преподаватель"),
    student(AbstractStudent.class,"студент"),
    umk(Umk.class,"умк"),
    message(Message.class,"сообщения"),
    group(StudentGroup.class,"группа");

    private SearchType(Class className,String rusName) {
        this.className = className;
        this.rusName = rusName;
    }

    private Class className;
    private String rusName;

    public String toClass(){
        return className.getName();
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
            case message:
                String [] str_message = new String[1];
                str_message[0]="theme";
                //str_message[1]="fulltext";
                return str_message;
                
                
            case umk:
            default:
                return new String[]{"name"};
        }
    }

    @Override
    public String toString() {
        return rusName;
    }
}
