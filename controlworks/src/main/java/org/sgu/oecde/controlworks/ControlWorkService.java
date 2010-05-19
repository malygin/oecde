package org.sgu.oecde.controlworks;

import java.util.HashMap;
import java.util.List;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
public class ControlWorkService {

    IControlWorkDao dao;
    ICurriculumDao cDao;

    public HashMap<Curriculum,ControlWork>getStudensControlWorks(AbstractStudent student, List<? extends Curriculum> curriculums){
        List<ControlWork> list = dao.getByStudentAndCurriculums(curriculums, student);
        HashMap<Curriculum,ControlWork>map = new HashMap<Curriculum, ControlWork>();
        for(Curriculum c:curriculums){
            ControlWork tmp = new ControlWork(student, c);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put(c, tmp);
        }
        return map;
    }

    public HashMap<AbstractStudent,ControlWork>getCurriculumControlWorks(List<? extends AbstractStudent> students, Curriculum curriculum){
        List<ControlWork> list = dao.getByStudentsAndCurriculum(students,curriculum);
        HashMap<AbstractStudent,ControlWork>map = new HashMap<AbstractStudent, ControlWork>();
        for(AbstractStudent s:students){
            ControlWork tmp = new ControlWork(s, curriculum);
            if(list.contains(tmp))
                tmp = list.get(list.indexOf(tmp));
            map.put(s, tmp);
        }
        return map;
    }

    public List<Curriculum> getCurriculumsWithControlWorks(DeCurriculum example){
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
