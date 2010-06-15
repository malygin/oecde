package org.sgu.oecde.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ConstantsDao;
import org.sgu.oecde.core.education.dao.IConstantsDao;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.resource.Author;
import org.sgu.oecde.core.education.resource.Image;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.SemesterGetter;
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
    public void getSt(){
        this.setDao("userDao");
         System.out.println(this.<AbstractUser>getByExample(AbstractUser.getUserWithName("belousovyae")));
    }

    @Ignore
    @Test
    public void getResults(){
        this.setDao("resultDao");
        for(AbstractResult r: this.<AbstractResult>getAllItems()){
            System.out.println(r.getCurriculum()+"   "+r);
        }
    }

//    @Ignore
    @Test
    public void getConsts(){
        SemesterGetter g = getBean("semesterGetter");
        System.out.println(g.getCurrentYear());
        System.out.println(g.getCurrentSemester());
    }

    @Ignore
    @Test
    public void getCurr(){
        setDao("curriculumDao");
        for(BasicItem b:getAllItems()){
            System.out.println(b.getClass()+"   "+b.getId());
        }
    }

    @Ignore
    @Test
    public void getAuth(){
        setDao("authorDao");
        List<Author> l = this.<Author>getAllItems();
        for(Author b:l){
            System.out.println(b.getTeacher()+"   "+b.getSurname()+"   "+b.getId());
        }
    }
}
