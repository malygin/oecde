/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.journal.filter;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.journal.EventType;

/**
 *
 * @author basakovvy
 */
public class TeacherFilterForOneUmk extends BaseFilter {

    private static final String teacherFilterForOnUmkCookiePath = "TeacherFilterForOneUmk";
    private int umkId;
    final String umkIdKey = "umkId";

    public TeacherFilterForOneUmk() {
        availableEvents.clear();
        availableEvents.add(EventType.UMK_CREATE);
        availableEvents.add(EventType.UMK_DELETE);
        availableEvents.add(EventType.UMK_EDIT);
        availableEvents.add(EventType.UMK_VIEW);
    }

    @Override
    public String getAddString() {
        return "AND MULTI_ID = " + umkId;
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
        if (map.containsKey(umkIdKey)) {
            final int value = Integer.valueOf(map.get(umkIdKey)).intValue();
            umkId = value;
        }
    }

    @Override
    public String getCookiePath() {
        return teacherFilterForOnUmkCookiePath;
    }

     @Override
    protected HashMap<String, String> getRequestParameters(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(umkIdKey, request.getParameter(umkIdKey));
        return hashMap;
    }

    public int getUmkId() {
        return umkId;
    }
}
