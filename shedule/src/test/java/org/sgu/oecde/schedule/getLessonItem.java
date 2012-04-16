package org.sgu.oecde.schedule;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.Semesters;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.schedule.dao.ILessonDao;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author shihovmy
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class getLessonItem extends BasicTest{


    IBasicDao<Teacher>  teacherDao;
    IBasicDao<Student>  studentDao;
    ICurriculumDao<DeCurriculum> sdsyDao;

    @Before
    public void setBean(){
        teacherDao = (IBasicDao) getBean("teacherDao");
        studentDao = (IBasicDao) getBean("studentDao");
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
        List<DeCurriculum> li = sdsyDao.getBySemesterYearAndParameters(Semesters.summer(), 2009, t);
        Discipline s = getDisciplines(li).iterator().next();
        Set<CityWithGroup> cis= new HashSet<CityWithGroup>();
        int number = 0;
        List<Group>cssIs = sdsyDao.getGroupsForTeacher(Semesters.summer(), 2009, t,null);
        System.out.println(cssIs.size());
        for(Iterator<Group> it = cssIs.iterator();it.hasNext();){
            Group css = it.next();
            number+=css.getNumber();
            CityWithGroup g = new CityWithGroup(((Student)css.getPersons().iterator().next()).getCity(), css);
            cis.add(g);
        }
        Lesson l = new Lesson();
        l.setLessonDate("05.02.10 16:38:21");
        l.setNumber(number);
        l.setCitiesWithGroups(cis);
        l.setRoom(1);
        l.setDiscipline(s);
        l.setTeacher(t);
        l.setYear(2009);
        l.setWinter(false);
        setDao("lessonDao");
        this.<ILessonDao>getDao().saveLesson(l);
        System.out.println("ok!");
    }

    private List<Discipline>getDisciplines(List<DeCurriculum>dl){
        List<Discipline> ds = new ArrayList<Discipline>();
        Discipline tmp = null;
        for(DeCurriculum d:dl){
            if(d.getDiscipline()!=tmp){
                ds.add(d.getDiscipline());
            }
            tmp = d.getDiscipline();
        }
        return ds;
    }

    @Ignore
    @Test
    public void getLessonForStudent(){
       Student student = studentDao.getById(320412l);
        setDao("lessonDao");
        List<Lesson> l=this.<ILessonDao>getDao().getLessonsForStudent(false,student.<Group>getGroup(),student.getCity(),30,1,"2011.07.01","2012.08.01");
        System.out.println(""+l.size());
        for(Lesson e:l){
            System.out.println("! "+e.getTeacher());
        }
        Long n=this.<ILessonDao>getDao().getLessonsCountForStudent(false, student.<Group>getGroup(), student.getCity(), null, null);
         System.out.println(""+n);        
    }
    
    @Ignore
    @Test
    public void getLessonForTeaher(){
       Teacher t = teacherDao.getById(44240l);
        setDao("lessonDao");
        List<Lesson> l=this.<ILessonDao>getDao().getLessonsForTeacher(false,t,30,1,"2011.07.01","2012.08.01");
        System.out.println(""+l.size());
        for(Lesson e:l){
            System.out.println("! "+e.getLessonDate());
        }
        Long n=this.<ILessonDao>getDao().getLessonsCountForTeacher(false, t, "2011.07.01","2012.08.01");
        System.out.println(""+n);        
    }
//    @Ignore
    @Test
    public void getLessonForAdmin(){
      // Teacher t = teacherDao.getById(44240l);
        setDao("lessonDao");
        List<Lesson> l=this.<ILessonDao>getDao().getLessonsForAdmin(30,1,null,null);
        System.out.println(""+l.size());
        for(Lesson e:l){
            System.out.println("! "+e.getLessonDate());
        }
        Long n=this.<ILessonDao>getDao().getLessonsCountForAdmin(null,null);
        System.out.println(""+n);        
    }
   
    
   @Ignore
    @Test
    public void getLessonByDate(){
        Lesson l=new Lesson();
        l.setLessonDate("2012.03.22");
      //  List<Group>cssIs = sdsyDao.getGroupsForTeacher(Semesters.summer(), 2010, t,new Discipline(2l));
        setDao("lessonDao");
        List<Lesson> list= this.<ILessonDao>getDao().getLessonsByDate(l);
         for(Lesson e:list){
            System.out.println("! "+e.getLessonDate()+" "+e.getTeacher());
        }

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
        setDao("lessonDao");
        Lesson l = getItem(286L);
        ((ILessonDao)getDao()).deleteLesson(l);
    }

      @Ignore
    @Test
    public void getListByMonth() throws DataAccessException, ParseException{
        setDao("lessonDao");
        List<Lesson> l= this.<ILessonDao>getDao().getListByMonth("2012", "03");
        System.out.println("!! "+l);
        for(Lesson c:l){
            System.out.println("-- "+c.getLessonDate());

        }
    }

      @Ignore
    @Test
    public void getByGroups() throws DataAccessException, ParseException{
        List gs = new ArrayList();
        Group g = new Group();
        g.setId(140928L);
        gs.add(g);
        setDao("lessonDao");
        List<Lesson> l= this.<ILessonDao>getDao().getLessonsByCity(new City(24l),false,2009,20,0,null,null);
        System.out.println("!! "+l);
        for(Lesson c:l){
            System.out.println("-- "+c.getLessonDate());

        }
    }
}
