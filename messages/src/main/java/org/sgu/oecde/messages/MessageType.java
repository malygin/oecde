/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.messages;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 * енам тип сообщения
 */
public enum MessageType {
      //личное сообщение
      privateMessage,
      //вопрос
      askMessage;
    private static final long serialVersionUID = 82L;

    @Override
    public String toString() {
        switch(this){
            case privateMessage:
                return "Личное сообщение";
            case askMessage:
                return "Вопрос";

            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case askMessage:
                return 2;
            case privateMessage:
                return 1;

            default:
                throw new AssertionError();
        }
    }

    public static MessageType parse(int type) {
        switch(type){
            case 2:
                return askMessage;
            case 1:
                return privateMessage;
            default:
                throw new AssertionError();
        }
    }

}
