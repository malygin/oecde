/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.resourse;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - изображение 
 */
abstract public class Image extends AbstractResource{
    private String url;
    private String previewUrl;

    private int width;
    private int height;

    private static final int previewWidth=300;
    private static final int previewHeight=400;

    public Image() {
    }

  
}
