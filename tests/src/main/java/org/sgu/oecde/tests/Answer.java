/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Answer extends BasicItem{

    private String title;
    private String rightAnswer;
    private static final long serialVersionUID = 75L;

    public Answer() {
    }

    public Answer(String title) {
        this.title = title;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
