package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teachersGroups")
@ViewScoped
public class TeachersGroups extends AbstractTeacherBean{

    private List<NewEntry<Speciality,List<Group>>>groupsMap;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    private static final long serialVersionUID = 111L;
    
    public List<NewEntry<Speciality,List<Group>>> getTeachersGroups(){
        if(groupsMap==null){
            groupsMap = new ArrayList<NewEntry<Speciality, List<Group>>>();
            List<Group>groups = teacherSessionBean.getGroups(semester);
            if(!CollectionUtils.isEmpty(groups)){
                Collections.sort(groups, new SortGroup());
                List<Group>specialityGroups = null;
                Speciality s = null;
                for(Group g:groups){
                    if(!g.getSpeciality().equals(s)){
                        specialityGroups = new LinkedList<Group>();
                        groupsMap.add(new NewEntry<Speciality, List<Group>>(g.getSpeciality(), specialityGroups));
                    }
                    specialityGroups.add(g);
                    s = g.getSpeciality();
                }
            }
        }
        return groupsMap;
    }

    public void setGroupsMap(List<NewEntry<Speciality, List<Group>>> groupsMap) {
        this.groupsMap = groupsMap;
    }

    public void changeSemester(AjaxBehaviorEvent event){
        Long w = (Long) event.getComponent().getAttributes().get("semester");
        setSemester(w!=null?w.intValue():0);
        groupsMap =null;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
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
