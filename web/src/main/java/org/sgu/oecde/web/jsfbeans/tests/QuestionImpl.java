
package org.sgu.oecde.web.jsfbeans.tests;

import org.sgu.oecde.tests.GivenAnswer;
import org.sgu.oecde.tests.Question;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 27.09.2010
 * 
 */
public class QuestionImpl {
    private Question question;
    private boolean answered=false;
    private GivenAnswer answer;

    public QuestionImpl() {
    }

    public QuestionImpl(Question question) {
        this.question = question;
    }

    
    public GivenAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(GivenAnswer answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getCountAnswers(){
        return this.question.getAnswers().size();
    }

}
