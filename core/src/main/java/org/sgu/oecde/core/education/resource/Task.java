package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс- занятие - лекция (обычная html)
 */
public class Task extends AbstractResource{
    /**
     * урл
     */
    private String url;

    public Task() {
    }

    public Task(Long id) {
        setId(id);
    }

    /**
     * урл
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * урл
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }


}
