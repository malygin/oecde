
package org.sgu.oecde.chat;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.springframework.dao.DataAccessException;
import org.sgu.oecde.core.UpdateDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 25.10.2010
 * 
 */
public interface IChatDao extends IUpdateDao<ChatMessage> {

    @Transactional
    public void save(ChatMessage message)  throws DataAccessException;

    public List<ChatMessage> getChatList(Long roomId, int number) throws DataAccessException ;

}
