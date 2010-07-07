package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.de.users.Group;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teachersGroups")
@ViewScoped
public class TeachersGroups extends AbstractTeacherBean{

    private Map<Speciality,List<Group>>groupsMap;

    private static final long serialVersionUID = 111L;
    
    public Map<Speciality,List<Group>> getTeachersGroups(){
        if(groupsMap==null){
            groupsMap = new HashMap<Speciality, List<Group>>();
            List<Group>groups = curriculumDao.<Group>getGroupsForTeacher(semesters(), year(),teacher);
            if(!CollectionUtils.isEmpty(groups)){
                Collections.sort(groups, new SortGroup());
                List<Group>specialityGroups = null;
                Speciality s = null;
                for(Group g:groups){
                    if(!g.getSpeciality().equals(s)){
                        specialityGroups = new LinkedList<Group>();
                        groupsMap.put(g.getSpeciality(), specialityGroups);
                    }
                    specialityGroups.add(g);
                    s = g.getSpeciality();
                }
            }
        }
        return groupsMap;
    }

    public void setSemester(int semester) {
        super.setSemester(semester);
        groupsMap =null;
    }

    private class SortGroup implements Comparator<Group>{

        @Override
        public int compare(Group o1, Group o2) {
            int speciality = 0;
            int name = 0;
            if(o1!=null&&o2!=null){
                if(o1.getSpeciality()!=null&&o1.getSpeciality().getId()!=null&&o2.getSpeciality()!=null)
                    speciality = o1.getSpeciality().getId().compareTo(o2.getSpeciality().getId());
                if(o1.getName()!=null)
                    name = o1.getName().compareTo(o2.getName());
            }
            return speciality==0?(name):speciality;
        }
    }
}
