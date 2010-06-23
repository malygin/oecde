package org.sgu.oecde.core.education.resource;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - видео-ролик
 */
public class Video extends AbstractResource{
    /**
     * урл
     */
    private String url;
    /**
     * картинка предпросмотра
     */
    private String previewImg;
    /**
     * кчество
     */
    private String quality;
    /**
     * ширина
     */
    private Integer width;
    /**
     * высота
     */
    private Integer height;


    public Video() {
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
     * @return картинка предпросмотра
     */
    public String getPreviewImg() {
        return previewImg;
    }

    /**
     * картинка предпросмотра
     * @param previewImg
     */
    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

    /**
     * качество
     * @return
     */
    public String getQuality() {
        return quality;
    }

    /**
     * качество
     * @param quality
     */
    public void setQuality(String quality) {
        this.quality = quality;
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
     * высота
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
