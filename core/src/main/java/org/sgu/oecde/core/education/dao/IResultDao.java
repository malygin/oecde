package org.sgu.oecde.core.education.dao;

import java.util.Collection;
import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;

/**
 * дао по работе с результатами
 * @author ShihovMY
 * @param <T> extends AbstractResult
 */
public interface IResultDao<T extends AbstractResult> extends IBasicDao<T>{

    /**
     * получает лист результатов, относящихся к данным студентам и учебным планам, а так же
     * равных образцу
     * @param curriculums - лист учебных планов
     * @param students - лист студентов
     * @param result - результат образец
     * @return лист результатов
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<T> getByStudentsAndCurriculums(Collection<? extends Curriculum>curriculums,Collection<? extends AbstractStudent>students, T result) throws DataAccessException;
}
