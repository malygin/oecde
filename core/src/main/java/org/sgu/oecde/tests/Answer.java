package org.sgu.oecde.tests;

import org.sgu.oecde.core.BasicItem;

/**
 * вариант ответа на вопрос
 * @author ShihovMY
 */
public class Answer extends BasicItem{
    /**
     * текст ответа
     */
    private String title;
    /**
     * правильный ответ
     */
    private String rightAnswer;
    private static final long serialVersionUID = 75L;

    public Answer() {
    }

    public Answer(String title) {
        this.title = title;
    }

    /**
     *
     * @return правильный ответ
     */
    public String getRightAnswer() {
        return rightAnswer;
    }

    /**
     * правильный ответ
     * @param rightAnswer
     */
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    /**
     *
     * @return текст ответа
     */
    public String getTitle() {
        return title;
    }

    /**
     * текст ответа
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
}
