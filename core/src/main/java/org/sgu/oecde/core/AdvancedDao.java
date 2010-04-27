/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.lang.reflect.Modifier;

/**
 *
 * @author ShihovMY
 */
public class AdvancedDao<T extends BasicItem> extends BasicDao<T> implements IAdvancedDao<T>{

    public AdvancedDao(Class<T> type){
        super(type);
    }

    public AdvancedDao() {
        super();
    }

    protected Criteria byParametersList(Criteria cr, Collection parameters, String field){
        if(CollectionUtils.isEmpty(parameters)||!StringUtils.hasText(field)||cr==null){
            logger.warn("Parameters are empty");
            return cr;
        }
        boolean contains = false;
        for(Field f:type.getDeclaredFields()){
            contains = f.getName().equals(field);
            contains &=Modifier.isPrivate(f.getModifiers())&&!Modifier.isStatic(f.getModifiers());
            if(contains)
                break;
        }
        if(!contains){
            logger.warn("field not in declared fields");
            return cr;
        }
        cr.add(Property.forName(field).in(parameters));
        return cr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getByParametersCollection(Collection collection, String field) throws DataAccessException {
        Criteria cr =  getSession().createCriteria(type);
        return this.byParametersList(cr, collection, field).list();
    }

}
