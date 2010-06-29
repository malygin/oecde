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
    private Long id;

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
        sb.append("; ").append("айди: ").append(this.id).append("; ");
        return sb.toString();
    }
}
