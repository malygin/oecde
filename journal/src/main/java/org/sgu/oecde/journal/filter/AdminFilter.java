package org.sgu.oecde.journal.filter;


import java.util.HashMap;
import java.util.Set;
import org.sgu.oecde.journal.EventType;
import org.springframework.stereotype.Service;

/**
 * @author basakovvy
 */
@Service
public class AdminFilter extends BaseFilter {

    private static final String adminFilterCookiePath = "AdminFilter";

    public AdminFilter() {
        //Вызов добавлен как напоминание, что допустимые события добавляются не только здесь.
        super();
        capacity = 20;
        addAvailableEvent(EventType.SYSTEM_LOGIN);

//        addAvailableEvent(EventType.SPAM_ALL);
//        addAvailableEvent(EventType.SPAM_GROUP);
//        addAvailableEvent(EventType.SPAM_SPECIALITY);
//        addAvailableEvent(EventType.SPAM_STREAM);

        addAvailableEvent(EventType.PHOTO_ADDITION);
        addAvailableEvent(EventType.PHOTO_DELETION);

        addAvailableEvent(EventType.TASK_HAS_BEEN_SEND_TO_PREP);
        addAvailableEvent(EventType.TASK_HAS_BEEN_READ);

        addAvailableEvent(EventType.UMK_VIEW);
        addAvailableEvent(EventType.NEWS_VIEW);
        addAvailableEvent(EventType.NEW_NEWS);
        addAvailableEvent(EventType.TEST_END);
        addAvailableEvent(EventType.POST_ANSWER);
        addAvailableEvent(EventType.POST_ADD);

    }

    @Override
    protected void parseMap(HashMap<String, String> map) {
        super.parseMap(map);
        if (map.containsKey("beginDate")) {
            beginDate = map.get("beginDate");
        }
        if (map.containsKey("endDate")) {
            endDate = map.get("endDate");
        }
    }

    @Override
    public String getCookiePath() {
        return adminFilterCookiePath;
    }

    @Override
    public Set<EventType> getDefaultEvents() {
        super.getDefaultEvents();
        events.remove(EventType.UMK_VIEW);
        events.remove(EventType.SYSTEM_LOGIN);
        return events;
    }
}
