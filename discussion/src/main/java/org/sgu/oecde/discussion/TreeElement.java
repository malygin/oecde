/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.discussion;

import java.util.Set;
import java.util.SortedSet;

/**
 * Интерфейс-маркер.
 *
 * @author basakovvy
 */
public interface  TreeElement {

    /**
     * Добавляет дочерний элемент.
     */
    boolean addChild(Node child);

    boolean addChildren(Set<Node> nodes);

    void setChildren(SortedSet<Node> nodes);

    boolean removeChild(Node child);
}
