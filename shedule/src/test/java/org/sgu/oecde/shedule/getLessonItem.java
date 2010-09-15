package org.sgu.oecde.shedule;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.Semesters;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.shedule.dao.ILessonDao;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author shihovmy
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class getLessonItem extends BasicTest{


    IBasicDao<Teacher>  teacherDao;
    ICurriculumDao<DeCurriculum> sdsyDao;

    @Before
    public void setBean(){
        teacherDao = (IBasicDao) getBean("teacherDao");
        sdsyDao = (ICurriculumDao<DeCurriculum>) getBean("curriculumDao");
    }

    @Ignore
    @Test
    public void getAll(){
        super.getAllItems();
    }

    @Ignore
    @Test
    public void  putLesson(){
        Teacher e = new Teacher();
        e.setSurname("ИВАНОВ");
        Teacher t = teacherDao.getByExample(e).get(0);
        List<DeCurriculum> li = sdsyDao.getBySemesterYearAndParameters(Semesters.winter(), 2009, t);
        DeCurriculum s = li.iterator().next();
        Set<Group> cis = new HashSet<Group>();
        int number = 0;
        List<Group> cssIs = sdsyDao.getGroupsForTeacher(Semesters.winter(), 2009, t);
        System.out.println(cssIs.size());
        for(Iterator<Group> it = cssIs.iterator();it.hasNext();){
            Group css = it.next();
            number+=css.getNumber();
        }
        cis= new HashSet<Group>(cssIs);
        Lesson l = new Lesson();
        l.setLessonDate("05.02.10 16:38:21");
        l.setGroups(cis);
        l.setNumber(number);
        l.setRoom(1);
        l.setCurriculum(s);
        l.setTeacher(t);
        setDao("lessonDao");
        this.<ILessonDao>getDao().saveLesson(l);
        System.out.println("ok!");
    }

    @Ignore
    @Test
    public void getLesson(){
        Lesson l = super.getItem(27L);
        System.out.println(l.getClass().getTypeParameters().length);
        System.out.println(l.getClass().getGenericSuperclass());
        for(Object c:l.getClass().getTypeParameters()){
            System.out.println("s   "+c);
        }
//        for(Group c:l.getCss()){
//            System.out.println("s   "+c.getCity().getName());
//        }
//        System.out.println(l.getNumber());
//        for(Group c:l.getCss()){
//            System.out.println("s   "+c.getCity().getName());
//        }
    }

    @Ignore
    @Test
    public void getLessonCount(){
        Lesson l = new Lesson();/*
        Teacher t = new Teacher();
        t.setId(44240);
        l.setTeacher(t);*/
       /* Group css = new Group();
        css.setId(13227);
        Set<Group> set = new HashSet();
        set.add(css);
        l.setCss(set);
        l.setRoom(2);
        DeCurriculum sdsy = new DeCurriculum();
        sdsy.setId(927175);
        l.setSdsy(sdsy);*/
           l.setLessonDate("24.04.10");
        setDao("lessonDao");       
        System.out.println("sd   "+((ILessonDao)getDao()).getLessonCount(l));
    }

    @Ignore
    @Test
    public void getItem(){
        Lesson l =super.getItem(75L);
        System.out.println("s    "+l.getLessonDate()+"   "+l.getUpdateDate());
    }

   @Ignore
    @Test
    public void getItemByItem(){
        Lesson l = new Lesson();
        l.setLessonDate("24.04.10");
        System.out.println("s    "+this.<Lesson>getByExample(l).get(0).getRoom());
    }
    @Ignore
    @Test
    public void DeleteItem(){
        Lesson l = new Lesson();
         l.setId(128L);
         ((ILessonDao)getDao()).deleteLesson(l);
    }
    
//      @Ignore
    @Test
    public void getListByMonth() throws DataAccessException, ParseException{
        setDao("lessonDao");
        List<Lesson> l= this.<ILessonDao>getDao().getListByMonth("2010", "02");
        System.out.println("!! "+l);
        for(Lesson c:l){
            System.out.println("-- "+c.getLessonDate());

        }
    }
}
