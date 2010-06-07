
package org.sgu.oecde.messages;

import java.util.List;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractPerson;


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
    private String date;
    //тип сообщения(личная переписка, вопрос)
    private MessageType type;
    //автор
    private AbstractPerson author;
    //получатели
    private List<AbstractPerson> recipients;
    //список файлов
    private List<MessageFile> files;
    //заархивировано?
    private boolean archived;
    //удалено?
    private boolean deleted;
    //прочитано?
    private boolean readed;

    public Message() {
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public AbstractPerson getAuthor() {
        return author;
    }

    public void setAuthor(AbstractPerson author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String full_text) {
        this.fullText = full_text;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public List<AbstractPerson> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<AbstractPerson> recipients) {
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


}
