package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс- занятие - лекция (обычная html)
 */
public class Task extends AbstractResource{
    private String url;

    public Task() {
    }

    public Task(Long id) {
        setId(id);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
