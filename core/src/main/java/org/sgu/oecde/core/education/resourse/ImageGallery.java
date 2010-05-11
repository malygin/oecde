/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.resourse;

import java.util.List;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Составной ресурс ресурс - галлерея видео изображений.
 */

//TODO: решить делать ли асбтрактную галлерею
abstract public class ImageGallery extends AbstractResource{

    private int numImages;
    private int height;
    private List<Image> images;

    public ImageGallery() {
    }


}
