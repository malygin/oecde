/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education;

import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
abstract public class AbstractResource extends BasicItem{
    private String title;

    public AbstractResource() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
