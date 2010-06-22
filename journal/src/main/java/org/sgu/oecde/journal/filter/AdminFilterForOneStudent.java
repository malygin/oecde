/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.journal.filter;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.journal.EventType;
import org.springframework.stereotype.Service;

/**
 *
 * @author basakovvy
 */
@Service
public class AdminFilterForOneStudent extends AdminFilter {

    private static final String adminFilterForOnStudCookiePath = "AdminFilterForOneStudent";
    private int userId;
    private final String userIdKey = "userId";

    public AdminFilterForOneStudent() {
        //Вызов добавлен как напоминание, что допустимые события добавляются не только здесь.
        availableEvents.clear();
        availableEvents.add(EventType.SYSTEM_LOGIN);
        availableEvents.add(EventType.UMK_VIEW);
        availableEvents.add(EventType.TASK_HAS_BEEN_SEND_TO_PREP);
        availableEvents.add(EventType.TEST_END);
        availableEvents.add(EventType.PHOTO_ADDITION);
        availableEvents.add(EventType.PHOTO_DELETION);
    }

    @Override
    public String getAddString() {
        return "AND ID_USER = " + userId;
    }

    @Override
    protected void parseMap(HashMap<String, String> map) {
        super.parseMap(map);
        if (map.containsKey(userIdKey)) {
            final int value = Integer.valueOf(map.get(userIdKey)).intValue();
            userId = value;
        }
    }

    @Override
    protected HashMap<String, String> getRequestParameters(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(userIdKey, request.getParameter(userIdKey));
        return hashMap;
    }

    @Override
    public String getCookiePath() {
        return adminFilterForOnStudCookiePath;
    }
}
