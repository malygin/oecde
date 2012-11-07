
package org.sgu.oecde.chat;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.springframework.dao.DataAccessException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 25.10.2010
 * интерфейс для чата
 */
public interface IChatDao extends IUpdateDao<ChatMessage> {

    /**
     * сохранение сообщения из чата
     * @param message
     * @throws DataAccessException
     */
    @Transactional
    public void save(ChatMessage message)  throws DataAccessException;


    /**
     * получение списка сообщений из чата
     * @param roomId - номер комнаты
     * @param number - количество сообщений
     * @return лист сообщений чата
     * @throws DataAccessException
     */
    public List<ChatMessage> getChatList(ChatRoom room, int number) throws DataAccessException ;

}
