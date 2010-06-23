package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - флеш-объект
 * @todo добавить методы по настройке объекта
 */
public class FlashObject extends AbstractResource{
    /**
     * урл
     */
    private String url;
    /**
     * урл картинки-заставки
     */
    private String previewImg;
    /**
     * ширина
     */
    private Integer width;
    /**
     * высота
     */
    private Integer height;


    public FlashObject() {
    }

    /**
     * высота
     * @return
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
     * урл картинки-заставки
     * @return
     */
    public String getPreviewImg() {
        return previewImg;
    }

    /**
     * урл картинки-заставки
     * @param previewImg
     */
    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
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

    /**
     * ширина
     * @return
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * ширина
     * @param width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }


}
