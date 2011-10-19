package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
abstract class AbstractStudentsListBean extends AbstractTeacherCurriculumBean{

    private Long id;
    private List<Student>students;
    private Group group;

    public List<Student> getStudentsList(){
        return students==null?new ArrayList():students;
    }

    public List<DeCurriculum>getGroupCurriculums(Group group){
        List<DeCurriculum>l = new LinkedList();
        if(group==null)
            return l;
        Student tmp  = new Student();
        tmp.setGroup(group);
        List<DeCurriculum>dl = getTeacherSessionBean().getDisciplines(semester);
        for(DeCurriculum d:dl){
            if(d.getSpeciality().equals(group.getSpeciality())&&d.getSemester().equals(semesterGetter.getSemesterByStudentYear(tmp, semester)))
                l.add(d);
        }
        return l;
    }

    public List<DeCurriculum>getDisciplines(){
        return getGroupCurriculums(group);
    }

    public DeCurriculum getCurriculum(){
        DeCurriculum c = null;
        if(super.getCurriculum()==null){
            List<DeCurriculum>l = getDisciplines();
            if(!CollectionUtils.isEmpty(l)){
                if(c == null){
                    c = l.get(0);
                }
            }
            setAccessDenied(c==null);
        }else
            return super.getCurriculum();
        return c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
        students = null;
        List<Group>l = getTeacherSessionBean().getGroups(semester);
        for(Group g:l){
            if(g!=null&&g.getId()!=null&&id!=null&&g.getId().equals(id)&&id!=0){
                students = new ArrayList(g.getPersons());
                Collections.sort(students, new Student.OrderByStudentName());
                deleteUnActiveStudent(students);
                if(group==null)
                    group = g;
            }
        }
        setAccessDenied(students==null);
    }

    
    private void deleteUnActiveStudent (List<Student> st){
      Iterator<Student>it=st.iterator();
      while(it.hasNext()){
          Student s=it.next();
          if (! s.isEnabled()) it.remove();
      }
        
    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
