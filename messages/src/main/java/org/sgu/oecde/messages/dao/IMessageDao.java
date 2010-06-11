package org.sgu.oecde.messages.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.messages.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 08.06.2010
 * 
 */
public interface IMessageDao extends IBasicDao<Message>{

    @SuppressWarnings("unchecked")
    public List<Message> getListInAll(AbstractUser user) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public List<Message> getListOutAll(AbstractUser user) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public List<Message> getListArchive(AbstractUser user) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public List<Message> getListDialog(AbstractUser current_user, AbstractUser user) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public List<Message> getListInByUserRole(UserType type, AbstractUser user) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public int getCountMessage(AbstractUser user) throws DataAccessException;

    @Transactional
    public void delete(Message message, AbstractUser user) throws DataAccessException;

    @Transactional
    public void read(Message message, AbstractUser user) throws DataAccessException;

    @Transactional
    public void archive(Message message, AbstractUser user) throws DataAccessException;

    @Transactional
    public void save(Message message) throws DataAccessException;
}
