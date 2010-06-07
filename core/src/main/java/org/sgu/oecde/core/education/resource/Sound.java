package org.sgu.oecde.core.education.resource;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - звуковой ролик
 */
public class Sound extends AbstractResource{
    private String url;
    private String previewImg;
    private String time;
    //todo: продумать как лучше хранить качество звука- битрейт?
    private String quality;
    //todo: будет ли еще что то кроме mp3?
    private String  type;

    public Sound() {
    }

    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
