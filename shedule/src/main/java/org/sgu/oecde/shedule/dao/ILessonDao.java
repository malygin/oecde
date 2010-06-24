package org.sgu.oecde.shedule.dao;

import java.text.ParseException;
import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.shedule.Lesson;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


/**
 * дао для работы с сущностью LessonItem
 * @see LessonItem
 * @author ShihovMY
 */
public interface ILessonDao extends IBasicDao<Lesson>{

    /**
     * сохраняет/изменяет занятие в базе
     * @param lesson - сохраняемая сущность
     * @throws DataAccessException
     */
    @Transactional
    public void saveLesson(final Lesson lesson) throws DataAccessException;

    /**
     * получает количество занятий по критериям, что берутся из параметров lesson
     * @param lesson сущность, из которой берутся параметры, используемые в критерии
     * @return количество найденных сущностей
     * @throws DataAccessException
     */
    public Integer getLessonCount(final Lesson lesson)throws DataAccessException;

    /**
     * @param year год
     * @param month месяц
     * @return занятия за указанные год и месяц
     * @throws DataAccessException
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public List<Lesson> getListByMonth(String year, String month) throws DataAccessException, ParseException;

    /**
     * удаляет занятие из базы
     * @param lesson - сохраняемая сущность
     * @throws DataAccessException
     */
    @Transactional
    public void deleteLesson(final Lesson lesson) throws DataAccessException;
}
