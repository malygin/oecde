package org.sgu.oecde.controlworks;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.DateConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class ControlWorkService implements InitializingBean{

    IControlWorkDao<ControlWork> dao;
    ICurriculumDao<AdvancedCurriculum> cDao;

    public <K extends Curriculum,V extends ControlWork>Map<K,V> getStudensControlWorks(AbstractStudent student, List<? extends Curriculum> curriculums){
        List<AbstractStudent>students = new LinkedList<AbstractStudent>();
        students.add(student);
        List<ControlWork> list = dao.getByStudentsAnsCurriculums(curriculums, students,null);
        Map<K,V>map = new LinkedHashMap<K, V>();
        for(Curriculum c:curriculums){
            ControlWork tmp = new ControlWork(student, c);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put((K) c, (V) tmp);
        }
        return map;
    }

    @SuppressWarnings({"unchecked", "element-type-mismatch"})
    public <T extends AbstractStudent,V extends ControlWork>Map<T, V>getCurriculumControlWorks(List<? extends AbstractStudent> students, Curriculum curriculum){
        List<Curriculum>curriculums = new LinkedList<Curriculum>();
        curriculums.add(curriculum);
        List<ControlWork> list = dao.getByStudentsAnsCurriculums(curriculums,students,null);
        Map<T,V>map = new LinkedHashMap<T, V>();
        for(AbstractStudent s:students){
            ControlWork tmp = new ControlWork(s, curriculum);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put((T)s, (V)tmp);
        }
        return map;
    }

    public void saveEmptyCw(ControlWork work){
        if(work!=null){
            ControlWorkAttempt a = new ControlWorkAttempt();
            a.setFilePath("empty");
            a.setAttemptDate(DateConverter.currentDate());
            a.setWork(work);
            Set set = new HashSet();
            set.add(a);
            work.setCwAttempt(set);
            dao.save(work);
        }
    }

    public <T extends AdvancedCurriculum>List<T> getCurriculumsWithControlWorks(AdvancedCurriculum example){
        example.setGotControlWork(true);
        return (List<T>) cDao.getByExample(example);
    }

    public void setcDao(ICurriculumDao<AdvancedCurriculum> cDao) {
        this.cDao = cDao;
    }

    public void setDao(IControlWorkDao<ControlWork> dao) {
        this.dao = dao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cDao);
        Assert.notNull(dao);
    }
}
