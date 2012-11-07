package org.sgu.oecde.messages;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 * енам тип сообщения
 */
public enum MessageType {
    //все
      all,
      //личное сообщение
      privateMessage,
      //вопрос
      askMessage,
      //самостоятельная работа
      homeWork;
      
    private static final long serialVersionUID = 82L;

    @Override
    public String toString() {
        switch(this){
            case all:
                return "Все сообщения";
            case privateMessage:
                return "Личное сообщение";
            case askMessage:
                return "Вопрос";
            case homeWork:
                return "Cамостоятельная работа";
                
            default:
                throw new AssertionError();
        }
    }

    public int toInt() {
        switch(this){
            case homeWork:
                return 3;
            case askMessage:
                return 2;
            case privateMessage:
                return 1;
            case all:
                return 0;
            default:
                throw new AssertionError();
        }
    }

    public static MessageType parse(int type) {
        switch(type){
            case 3:
                return homeWork;
            case 2:
                return askMessage;
            case 1:
                return privateMessage;
            case 0:
                return all;
            default:
                throw new AssertionError();
        }
    }
   /**
     * @author sokha
     * @return 
     */
     public Map toMap(){
        Map result = new LinkedHashMap();
        for(MessageType mt: MessageType.values())
            result.put(mt.toString(), mt);
        
        return result;
    }
    public Map toMapForWrite(){
        Map result = new LinkedHashMap();
        for(MessageType mt: MessageType.values()){
            if(mt==MessageType.all) continue;
            result.put(mt.toString(), mt);
        }
           
        return result;
    }

}
