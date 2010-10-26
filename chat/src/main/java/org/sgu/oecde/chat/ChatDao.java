
package org.sgu.oecde.chat;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.UpdateDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 25.10.2010
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
        getSession().save(message);
    }
   
    @Override
 /**
  * @todo сделать комнаты, если понадобиться
  */
    public List<ChatMessage> getChatList(Long roomId, int number) throws DataAccessException {

         return getSession().createCriteria(type).addOrder(Order.desc("dateMessage")).
              //   add(Property.forName("room").eq(roomId)).
                 setMaxResults(number).list();

    }

}
