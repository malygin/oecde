/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class AnsweredQuestion extends BasicItem{
    private Question question;
    private Set<GivenAnswer> givenAnswers;
    private boolean right;
    private int resultPoints;
    private static final long serialVersionUID = 74L;

    public AnsweredQuestion() {
    }

    public AnsweredQuestion(int id) {
        setId(id);
    }

    public Set<GivenAnswer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(Set<GivenAnswer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getResultPoints() {
        return resultPoints;
    }

    public void setResultPoints(int resultPoints) {
        this.resultPoints = resultPoints;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
