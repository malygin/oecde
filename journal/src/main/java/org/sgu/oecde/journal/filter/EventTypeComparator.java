/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.journal.filter;

import java.util.Comparator;
import org.sgu.oecde.journal.EventType;

/**
 *
 * @author basakovvy
 */
public class EventTypeComparator implements Comparator<EventType> {

    public int compare(EventType o1, EventType o2) {
        if (o1 != null && o1.equals(o2)) {
            return 0;
        }
        return o1.getId() > o2.getId() ? 1 : -1;
    }
}
