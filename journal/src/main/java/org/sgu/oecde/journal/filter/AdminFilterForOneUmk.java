package org.sgu.oecde.journal.filter;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.journal.EventType;

/**
 *
 * @author basakovvy
 */
public class AdminFilterForOneUmk extends AdminFilter {

    private static final String adminFilterForOnUmkCookiePath = "AdminFilterForOneUmk";
    private int umkId;
    final String umkIdKey = "umkId";

    public AdminFilterForOneUmk() {
        availableEvents.clear();
        availableEvents.add(EventType.UMK_CREATE);
        availableEvents.add(EventType.UMK_DELETE);
        availableEvents.add(EventType.UMK_EDIT);
        availableEvents.add(EventType.UMK_VIEW);
        availableEvents.add(EventType.TEST_END);
    }

    @Override
    public String getAddString() {
        return "AND MULTI_ID = " + umkId;
    }

    @Override
    protected void parseMap(HashMap<String, String> map) {
        super.parseMap(map);

        if (map.containsKey(umkIdKey)) {
            try {
                umkId = Integer.valueOf(map.get(umkIdKey));
            } catch (NumberFormatException ex) {
                System.err.println("Пришло неверное значение umkId: " + map.get(umkIdKey));
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected HashMap<String, String> getRequestParameters(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(umkIdKey, request.getParameter(umkIdKey));
        return hashMap;
    }

    @Override
    public String getCookiePath() {
        return adminFilterForOnUmkCookiePath;
    }

    public int getUmkId() {
        return umkId;
    }
}
