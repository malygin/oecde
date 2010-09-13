package org.sgu.oecde.journal.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.EventType;

/**
 *
 * @author basakovvy
 */
public abstract class BaseFilter {

    private EventTypeComparator eventTypeComparator = new EventTypeComparator();

    /**Пользователь, к которому приклеплен фильтр*/
    protected AbstractUser userItem;
    /**id юзера*/
    protected Long userId;
    /** Дата, начиная с которой выводить события.*/
    protected String beginDate = "2009.08.01 00:00:00";
    /** Дата, по которую выводить события.*/
    protected String endDate = "2019.08.31 00:00:00";
    /** Количество событий на странице. Пользователем меняться не может. Только здесь.*/
    protected int capacity = 10;
    /** Номер страницы списка событий, на которой сейчас находится пользователь,
     *  при изменении условий фильтрации(даты, набора типов событий) сбрасывается на 1.*/
    protected int pageNumber = 1;
    /** Общее количество событий, найденых при заданных критериев, меняется в Journal*/
    protected int maxPageNumber = 1;
    /** Перечень событий, которые нужно отображать на данный момент.*/
    protected SortedSet<EventType> events = new TreeSet(eventTypeComparator);
    /** Перечень событий, которые возможно отобразить для данного типа пользователя.*/
    protected SortedSet<EventType> availableEvents = new TreeSet(eventTypeComparator);
    private EventType checked;

    protected BaseFilter() {
        addAvailableEvent(EventType.OWN_MESSAGE);
        addAvailableEvent(EventType.NEW_NEWS);

        addAvailableEvent(EventType.UMK_CREATE);
        addAvailableEvent(EventType.UMK_EDIT);
        addAvailableEvent(EventType.UMK_DELETE);

        addAvailableEvent(EventType.GRADING);
    }

    public void addEventType(EventType type) {
        events.add(type);
    }

    public final void setAttributes(Map map) {
        if (map instanceof HashMap) {
            HashMap<String, String[]> hashMap = (HashMap<String, String[]>) map;
            HashMap<String, String> anotherHashMap = new HashMap<String, String>();
            for (String key : hashMap.keySet()) {
                anotherHashMap.put(key, hashMap.get(key)[0]);
            }
            parseMap(anotherHashMap);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Принимает на вход карту параметров из request и ищет типы событий,
     * которые нужно выводить, даты и прочее.
     * @param
     */
    protected void parseMap(HashMap<String, String> map) {
        events = new TreeSet(eventTypeComparator);
        for (String str : map.keySet()) {
            if (available(str) && "true".equals(map.get(str))) {
                events.add(EventType.value(str));
            }
        }
        if (map.containsKey("pageNumber")) {
            Integer pageNumber = new Integer(map.get("pageNumber"));
            setPageNumber(pageNumber);
        }
    }

    public abstract String getCookiePath();

    public final void setCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HashMap<String, String> map = new HashMap<String, String>(cookies.length);
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            //Проверяет на то, что cookie относится к данному фильтру.            
            if (cookieName.startsWith(getCookiePath())) {
                cookieName = cookieName.substring(getCookiePath().length());
                if (cookie.getValue() != null) {
                    map.put(cookieName, cookieValue);
                }
            }
        }
        map.putAll(getRequestParameters(request));
        parseMap(map);
    }

    //В данном виде не имеет смысла, предназначен для переопределения в потомках.
    protected HashMap<String, String> getRequestParameters(HttpServletRequest request){
        return new HashMap<String, String>(0);
    }

    /**
     * Проверяет, допустим ли тип события для данного фильтра.
     * @param str - тип события в виде String(приходит из request).
     */
    private boolean available(String str) {
        EventType event = EventType.value(str);
        return event != null && availableEvents.contains(event);
    }

    public void setCheck(EventType event) {
        checked = event;
    }

    public boolean isChecked() {
        return events.contains(checked);
    }

    /**Стандартная реализация .*/
    public String getAddCondition(EventType event) {
        switch (event) {
            default:
                return "";
        }
    }

    public String getAddString() {
        return "";
    }

    protected boolean addAvailableEvent(EventType event) {
        return availableEvents.add(event);
    }

    public Set<EventType> getAvailableEvents() {
        return availableEvents;
    }

    public void setEvents(SortedSet<EventType> events) {
        this.events = events;
    }
    //В данном виде не имеет смысла, предназначен для переопределения в потомках.
    public Set<EventType> getDefaultEvents(){
        events.addAll(availableEvents);
        return events;
    }


    public Set<EventType> getEvents() {
        if (events.isEmpty()){
            return getDefaultEvents();
        }
        return events;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setMaxPageNumber(int number) {
        this.maxPageNumber = number;
    }

    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    public AbstractUser getUserItem() {
        return userItem;
    }

    public void setUserItem(AbstractUser userItem) {
        this.userItem = userItem;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
