/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.resource.Image;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.Semesters;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
public class getSimpleItem extends BasicTest{

    @Ignore
    @Test
    public void getF(){
        this.<StudentGroup>setDao("studentDao");
        Student e = new Student();
        e.setName("иван");
        Set<AbstractStudent> l = this.<Student>getByExample(e).get(0).getGroup().getPersons();
    }

    @Ignore
    @Test
    public void makeDepartment(){
        this.<Curriculum>setDao("curriculumDao");
        ICurriculumDao<DeCurriculum> dao = (ICurriculumDao) this.getDao();
        DeCurriculum c = new DeCurriculum();
        c.setCalendarYear(2009);
        List<DeCurriculum> l =dao.getCurriculumsByModSemester(c, Semesters.summer());
        for(DeCurriculum cu:l){
            System.out.println(cu.getDiscipline().getName());
        }
    }

    @Ignore
    @Test
    public void getCurr(){
        this.setDao("userDao");
         System.out.println(this.<AbstractUser>getByExample(AbstractUser.getUserWithName("belousovyae")));
    }
 
}
