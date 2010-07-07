package org.sgu.oecde.de;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ConstantsDao;
import org.sgu.oecde.core.education.dao.CurriculumDao;
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
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class getSimpleItem extends BasicTest{

    @Ignore
    @Test
    public void makeDepartment(){
        this.<DeCurriculum>setDao("curriculumDao");
        ICurriculumDao<DeCurriculum> dao = (ICurriculumDao) this.getDao();
        DeCurriculum c = new DeCurriculum();
        c.setCalendarYear(2009);
        List<DeCurriculum> l =dao.getCurriculumsByModSemester(c, Semesters.summer());
        for(Curriculum cu:l){
            System.out.println(cu.getUmk().getName());
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

    @Ignore
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
        System.out.println(getByExample(new DeCurriculum()).size());
//        List<DeCurriculum> l= this.<CurriculumDao>getDao().getCurriculumsByModSemester(new DeCurriculum(2008610834L), Semesters.summer());
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
