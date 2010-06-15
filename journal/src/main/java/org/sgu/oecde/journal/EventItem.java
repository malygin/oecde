package org.sgu.oecde.journal;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.util.EventParser;

/**
 * @author bASAKOVVY
 */
public class EventItem extends BasicItem{
    private static final long serialVersionUID = 85L;
    /**
     * тип события из таблички DO_JOURNAL_TYPES_EVENTS.
     */
    private EventType eventType;
    /**
     * айди пользователя.
     */
    private AbstractUser user;
    /**
     * время начала совершения события.
     */
    private String time;
    /**
     * универсальный айди. В зависимости от типа может содержать айди умк, дисциплины и тд.
     */
    private Long multiId;
    /**
     * тело события. Формируется по разному, в зависимости от типа события.
     * Содержит основные блоки информации (ФИО пользователя, имя дисциплины и т.п.),
     * разделённые определённым символом или набором символов.
     * Организован на манер CSV - разделителем служит группа строка "splitter" из интерфейса
     *
     * @see journal.util.LogTerms
     */
    private String eventBody;

    public EventItem() {
    }

    public EventItem(EventType eventType, AbstractUser user,  String time, Long multiId) {
        this.eventType = eventType;
        this.time = time;
        this.user = user;
        this.multiId = multiId;
    }

    @Override
    public String toString() {
        EventParser parser = new EventParser();
        return parser.parseEventBody(this);
    }


    public String getTimeString() {
        return time;
    }


    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setEventType(Integer eventTypeId) {
        this.eventType = EventType.value(eventTypeId);
    }

    public Long getMultiId() {
        return multiId;
    }

    public void setMultiId(Long multiId) {
        this.multiId = multiId;
    }

    public <T extends AbstractUser>T getUser() {
        return (T) user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
