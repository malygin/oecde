package org.sgu.oecde.core;

import java.util.ArrayList;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.education.CalendarConstantName;
import java.util.List;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.core.util.Semesters;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.dao.IGroupDao;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author ShihovMY
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class DeTests extends BasicTest{

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

//    @Ignore
    @Test
    public void getSt(){
        this.setDao("userDao");
        Student s = getItem(324725l);
        for(Student st:s.<Group>getGroup()){
            System.out.println(st);
        }

    }

    @Ignore
    @Test
    public void getResults(){
        this.setDao("resultDao");
        for(AbstractResult r: this.<AbstractResult>getAllItems()){
            System.out.println(r+"   "+r);
        }
    }

//    @Ignore
    @Test
    public void getConsts(){
        SemesterGetter g = getBean("semesterGetter");
        g.save(CalendarConstantName.semester,"1",true);
        System.out.println(g.getCurrentYear());
        System.out.println(g.getCurrentSemester());
    }

    @Ignore
    @Test
    public void getCurr(){
        setDao("curriculumDao");
        System.out.println(getByExample(new DeCurriculum()).size());
//       List<DeCurriculum> l= this.<CurriculumDao>getDao().getCurriculumsByModSemester(new DeCurriculum(2008610834L), Semesters.summer());
    }

//    @Ignore
    @Test
    public void getAuth(){
        setDao("groupDao");
        City c = new City();
        c.setId(23L);
        List<Group> d = this.<IGroupDao>getDao().getGroupsByCity(c);
        System.out.println(d);
    }

//    @Ignore
    @Test
    public void getCur(){
        this.setDao("userDao");
        Student st = getItem(324613L);

        setDao("groupDao");
        Group d = this.<IGroupDao>getDao().getTeachersAndCurriculumsByOldGroup(2, st);
        System.out.println(d);
    }

    @Ignore
    @Test
    public void saveRes(){
        Estimate e1 = new Estimate();
        Estimate e2 = new Estimate();
        this.setDao("userDao");
        Student st1 = getItem(324607L);
        Student st2 = getItem(324718L);
        setDao("curriculumDao");
        DeCurriculum c = getItem(200947525L);
        e1.setCurriculum(c);
        e2.setCurriculum(c);
        e1.setStudent(st2);
        e2.setStudent(st1);
        e1.setGradeCode(PointToEstimate.five);
        e2.setGradeCode(PointToEstimate.five);
        e1.setDate(DateConverter.currentDate());
        e2.setDate(DateConverter.currentDate());
        List<Estimate>es = new ArrayList<Estimate>(2);
        es.add(e2);
        es.add(e1);
        setDao("estimateDao");
        for(Estimate e:es){
            this.<IEstimateDao>getDao().save(e);
        }
    }
}
