package org.sgu.oecde.discussion.dao;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * дао нодов
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
    
    /**
     * получение списка нодов по странице
     * @param id объекта
     * @param type объекта
     * @param nodeOnPage сколько нодов на странице
     * @param numPage номер страницы
     * @return лист нодов
     */
    public List<Node> getByPage(Long id, ForumTypes type, int nodeOnPage, int numPage) throws DataAccessException ;
}
