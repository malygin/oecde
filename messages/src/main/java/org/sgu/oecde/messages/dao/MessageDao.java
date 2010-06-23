package org.sgu.oecde.messages.dao;

import java.util.List;
import javax.management.Query;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.HqlConstructor;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.MessageRecipient;
import org.springframework.dao.DataAccessException;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 08.06.2010
 * дао для сообщений
 */

public class MessageDao  extends BasicDao<Message> implements IMessageDao{
//TODO - объединить запросы
    private final String LIST_IN = "select m from Message m JOIN m.recipients  recipients  where recipients.recipient.id=:recipient_id and recipients.deleted=false ";
    private final String LIST_DIALOG = "select m from Message m JOIN m.recipients  recipients  where" +
            " (recipients.recipient.id=:current_user_id  or recipients.recipient.id=:user_id) and" +
            " (m.author.id=:current_user_id or m.author.id=:user_id) and " +
            "not (m.author.id=:user_id and recipients.recipient.id=:user_id)  and " +
            "not (m.author.id=:current_user_id and recipients.recipient.id=:current_user_id) order by m.dateMessage desc";
    private final String LIST_COUNT = "select count(m) from Message m JOIN m.recipients  recipients  where recipients.recipient.id=:recipient_id and recipients.deleted=false and recipients.archived=false and recipients.readed=false";
    private final String UPDATE ="update MessageRecipient m  set m.=true where m.recipient.id=:recipient_id and message_id=:message_id";

    protected MessageDao(){
        super(Message.class);
    }

   /**
    * Получение списка всех входящих сообщений
    * @param user -текущйи пользователь
    * @return список сообщений, доработанный для вывода
    * @throws DataAccessException
    */
    @Override
    public List<Message> getListInAll(AbstractUser user) throws DataAccessException {
        List <Message> messages=getSession().createQuery(LIST_IN+" and recipients.archived=false order by m.dateMessage desc").setLong("recipient_id", user.getId()).list();
       //обработаем список вытащим метки прочитано или нет в основной список
       for(Message l:messages){
           for(MessageRecipient r:l.getRecipients()){
              // System.out.println(""+r.getRecipient().getId());
             //  System.out.println(""+user.getId());
            //   System.out.println("");
               if (r.getRecipient().getId() == user.getId()){
                //   System.out.println("!!!");
                   l.setReaded(r.getReaded());
               }
           }          
       }        
        return messages;
    }

    /**
     * Получение списка сообщений от пользователей определенного типа
     * @param type - тип пользователей от кого сообщения
     * @param user - текущий пользователь, получатель сообщений
     * @return список сообщений
     * @throws DataAccessException
     */
    @Override
    public List<Message> getListInByUserRole(UserType type, AbstractUser user) throws DataAccessException {
        //TODO - имеет ли смысл, если проще отсортировать список входящих просто
        throw new UnsupportedOperationException("Not supported yet.");
    }   

    /**
     * Получение списка исходящих сообщений
     * @param user - текущий пользователь
     * @return список сообщений
     * @throws DataAccessException
     */
    @Override
    public List<Message> getListOutAll(AbstractUser user) throws DataAccessException {
       Message mess=new Message();
       mess.setAuthor(user);
       return getByExample(mess);
    }

    /**
     * Сохранение сообщения со всеми файлами и получателями
     * @param message - сохраняемое сообщение
     * @throws DataAccessException
     */
    @Override
    public void save(Message message) throws DataAccessException {
       getSession().saveOrUpdate(message);
      
    }

    /**
     * Помечаем, что текущий пользователь удалил сообщение
     * @param message - сообщение
     * @param user - текущий пользователь
     * @throws DataAccessException
     */
    @Override
    public void delete(Message message, AbstractUser user) throws DataAccessException {
        StringBuilder query = new StringBuilder(UPDATE);
        query.insert(33, "deleted");      
        getSession().createQuery(query.toString()).setLong("recipient_id", user.getId()).setLong("message_id", message.getId()).executeUpdate();
  
    }

   /**
     * Помечаем, что текущий пользователь прочитал  сообщение
     * @param message - сообщение
     * @param user - текущий пользователь
     * @throws DataAccessException
     */
    @Override
    public void read(Message message, AbstractUser user) throws DataAccessException {
        StringBuilder query = new StringBuilder(UPDATE);
        query.insert(33, "readed");
        getSession().createQuery(query.toString()).setLong("recipient_id", user.getId()).setLong("message_id", message.getId()).executeUpdate();

    }

   /**
     * Помечаем, что текущий пользователь заархивировал  сообщение
     * @param message - сообщение
     * @param user - текущий пользователь
     * @throws DataAccessException
     */
    @Override
    public void archive(Message message, AbstractUser user) throws DataAccessException {
        StringBuilder query = new StringBuilder(UPDATE);
        query.insert(33, "archived");
        getSession().createQuery(query.toString()).setLong("recipient_id", user.getId()).setLong("message_id", message.getId()).executeUpdate();

    }

    /**
     * Получение списка не прочитанных сообщений
     * @param user- текущий пользователь
     * @return количесство не прочитанных сообщений
     * @throws DataAccessException
     */
    @Override
    public int getCountMessage(AbstractUser user) throws DataAccessException {
      List<Long> list = getSession().createQuery(LIST_COUNT).setLong("recipient_id", user.getId()).list();
         int  i = list.get(0).intValue();
         return i;
    }

/**
 * Получение списка заархивированных сообщений
 * @param user -текущий пользователь
 * @return список сообщений
 * @throws DataAccessException
 */
    @Override
    public List<Message> getListArchive(AbstractUser user) throws DataAccessException { 
          return getSession().createQuery(LIST_IN+" and recipients.archived=true order by m.dateMessage desc").setLong("recipient_id", user.getId()).list();
     }
/**
 * Возвращает диалог текущего пользователя с другим
 * @param current_user - текущий
 * @param user - второй
 * @return список сообщений
 * @throws DataAccessException
 */
    @Override
    public List<Message> getListDialog(AbstractUser current_user, AbstractUser user) throws DataAccessException {
         return getSession().createQuery(LIST_DIALOG).setLong("current_user_id", current_user.getId()).setLong("user_id", user.getId()).list();
    }


}

