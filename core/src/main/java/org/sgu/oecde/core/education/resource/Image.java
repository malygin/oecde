package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - изображение 
 */
 public class Image extends AbstractResource{
    /**
    * урл
    */
    private String url;
    /**
     * урл уменьшенного изображения
     */
    private String previewUrl;
    /**
     * ширина
     */
    private Integer width;
    /**
     * высота
     */
    private Integer height;

    public Image() {
    }

    /**
     *
     * @return высота
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * высота
     * @param height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * @return урл уменьшенного изображения
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * урл уменьшенного изображения
     * @param previewUrl
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
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

    /**
     * ширина
     * @return
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * высота
     * @param width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }
}
