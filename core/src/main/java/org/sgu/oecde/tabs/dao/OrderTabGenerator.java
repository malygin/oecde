/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.tabs.dao;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Jun 23, 2011
 */
public class OrderTabGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        int b=6;        
        return "1";
    }
    
}
