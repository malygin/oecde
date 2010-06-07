package org.sgu.oecde.core.education;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public class CalendarConstants implements Serializable{
    private ICalendarConstantName name;
    private String value;
    private static final long serialVersionUID = 58L;

    public CalendarConstants() {
    }

    public CalendarConstants(ICalendarConstantName name) {
        this.name = name;
    }

    public CalendarConstants(ICalendarConstantName name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CalendarConstants other = (CalendarConstants) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    public ICalendarConstantName getName() {
        return name;
    }

    public void setName(ICalendarConstantName name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
