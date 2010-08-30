package org.sgu.oecde.core.education.resource;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - звуковой ролик
 */
public class Sound extends AbstractResource{
    /**
     * урл
     */
    private String url;
    /**
     * урл картинки предпросмотра
     */
    private String previewImg;
    /**
     * длительность
     */
    private String time;
    /**
     * качество
     */
    //todo: продумать как лучше хранить качество звука- битрейт?
    private String quality;
    /**
     * тип звука
     */
    //todo: будет ли еще что то кроме mp3?
    private String  type;

    private static final long serialVersionUID = 146L;

    public Sound() {
    }

    /**
     *
     * @return урл картинки предпросмотра
     */
    public String getPreviewImg() {
        return previewImg;
    }

    /**
     * урл картинки предпросмотра
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
     * длительность
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * длительность
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return тип
     */
    public String getType() {
        return type;
    }

    /**
     * тип
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
