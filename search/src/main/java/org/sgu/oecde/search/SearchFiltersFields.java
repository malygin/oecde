/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.search;

import java.util.HashMap;
import java.util.Hashtable;

import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.messages.Message;
//import org.sgu.oecde.messages..Message;

/**
 *
 * @author malygin
 * 
 */
public enum SearchFiltersFields {
    teacher(Teacher.class,"преподаватель", 
            new String[]{"name", "secondName", "surname"}),
    student(AbstractStudent.class,"студент",
            new String[]{"name","secondName","surname"}),
    umk(Umk.class,"умк",
            new String[]{"name"}),
    message(Message.class,"сообщения",
            new String[]{"theme", "fulltext"}),
    group(StudentGroup.class,"группа",
            new String[]{"name"});
    
    private Class className;
    private String rusName;
    private String[] availbaleFields;
    private String[] selectedFileds;
    private HashMap<String,String> additionalFields=new HashMap<String, String>();
    
    private SearchFiltersFields(Class className,String rusName, String[] availbaleFields) {
        this.className = className;
        this.rusName = rusName;
        this.availbaleFields=availbaleFields;
        this.selectedFileds=availbaleFields;
    }
    
    public  SearchFiltersFields[] getStudentValues(){
        SearchFiltersFields[] studentList={teacher, student, message};
        return studentList;
    }
    
    public String toClass(){
        return className.getName();
    }
    
    @Override
    public String toString() {
        return rusName;
    }

    public String[] getAvailbaleFields() {        
        return availbaleFields;
    }

    public String[] getSelectedFileds() {
        return selectedFileds;
    }

    public void setSelectedFileds(String[] selectedFileds) {
        this.selectedFileds = selectedFileds;
    }

    public HashMap<String, String> getAdditionalFields(AbstractUser user, Boolean restrict) {
        switch(this){
           case message:
               if ((user instanceof AbstractStudent) || restrict){
                   additionalFields.put("author", user.getId().toString());                   
               }
               break;               
        }
        return additionalFields;
    }
    
    
    
    
    
}
