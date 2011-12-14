package org.sgu.oecde.messages.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.MessageRecipient;
import org.sgu.oecde.messages.MessageType;
import org.sgu.oecde.messages.dao.IMessageDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 30.06.2010
 * сервис, который предоставляет для бинов удобные методы и готовые списи
 */

@Service
public class MessageService implements Serializable{

    @Resource (name="messageDao")
    IMessageDao messageDao;

    public MessageService() {
    }


    /**
     * Создание списка фасадов для вывода из списка сообщений
     * @todo поставить веселое условие о том куда это список если на выход то проверка на чтение другая- просто прочел ли адрессат
     * @param user - тукущий пользователь
     * @param messages - список сообщений
     * @return - список фасадов
     */
    private List<MessageImpl>  SetMessageListReaded(AbstractUser user, List <Message> messages, String type){
       List<MessageImpl> messageImpls= new ArrayList();
        for(Message l:messages){
           MessageImpl messageImpl = new MessageImpl();
           messageImpl.setMessage(l);
           if(type.equals("out")){
                 for(MessageRecipient r:l.getRecipients()){
                  // if (r.getRecipient().g{
                      messageImpl.setReaded(r.getReaded());
                  // }
                }
           }else{
               for(MessageRecipient r:l.getRecipients()){
                   if (r.getRecipient().getId().equals(user.getId())){
                      messageImpl.setReaded(r.getReaded());
                   }
                }
           }
           messageImpls.add(messageImpl);
       }
        return messageImpls;
    }

     /**
    * Получение списка всех входящих сообщений
    * @param user - текущйи пользователь
    * @param messageOnPage - сообщений на странице
    * @param numPage номер страницы
    * @return список сообщений, доработанный для вывода
    */
    public List<MessageImpl> getListInAll(AbstractUser user, int messageOnPage, int numPage) throws DataAccessException{
      List <Message> messages=messageDao.getList(user,"in", messageOnPage, numPage);
      return SetMessageListReaded(user, messages,"in");
    }

     public List<MessageImpl> getListSortedInAll(AbstractUser user, MessageType type, int messageOnPage, int numPage) throws DataAccessException{
      List <Message> messages=messageDao.getSortedInList(user,type, messageOnPage, numPage);
      return SetMessageListReaded(user, messages,"in");
    }
    
    
    /**
     * Получение списка исходящих сообщений
     * @param user - текущий пользователь
     * @param messageOnPage - сообщений на странице
     * @param numPage номер страницы
     * @return список сообщений
     */

    public List<MessageImpl> getListOutAll(AbstractUser user, int messageOnPage, int numPage) throws DataAccessException{
      List <Message> messages=messageDao.getList(user,"out", messageOnPage, numPage);
      return SetMessageListReaded(user, messages,"out");
    }

   /**
     * Получение списка заархивированных сообщений
     * @param user -текущий пользователь
     * @param messageOnPage - сообщений на странице
     * @param numPage номер страницы
     * @return список сообщений
     */
    @SuppressWarnings("unchecked")
    public List<MessageImpl> getListArchive(AbstractUser user, int messageOnPage, int numPage) throws DataAccessException{
      List <Message> messages=messageDao.getList(user,"arch", messageOnPage, numPage);
      return SetMessageListReaded(user, messages,"in");
    }

   /**
     * Возвращает диалог текущего пользователя с другим
     * @param current_user - текущий
     * @param user - второй
     * @return список сообщений
     */
    @SuppressWarnings("unchecked")
    public List<MessageImpl> getListDialog(AbstractUser current_user, Long user) throws DataAccessException{
       return SetMessageListReaded(current_user, messageDao.getListDialog(current_user, user),"in");
    }

    /**
     * Получение списка сообщений от пользователей определенного типа
     * @param type - тип пользователей от кого сообщения
     * @param user - текущий пользователь, получатель сообщений
     * @return список сообщений
     */
    @SuppressWarnings("unchecked")
    public List<Message> getListInByUserRole(UserType type, AbstractUser user) throws DataAccessException{
          return null;
    }

   /**
     * Получение количества не прочитанных сообщений
     * @param user - текущий пользователь
     * @return количесство не прочитанных сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCountNewMessage(AbstractUser user) throws DataAccessException{
          return messageDao.getCount(user, "new");
    }

    /**
     * Получение количества входящих сообщений
     * @param user - текущий пользователь
     * @return количество входящих сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCountMessageIn(AbstractUser user) throws DataAccessException{
          return messageDao.getCount(user, "in");
    }

    /**
     * Получение количества исходящих сообщений
     * @param user - текущий пользователь
     * @return количество исходящих сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCountMessageOut(AbstractUser user) throws DataAccessException{
          return messageDao.getCount(user, "out");
    }
     /**
     * Получение количества исходящих сообщений
     * @param user - текущий пользователь
     * @param type MessageType
     * @return количество исходящих сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCountMessageByType(AbstractUser user, MessageType type) throws DataAccessException{
        if(type==MessageType.all) return getCountMessageIn(user);
          return messageDao.getCountByType(user, type);
    }
    /**
     * Получение количества заархивированных сообщений
     * @param user - текущий пользователь
     * @return количества заархивированных сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCountMessageArchive(AbstractUser user) throws DataAccessException{
         return messageDao.getCount(user, "arch");
    }

   /**
     * Помечаем, что текущий пользователь удалил сообщение
     * @param messageId - id сообщения
     * @param user - текущий пользователь
     */

    public void delete(Long messageId, AbstractUser user) throws DataAccessException{
        messageDao.update(messageId, user, "deleted");
    }

   /**
     * Помечаем, что текущий пользователь прочитал  сообщение
     * @param messageId - id сообщения
     * @param user - текущий пользователь
     */

    public void read(Long messageId, AbstractUser user) throws DataAccessException{
        messageDao.update(messageId, user, "readed");
    }


   /**
     * Помечаем, что текущий пользователь заархивировал  сообщение
     * @param messageId - id сообщения
     * @param user - текущий пользователь
     */

    public void archive(Long messageId, AbstractUser user) throws DataAccessException{
         messageDao.update(messageId, user, "archived");
    }

   /**
     * Сохранение сообщения со всеми файлами и получателями
     * @param message - сохраняемое сообщение
     */
    public void save(Message message) throws DataAccessException{
         messageDao.save(message);
    }

    /**
     * Получение фасад сообщения по id
     * @param messageId - id сообщения
     * @return фасад
     */
    public MessageImpl getById(Long messageId){
        Message message=messageDao.getById(messageId);
        MessageImpl messageImpl=new MessageImpl();
        messageImpl.setMessage(message);
        return messageImpl;
    }

}
