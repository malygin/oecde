package org.sgu.oecde.web.jsfbeans.util;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public class NewEntry<K,V> implements Serializable{
    private K key;
    private V value;

    private static final long serialVersionUID = 151L;

    public NewEntry() {
    }

    public K getKey() {
        return key;
    }

    public NewEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
