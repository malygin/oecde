package org.sgu.oecde.journal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
public enum FilterType {
    studentEvents{
        @Override
        public EventType[] getArray() {
            EventType[] studentEventsList = {
                EventType.SPAM_ALL,
                EventType.SPAM_GROUP,
                EventType.SPAM_SPECIALITY,
                EventType.SPAM_STREAM,
                EventType.TASK_HAS_BEEN_READ,
                EventType.HAND_WRITTEN_CONTROL_WORK,
                EventType.POST_ANSWER,
                EventType.OWN_MESSAGE,
                EventType.UMK_CREATE,
                EventType.UMK_EDIT,
                EventType.UMK_DELETE,
                EventType.NEW_NEWS,
                EventType.GRADING
            };
            return studentEventsList;
        }
    },
    studentNewsLineEvents(5){
        @Override
        public EventType[] getArray() {
            EventType[]studentNewsLineEventsList = {
                EventType.NEW_NEWS,
                EventType.OWN_MESSAGE,
                EventType.TASK_HAS_BEEN_READ
            };
            return studentNewsLineEventsList;
        }

    },
    adminEvents(31){
        @Override
        public EventType[] getArray() {
            return EventType.values();
        }

    },
    adminStudentEvents{
        @Override
        public EventType[] getArray() {
            EventType[]adminStudentEventsList = {
                EventType.SYSTEM_LOGIN,
                EventType.UMK_VIEW,
                EventType.TASK_HAS_BEEN_SEND_TO_PREP,
                EventType.TEST_END,
                EventType.PHOTO_ADDITION
            };
            return adminStudentEventsList;
        }

    },
    umkEvents{
        @Override
        public EventType[] getArray() {
            EventType[]umkEventsList = {
                EventType.UMK_CREATE,
                EventType.UMK_DELETE,
                EventType.UMK_EDIT,
                EventType.UMK_VIEW,
                EventType.TEST_END
            };
            return umkEventsList;
        }

            };

    private FilterType() {
        availableEvents = new ArrayList<EventForChoise>();
        EventType[] t = getArray();
        for(EventType e:t){
            availableEvents.add(new EventForChoise(e));
        }
    }

    private FilterType(int capacity) {
        this();
        this.capacity = capacity;
    }

    private String beginDate = "2012.09.01";

    private String endDate = "2019.01.01";

    private int capacity = 20;

    private Object object;

    private List<EventForChoise>availableEvents;

    private final static Log logger = LogFactory.getLog(EventType.class);

    private static final long serialVersionUID = 188L;
    
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    

    public List<EventForChoise> getAvailableEventsList(){
        return availableEvents;
    };

    public void addCondition(StringBuilder sb){
        if(availableEvents.size()>0)
            sb.append(" and (");
        else
            return;
        boolean notFirst = false;
        for(EventForChoise e:availableEvents){
            if(e.isChosen()){
                if(notFirst){
                    sb.append(" or ");
                }
                sb.append(" (");
                sb.append(" eventType = '").append(e.eventType).append("'");
                switch(this){
                    case umkEvents:
                        if(object instanceof Long)
                            sb.append(" and multiId=").append(object);
                        break;
                    case adminStudentEvents:
                        if(object instanceof Long)
                            sb.append(" and user.id=").append(object);
                        break;
                    case studentEvents:
                    case studentNewsLineEvents:
                        if(object instanceof AbstractStudent){
                            if(e.eventType.addConditionByStudent(sb, (AbstractStudent)object))
                                sb.append(" and user.id=").append(((AbstractStudent)object).getId());
                        }else
                            logger.debug("object is "+object);
                        break;
                }
                sb.append(" ) ");
                notFirst = true;
            }
        }
        sb.append(" )");
    }

    public class EventForChoise implements Serializable{
        private EventType eventType;
        private boolean chosen;

        public EventForChoise(EventType eventType) {
            this.eventType = eventType;
            this.chosen = eventType.isChosen();
        }

        public boolean isChosen() {
            return chosen;
        }

        public void setChosen(boolean chosen) {
            this.chosen = chosen;
        }

        public EventType getEventType() {
            return eventType;
        }

        public void setEventType(EventType eventType) {
            this.eventType = eventType;
        }
    }

    public abstract EventType[]getArray();
}
