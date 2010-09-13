package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            students.remove(student);
            Collections.sort(students, new OrderByStudentName());
        }
        return students;
    }

    private class OrderByStudentName implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            int st = 0;
            if(o1!=null &&o2!=null && o1.getFio()!=null){
                st = o1.getFio().compareTo(o2.getFio());
            }
            return st;
        }

    }
}
