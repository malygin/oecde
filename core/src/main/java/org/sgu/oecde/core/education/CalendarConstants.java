/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public class CalendarConstants implements Serializable{
    int semester;
    int year;

    public CalendarConstants() {
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        if (this.semester != other.semester) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.semester;
        hash = 47 * hash + this.year;
        return hash;
    }
}
