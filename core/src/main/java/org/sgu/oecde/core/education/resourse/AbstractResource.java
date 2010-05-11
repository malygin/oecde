/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.resourse;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.ITeacher;

/**
 *
 * @author ShihovMY
 *
 */
abstract public class AbstractResource extends BasicItem{
    
    private String title;
    private String shortDescription;
    private String Description;

    //версия ресурса,начиная с 1
    private int version;
    //видимость ресурса, true- видим
    private boolean visible;
    private String dateLastchange;

    //TODO: Есть авторы, продумать уровни доступа к ресурсам
    //TODO: нужны ли теги для ресурсов
    private Set<ITeacher>authors;

    public AbstractResource() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
