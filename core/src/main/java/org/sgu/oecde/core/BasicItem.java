package org.sgu.oecde.core;

import java.io.Serializable;

/**
 *базовая сущность. от неё наследуются все остальные. переопределены методы equals и hashCode
 * @author shihovmy
 */

public abstract class BasicItem implements Serializable{
    /**
     * айди == primary key в бд
     */
    private int id;

    /**
	 * 
	 * @param obj
	 * @return 
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicItem other = (BasicItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
	 * 
	 * @return 
	 */
	@Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    /**
     * получает айди
     * @return айди
     */
    public final int getId() {
        return id;
    }

    /**
     * устанавливает айди
     * @param id - айди
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
	 * 
	 * @return 
	 */
	@Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Id: ").append(this.id).append("; ");
        return sb.toString();
    }
}
