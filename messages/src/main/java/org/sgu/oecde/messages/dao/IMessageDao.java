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

   /**
    * Получение списка сообщений в зависимости от типа
    * @param user - текущйи пользователь
    * @param type -- строка, с меткой что за список мы возвращаем (new - новые сообщения, in-входящие, out-исходящие, arch-заархивированные)
    * @param messageOnPage - сообщений на странице
    * @param numPage номер страницы
    * @return список сообщений, доработанный для вывода
    */
    @SuppressWarnings("unchecked")
    public List<Message> getList(AbstractUser user, String type, int messageOnPage, int numPage) throws DataAccessException;

   
   /**
     * Возвращает диалог текущего пользователя с другим
     * @param current_user - текущий
     * @param user - второй
     * @return список сообщений
     */
    @SuppressWarnings("unchecked")
    public List<Message> getListDialog(AbstractUser current_user, AbstractUser user) throws DataAccessException;

    /**
     * Получение списка сообщений от пользователей определенного типа
     * @param type - тип пользователей от кого сообщения
     * @param user - текущий пользователь, получатель сообщений
     * @return список сообщений
     */
    @SuppressWarnings("unchecked")
    public List<Message> getListInByUserRole(UserType type, AbstractUser user) throws DataAccessException;

   /**
     * Получение количества на разные списки сообщений
     * @param user - текущий пользователь
     * @param type - строка, с меткой что за список мы считаем (new - новые сообщения, in-входящие, out-исходящие, arch-заархивированные)
     * @return количесство не прочитанных сообщений
     */
    @SuppressWarnings("unchecked")
    public int getCount(AbstractUser user, String type) throws DataAccessException;

   
   /**
     * Помечаем, что текущий пользователь удалил, заархивировал или прочитал сообщение сообщение
     * @param messageId - id сообщения
     * @param user - текущий пользователь
     * @param column - строка readed-archived-deleted
     */
    @Transactional
    public void update(Long messageId, AbstractUser user, String column) throws DataAccessException;

   /**
     * Помечаем, что текущий пользователь прочитал  сообщение
     * @param messageId - id сообщения
     * @param user - текущий пользователь
     */

    @Transactional
    public void save(Message message) throws DataAccessException;
}
