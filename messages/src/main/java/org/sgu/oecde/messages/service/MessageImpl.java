
package org.sgu.oecde.messages.service;

import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.messages.Message;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 30.06.2010
 * фасад для сообщений, адаптированный для вывода
 */
public class MessageImpl {
    private Message message;
    
    //метки удаления, прочитано, заархивировано - в сущности получателя
    private Boolean archived=false;
    private Boolean deleted=false;
    private Boolean readed=false;
    final static public int numCharInShortText=30;

    /**
     * Возвращает boolean для метки письма прочитано оно или нет (jsf не выводит Boolean)
     * @return true если прочитан
     */
   public boolean getNew(){
       return !this.readed;
   }

   /**
     * Возвращает информацию есть ли файл (jsf не выводит Boolean)
     * @return true если файлы есть
     */
   public boolean getFilesExist(){
      return !this.message.getFiles().isEmpty();
   }
    /**
     * возвращает сокращенный, если это необходим текст письма
     * @return сокращенный текст письма
     */
   public String getShortText(){
       if (message.getFullText().length()>numCharInShortText)
       return message.getFullText().substring(0, numCharInShortText)+"...";
       else return message.getFullText();
   }

   /**
    * Возвращает фио автора
    * @todo разобраться когда получатель - SUPERVISOR
    * @return фио
    */
   public String getFioAuthor(){
     String fio="";
     UserType userType=UserType.fromRole(message.getAuthor());
     AbstractPerson person=(AbstractPerson) message.getAuthor();
     switch(userType){
         case STUDENT:
            fio=person.getName();
            break;
         case ADMIN:
             fio=person.getFio();
             break;
         case TEACHER:
             fio=person.getFio();
             break;
       }
     return fio;
   }

   /**
    * Возвращает строку - тип автора
    * @return
    */
   public String getTypeAuthor(){
       return UserType.fromRole(message.getAuthor()).toString();
   }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }


}