package org.sgu.oecde.core.education.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IConstantsDao {
    public List<Map> getConstants(String entityName) throws DataAccessException;

    @Transactional
    public void save(Map c,String entity) throws DataAccessException;

    @Transactional
    public void update(Map c,String entity) throws DataAccessException;
}
