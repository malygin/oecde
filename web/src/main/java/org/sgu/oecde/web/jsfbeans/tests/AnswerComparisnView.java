
package org.sgu.oecde.web.jsfbeans.tests;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 15.10.2010
 * 
 */
public class AnswerComparisnView {
    private String title;
    private String title2;
    private String answer="";

    public AnswerComparisnView(String title) {
        this.title = title;
    }

    

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    


}
