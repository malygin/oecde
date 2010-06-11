
package org.sgu.oecde.messages;

import org.sgu.oecde.core.BasicItem;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 * сущность-файл для сообщений
 */
public class MessageFile extends BasicItem {
    //имя
    private String name;
   //папка - опционально
    private String path;
    //тип- будем хранить расширение
    private String type;
    //размер
    private String sizeFile;

    public MessageFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
