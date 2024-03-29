package org.sgu.oecde.messages.dao;

import java.util.ArrayList;
import java.util.List;

import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.messages.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.sgu.oecde.messages.MessageType;
/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 08.06.2010
 * дао для сообщений
 */
@Repository
public class MessageDao  extends BasicDao<Message> implements IMessageDao{

    private final String LIST_IN = "select m from Message m JOIN m.recipients  recipients  where recipients.recipient.id=:recipient_id and recipients.deleted=false ";
    
   //  private final String LIST_IN_SORTED = "select m from Message m JOIN m.recipients  recipients  where recipients.recipient.id=:recipient_id and recipients.deleted=false ";
   
    private final String LIST_OUT="select m from Message m where m.author=:author_id order by m.dateMessage desc ";
    private final String LIST_DIALOG = "select m from Message m JOIN m.recipients  recipients  where" +
            " (recipients.recipient.id=:current_user_id  or recipients.recipient.id=:user_id) and" +
            " (m.author.id=:current_user_id or m.author.id=:user_id) and " +
            "not (m.author.id=:user_id and recipients.recipient.id=:user_id)  and " +
            "not (m.author.id=:current_user_id and recipients.recipient.id=:current_user_id) order by m.dateMessage desc";
    private final String LIST_COUNT = "select count(m) from Message m JOIN m.recipients  recipients  where recipients.recipient.id=:recipient_id and recipients.deleted=false";
    private final String LIST_COUNT_OUT="select count(m) from Message m  where m.author=:author_id";
    private final String UPDATE ="update MessageRecipient m  set m.=true where m.recipient.id=:recipient_id and message_id=:message_id";

    protected MessageDao(){
        super(Message.class);
    }

    @Override
    public List<Message> getSortedInList(AbstractUser user, MessageType type, int messageOnPage, int numPage) throws DataAccessException {
        List <Message> messages=new ArrayList();
        messages =getSession().createQuery("select m from Message m join m.recipients r  where r.recipient.id=:recipient_id and r.deleted=false and m.type=:type order by m.dateMessage desc").
                setLong("recipient_id", user.getId()).
                setInteger("type", type.toInt()).
                setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).list();
       return messages;
    }
    
    @Override
    public List<Message> getList(AbstractUser user, String type, int messageOnPage, int numPage) throws DataAccessException {
        List <Message> messages=new ArrayList();
        if (type.equals("new")){
              //TODO:
        }else if(type.equals("in")){
             messages =getSession().createQuery(LIST_IN+" and recipients.archived=false order by m.dateMessage desc").
                setLong("recipient_id", user.getId()).
                setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).list();
        }else if(type.equals("arch")){
             messages = getSession().createQuery(LIST_IN+" and recipients.archived=true order by m.dateMessage desc").
                 setLong("recipient_id", user.getId()).
                 setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).list();
        }else if(type.equals("out")){
             messages = getSession().createQuery(LIST_OUT).
                 setLong("author_id", user.getId()).
                 setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).list();
          }
       return messages;
    }

    @Override
    public List<Message> getListInByUserRole(UserType type, AbstractUser user) throws DataAccessException {
        //TODO - имеет ли смысл, если проще отсортировать список входящих просто
        throw new UnsupportedOperationException("Not supported yet.");
    }  

   
    @Override
    public List<Message> getListDialog(AbstractUser current_user, Long user) throws DataAccessException {
         return getSession().createQuery(LIST_DIALOG).setLong("current_user_id", current_user.getId()).setLong("user_id", user).list();
    }
   
    @Override
     @Transactional
    public void save(Message message) throws DataAccessException {
       getSession().save(message);      
    }
  
    @Override
     @Transactional
    public void update(Long  messageId, AbstractUser user, String column) throws DataAccessException {
        StringBuilder query = new StringBuilder(UPDATE);
        // вставляем в строку с запросом
        query.insert(33, column);
        getSession().createQuery(query.toString()).setLong("recipient_id", user.getId()).setLong("message_id", messageId).executeUpdate();
  
    }

    @Override
    public int getCount(AbstractUser user, String type) throws DataAccessException {
        List<Long> list = null;
        if (type.equals("new")){
             list = getSession().createQuery(LIST_COUNT+" and recipients.archived=false and recipients.deleted=false and recipients.readed=false").setLong("recipient_id", user.getId()).list();
        }else if(type.equals("in")){
             list = getSession().createQuery(LIST_COUNT+" and recipients.deleted=false and recipients.archived=false").setLong("recipient_id", user.getId()).list();
        }else if(type.equals("arch")){
             list = getSession().createQuery(LIST_COUNT+" and recipients.deleted=false and recipients.archived=true").setLong("recipient_id", user.getId()).list();
        }else if(type.equals("out")){
              list = getSession().createQuery(LIST_COUNT_OUT).setLong("author_id", user.getId()).list();
        }     
      return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }
    
    @Override
    public int getCountByType(AbstractUser user, MessageType type) throws DataAccessException {
        List<Long> list = null;
             list = getSession().createQuery(LIST_COUNT+" and recipients.deleted=false and recipients.archived=false and m.type=:type").setLong("recipient_id", user.getId()).
                     setLong("type", type.toInt()).list();
        
      return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }


}

