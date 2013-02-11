package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupBean")
@ViewScoped
public class StudentGroupList extends StudentCurriculumBean{

    private List<Student>students;

    private static final long serialVersionUID = 104L;

    public List<Student>getStudents(){
        if(students==null){
            students = new ArrayList<Student>(student.<Group>getGroup().getPersons());
//            students.remove(student);
            Collections.sort(students);
        }
        return students;
    }

    public int getStudentNumber(){
         return getStudents().indexOf(student)+1;
//        return 0;
    }


}
