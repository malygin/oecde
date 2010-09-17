package org.sgu.oecde.controlworks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.sgu.oecde.controlworks.dao.ControlWorkAttemptDao;
import org.sgu.oecde.controlworks.dao.IControlWorkAttemptDao;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * сервис по работе с контрольными работами.
 * @author ShihovMY
 */
@Service
public class ControlWorkService implements Serializable{

    /**
     * кр дао
     */
    @Autowired
    IControlWorkDao<ControlWork> controlWorkDao;
    /**
     * кр дао
     */
    @Autowired
    IControlWorkAttemptDao controlWorkAttemptDao;
    /**
     * кр попытка дао
     */
    @Autowired
    ICurriculumDao<AdvancedCurriculum> curriculumDao;

    private static final long serialVersionUID = 148L;

    private ControlWorkService() {
    }

    /**
     * для всех данных учебных планов получает контрольные работы данного студента и возрващает
     * Map со всеми планами и кр, если есть
     * @param <K> extends Curriculum
     * @param <V> extends ControlWork
     * @param student студент
     * @param curriculums планы
     * @return учебные планы и контрльные работы по ним
     */
    @SuppressWarnings({"unchecked"})
    public <K extends Curriculum,V extends ControlWork>Map<K,V> getStudensControlWorks(AbstractStudent student, List<? extends Curriculum> curriculums){
        List<AbstractStudent>students = ListUtil.oneItemList(student);
        List<ControlWork> list = controlWorkDao.getByStudentsAndCurriculums(curriculums, students,null);
        Map<K,V>map = new LinkedHashMap<K, V>();
        if(CollectionUtils.isEmpty(list))
            return map;
        for(Curriculum c:curriculums){
            Iterator<ControlWork>i = list.iterator();
            while(i.hasNext()){
                ControlWork w = i.next();
                if(w!=null&&w.getCurriculum()!=null&&w.getStudent()!=null){
                    if(w.getCurriculum().equals(c)&&w.getStudent().equals(student)){
                        i.remove();
                        map.put((K) c, (V) w);
                    }
                }
            }
        }
        return map;
    }

    /**
     * для всех студентов получает контрольные работы данного учебного плана и возрващает
     * Map со всеми планами и кр
     * @param <T> extends AbstractStudent
     * @param <V> extends ControlWork
     * @param students студенты
     * @param curriculum учебный план
     * @return студенты и кр
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractStudent,V extends ControlWork>Map<T, V>getCurriculumControlWorks(List<? extends AbstractStudent> students, Curriculum curriculum){
        List<Curriculum>curriculums = ListUtil.oneItemList(curriculum);
        return getAllControlWorks(students,curriculums);
    }

    /**
     * для всех студентов по всем данным дисциплинам получает кр, если есть
     * @param <T> extends AbstractStudent
     * @param <V> extends ControlWork
     * @param students студенты
     * @param curriculum учебные планы
     * @return студенты и кр
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractStudent,V extends ControlWork>Map<T, V>getAllControlWorks(List<? extends AbstractStudent> students,List<? extends Curriculum> curriculums){
        List<ControlWork> list = controlWorkDao.getByStudentsAndCurriculums(curriculums,students,null);
        Map<T,V>map = new LinkedHashMap<T, V>();
        for(AbstractStudent s:students){
            for(Curriculum curriculum:curriculums){
                Iterator<ControlWork>i = list.iterator();
                map.put((T) s, null);
                while(i.hasNext()){
                    ControlWork w = i.next();
                    if(w!=null&&w.getCurriculum()!=null&&w.getStudent()!=null){
                        if(w.getCurriculum().equals(curriculum)&&w.getStudent().equals(s)){
                            i.remove();
                            map.put((T) s, (V) w);
                        }
                    }
                }
            }
        }
        return map;
    }

    /**
     * сохраняет пустую кр, что является отметкой об отправки кр в рукописном
     * @param work
     */
    public void saveEmptyCw(ControlWork work) throws DataAccessException{
        if(work!=null){
            ControlWorkAttempt a = new ControlWorkAttempt();
            a.setFilePath("empty");
            a.setAttemptDate(DateConverter.currentDate());
            a.setWork(work);
            List<ControlWorkAttempt>set = null;
            if(CollectionUtils.isEmpty(work.getCwAttempt())){
                set = new ArrayList<ControlWorkAttempt>(1);
                work.setCwAttempt(set);
            }else{
                set = (List<ControlWorkAttempt>) work.getCwAttempt();
            }
            set.add(a);
            controlWorkDao.save(work);
        }
    }

    /**
     * получает все учебные планы, что соотвествуют образцу, а так же имеют отметку о контрольной работе
     * @param <T> extends AdvancedCurriculum
     * @param example учебный план-образец
     * @return дополненный учебный план
     * @see org.sgu.oecde.core.IBasicDao#getByExample(org.sgu.oecde.core.BasicItem) 
     */
    public <T extends AdvancedCurriculum>List<T> getCurriculumsWithControlWorks(AdvancedCurriculum example){
        Assert.notNull(example);
        example.setGotControlWork(true);
        return (List<T>) curriculumDao.getByExample(example);
    }

    public void save(ControlWork w) throws DataAccessException{
        w.setDate(DateConverter.currentDate());
        controlWorkDao.save(w);
    }

    public void setWorkRead(ControlWorkAttempt a) throws DataAccessException{
        Assert.notNull(a);
        a.setRead(true);
        controlWorkAttemptDao.update(a);
    }
}
