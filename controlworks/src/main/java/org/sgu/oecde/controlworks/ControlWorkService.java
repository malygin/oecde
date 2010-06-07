package org.sgu.oecde.controlworks;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
public class ControlWorkService {

    IControlWorkDao dao;
    ICurriculumDao cDao;

    public Map<? extends Curriculum,? extends ControlWork>getStudensControlWorks(AbstractStudent student, List<? extends Curriculum> curriculums){
        List<AbstractStudent>students = new LinkedList<AbstractStudent>();
        students.add(student);
        List<ControlWork> list = dao.getByStudentsAnsCurriculums(curriculums, students,null);
        Map<Curriculum,ControlWork>map = new LinkedHashMap<Curriculum, ControlWork>();
        for(Curriculum c:curriculums){
            ControlWork tmp = new ControlWork(student, c);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put(c, tmp);
        }
        return map;
    }

    public Map<? extends AbstractStudent,? extends ControlWork>getCurriculumControlWorks(List<? extends AbstractStudent> students, Curriculum curriculum){
        List<Curriculum>curriculums = new LinkedList<Curriculum>();
        curriculums.add(curriculum);
        List<ControlWork> list = dao.getByStudentsAnsCurriculums(students,curriculums,null);
        Map<AbstractStudent,ControlWork>map = new LinkedHashMap<AbstractStudent, ControlWork>();
        for(AbstractStudent s:students){
            ControlWork tmp = new ControlWork(s, curriculum);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put(s, tmp);
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

    public List<DeCurriculum> getCurriculumsWithControlWorks(DeCurriculum example){
        example.setControlWorksNumber(1);
        return cDao.getByExample(example);
    }

    public IControlWorkDao getDao() {
        return dao;
    }

    public void setDao(IControlWorkDao dao) {
        this.dao = dao;
    }
}
