package org.sgu.oecde.core.education.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * интерфейс для ConstantsDao. не наследуется от базовых дао.
 * @author ShihovMY
 */
public interface IConstantsDao {
    
    /**
     * получает List<Map>, содержащих имя константы и значение
     * @param entityName
     * @return List<Map>
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<Map> getConstants(String entityName) throws DataAccessException;

    /**
     *
     * @param c - сущность, которая будет сохранена в бд посредством {@code org.hibernate.Session.update}
     * @param entity - имя entity
     * @throws DataAccessException
     */
    @Transactional
    public void save(Map c,String entity) throws DataAccessException;

    /**
     *
     * @param c - сущность, которая будет изменена в бд посредством {@code org.hibernate.Session.update}
     * @param entity - имя entity
     * @throws DataAccessException
     */
    @Transactional
    public void update(Map c,String entity) throws DataAccessException;
}
