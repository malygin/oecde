package org.sgu.oecde.discussion.util;


import java.util.Comparator;
import org.sgu.oecde.discussion.Node;

/**
 * @author Basakov
 */
public class NodeRevertComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return -1 * o1.compareTo(o2);
    }
}