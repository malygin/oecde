package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.List;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
abstract class AbstractStudentsListBean extends AbstractTeacherBean{

    private Long id;
    private List<Student>students;

    public final List<Student> getStudentsList(){
        return students==null?new ArrayList():students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Group gr = new Group(id);
        students = null;
        List<Group>l = curriculumDao.<Group>getGroupsForTeacher(semesters(), year(),teacher);
        for(Group g:l){
            if(gr.equals(g))
                students = new ArrayList(g.getPersons());
        }
        accessDenied = accessDenied?true:students==null;
    }
}
