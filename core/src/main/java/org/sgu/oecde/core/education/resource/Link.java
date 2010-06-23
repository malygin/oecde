package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - ссылка
 */
public class Link extends AbstractResource{
    /**
     * урл
     */
    private String url;


    public Link() {
    }

    /**
     *
     * @return урл
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
