package org.sgu.oecde.web.jsfbeans.supervisor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="oneCityGroupsBean")
@SessionScoped
public class OneCityGroupsBean implements Serializable{

    @ManagedProperty(value="#{supervisorSessionBean}")
    SupervisorSessionBean supervisorSessionBean;

    List<NewEntry<Speciality,List<Group>>>specialities;

    private static final long serialVersionUID = 184L;

    @Secured("ROLE_SUPERVISOR")
    public List<NewEntry<Speciality,List<Group>>> getSpecialities(){
        if(specialities == null){
            List<Group>l = supervisorSessionBean.getGroups();
            Speciality s = null;
            NewEntry<Speciality,List<Group>>specialityEntry = null;
            List<NewEntry<Speciality,List<Group>>> specList = new ArrayList<NewEntry<Speciality, List<Group>>>();
            List<Group>groupList = null;
            for(Group g:l){
                if(g.getSpeciality()!=s){
                    specialityEntry = new NewEntry<Speciality, List<Group>>(g.getSpeciality(), groupList);
                    specList.add(specialityEntry);
                    groupList = new ArrayList<Group>();
                }
                groupList.add(g);
                s = g.getSpeciality();
            }
        }
        return specialities;
    }

}
