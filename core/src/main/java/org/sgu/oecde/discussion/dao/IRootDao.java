package org.sgu.oecde.discussion.dao;

import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * дао корневых элементов веток обсуждений
 * @author ShihovMY
 */
public interface IRootDao extends IUpdateDao<Root>{

    /**
     *
     * @param idObject адйи обсуждаемого объекта
     * @param typeObject тип обсуждаемого объекта
     * @return количество постов
     * @throws DataAccessException
     */
    public int getNodesCount(Long idObject, ForumTypes typeObject)  throws DataAccessException ;

    public Root getRootByPage(Long idObject, ForumTypes typeObject,  int messageOnPage, int numPage);


    /**
     * сохраняет данный корневой элемент
     * @param root
     * @throws DataAccessException
     */
    @Transactional
    public void save(Root root) throws DataAccessException ;
}
