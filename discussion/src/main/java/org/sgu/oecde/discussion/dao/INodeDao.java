package org.sgu.oecde.discussion.dao;

import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.discussion.Node;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * дао постов
 * @author ShihovMY
 */
public interface INodeDao extends IUpdateDao<Node>{

    /**
     * удаляет данный пост
     * @param node - пост
     * @throws DataAccessException
     */
    @Transactional
    public void delete(Node node) throws DataAccessException  ;

    /**
     * сохраняет/изменяет данный пост
     * @param node
     * @throws DataAccessException
     */
    @Transactional
    public void save(Node node)  throws DataAccessException;
}
