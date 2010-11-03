package org.sgu.oecde.journal;

import javax.annotation.Resource;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@Service
public class JournalService {

    @Resource
    private IJournalDao journalDao;

    private JournalService() {
    }

    private static final long serialVersionUID = 188L;

    /**
     *
     * @param EventType eventType тип события
     * @param AbstractUser user пользователь, к которому будет прикреплено это событие
     * @param Object[] o массив объектов, необходимых для формирования события. типы объектов
     * указаны в описании типа события
     * @see EventType
     */
    public void save(EventType eventType, AbstractUser user, Object ... o) {
        Assert.notNull(eventType);
        EventItem item = eventType.fillEventItem(user, o);
        Assert.notNull(item);
        item.setEventType(eventType);
        journalDao.saveEventItem(item);
    }

    /**
     *
     * @param String тип события
     * @param AbstractUser user пользователь, к которому будет прикреплено это событие
     * @param Object[] o массив объектов, необходимых для формирования события. типы объектов
     * указаны в описании типа события
     * @see EventType
     */
    public void saveByStringType(String type, AbstractUser user, Object ... o) {
        EventType eventType = EventType.valueOf(type);
        save(eventType, user, o);
    }
}
