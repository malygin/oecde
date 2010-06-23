package org.sgu.oecde.messages;

import java.util.List;
import org.sgu.oecde.core.BasicItem;

import org.sgu.oecde.core.users.AbstractUser;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 * сущность-сообщение
 */
public class Message extends BasicItem {
    //тема
    private String theme;
    //текст сообщения
    private String fullText;
    //дата отправки
    private String dateMessage;
    //тип сообщения(личная переписка, вопрос)
    private MessageType type;
    //автор
    private AbstractUser author;
    //получатели
    private List<MessageRecipient> recipients;
    //список файлов
    private List<MessageFile> files;
    //эти три поля здесь для удобства вывода, метки удаления, прочитано, заархивировано - в сущности получателя
    private Boolean archived=false;
    private Boolean deleted=false;
    private Boolean readed=false;

    public Message() {
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public AbstractUser getAuthor() {
        return author;
    }

    public void setAuthor(AbstractUser author) {
        this.author = author;
    }

  

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String full_text) {
        this.fullText = full_text;
    }

    public Boolean isReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public List<MessageRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<MessageRecipient> recipients) {
        this.recipients = recipients;
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String them) {
        this.theme = them;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public List<MessageFile> getFiles() {
        return files;
    }

    public void setFiles(List<MessageFile> files) {
        this.files = files;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }


}
