package org.sgu.oecde.messages;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 11.06.2010
 * сущность - получать сообщения
 */
public class MessageRecipient extends BasicItem{

    //получатель
    private AbstractUser recipient;
    //заархивировано?
    private Boolean archived=false;
    //удалено?
    private Boolean deleted=false;
    //прочитано?
    private Boolean readed=false;

    public MessageRecipient() {
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

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public AbstractUser getRecipient() {
        return recipient;
    }

    public void setRecipient(AbstractUser recipient) {
        this.recipient = recipient;
    }

    /**
    * Возвращает фио автора
    * @todo разобраться когда получатель - SUPERVISOR
    * @return фио
    */
   public String getFioAuthor(){
     String fio="";
     UserType userType=UserType.toType(recipient);
     AbstractPerson person=(AbstractPerson) recipient;
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

}
