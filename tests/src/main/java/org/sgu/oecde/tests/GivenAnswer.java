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
public class GivenAnswer extends BasicItem{
    
    private Answer rightAnswer;
    private String givenAnswer;
    private static final long serialVersionUID = 73L;

    public GivenAnswer() {
    }

    public GivenAnswer(int id) {
        setId(id);
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
