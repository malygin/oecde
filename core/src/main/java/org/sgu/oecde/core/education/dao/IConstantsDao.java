package org.sgu.oecde.core.education.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 * интерфейс для ConstantsDao. не наследуется от базовых дао.
 * @author ShihovMY
 */
public interface IConstantsDao extends Serializable{
    
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
    public void save(Map c,String entity) throws DataAccessException;

    /**
     *
     * @param c - сущность, которая будет изменена в бд посредством {@code org.hibernate.Session.update}
     * @param entity - имя entity
     * @throws DataAccessException
     */
    public void update(Map c,String entity) throws DataAccessException;
}
