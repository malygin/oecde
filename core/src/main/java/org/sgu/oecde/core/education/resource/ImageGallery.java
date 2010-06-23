package org.sgu.oecde.core.education.resource;

import java.util.Set;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Составной ресурс ресурс - галлерея видео изображений.
 */
 public class ImageGallery extends AbstractResource{
    /**
     * сет изображений
     */
    private Set<Image> images;

    public ImageGallery() {
    }

    /**
     * изображения галереи
     * @return
     */
    public Set<Image> getImages() {
        return images;
    }

    /**
     * изображения галереи
     * @param images
     */
    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
