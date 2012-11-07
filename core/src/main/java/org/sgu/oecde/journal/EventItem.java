package org.sgu.oecde.journal;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.DateConverter;

/**
 * событие
 * @author ShihovMY
 */
public class EventItem extends BasicItem{
    private static final long serialVersionUID = 85L;
    /**
     * тип события
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

    public EventItem(AbstractUser user, Long multiId, String eventBody) {
        this.user = user;
        this.multiId = multiId;
        this.eventBody = eventBody;
        time = DateConverter.currentDate();
    }

    /**
     *
     * @return тело события
     */
    public String getEventBody() {
        return eventBody;
    }

    /**
     * тело события
     * @param eventBody
     */
    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    /**
     *
     * @return тип события
     * @see EventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * тип события
     * @param eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     *
     * @return универсальный айди
     */
    public Long getMultiId() {
        return multiId;
    }

    /**
     * универсальный айди
     * @param multiId
     */
    public void setMultiId(Long multiId) {
        this.multiId = multiId;
    }

    /**
     *
     * @param <T> extends AbstractUser
     * @return автор события
     */
    public <T extends AbstractUser>T getUser() {
        return (T) user;
    }

    /**
     * автор события
     * @param user
     */
    public void setUser(AbstractUser user) {
        this.user = user;
    }

    /**
     *
     * @return дата
     */
    public String getTime() {
        return time;
    }

    /**
     * дата
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    public EventBodyElement[] getBody(){
        return eventType!=null?eventType.parseEvent(this):new EventBodyElement[0];
    }

    public EventBodyElement[] getBodyForAdmin(){
        return eventType!=null?eventType.parseEventForAdmin(this):new EventBodyElement[0];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("автор события: ").append(user).append(", дата: ").append(time).append(", тип: ").append(eventType.getRus());
        return sb.toString();
    }
}
