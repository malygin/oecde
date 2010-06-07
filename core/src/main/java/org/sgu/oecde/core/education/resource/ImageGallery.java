package org.sgu.oecde.core.education.resource;

import java.util.Set;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Составной ресурс ресурс - галлерея видео изображений.
 */

//TODO: решить делать ли асбтрактную галлерею настройки галлереи
 public class ImageGallery extends AbstractResource{

    private Integer numImages;
  
    private Set<Image> images;

    public ImageGallery() {
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
 
    public Integer getNumImages() {
        return numImages;
    }

    public void setNumImages(Integer numImages) {
        this.numImages = numImages;
    }


}
