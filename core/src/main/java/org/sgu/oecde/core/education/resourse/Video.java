/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.resourse;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.05.2010
 * Простой ресурс - видео-ролик
 */
abstract public class Video extends AbstractResource{
    private String url;
    private String previewImg;

    private String quality;

    private int width;
    private int height;


    public Video() {
    }


}
