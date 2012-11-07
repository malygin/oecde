
package org.sgu.oecde.chat;

import java.util.List;
import org.hibernate.criterion.Order;
import org.sgu.oecde.core.UpdateDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 25.10.2010
 * дао для чата
 *
 */
@Repository("chatDao")
public class ChatDao  extends UpdateDao<ChatMessage> implements IChatDao {

    public ChatDao() {
        super(ChatMessage.class);
    }

    private static final long serialVersionUID = 161L;

    @Transactional
    @Override
    public void save(ChatMessage message) throws DataAccessException {
        getSession().merge(message);
    }
   
    @Override
 /**
  * @todo сделать комнаты, если понадобиться
  */
    public List<ChatMessage> getChatList(ChatRoom room, int number) throws DataAccessException {

         return getSession().createCriteria(type).addOrder(Order.desc("dateMessage")).
                 add(org.hibernate.criterion.Property.forName("room").eq(room)).
                 setMaxResults(number).list();

    }

}
