package org.sgu.oecde.search.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author ShihovMY
 */
public class SearchDao extends HibernateTemplate implements ISearchDao{

    public List search(Class type,String[] words) throws DataAccessException{
        Assert.notNull(type);
        Assert.notEmpty(words);

        StringBuilder sb = new StringBuilder("from").append(" ").append(type.getName()).append(" where ");
        boolean notFirst = false;
        Class searchType = type;
        while (!Object.class.equals(searchType) && searchType != null) {
                Field[] fields = searchType.getDeclaredFields();
                fields:
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if(!String.class.equals(field.getType())||Modifier.isStatic(field.getModifiers())
                            ||Modifier.isFinal(field.getModifiers()))
                        continue fields;
                    for(String s:words){
                        if(notFirst)
                            sb.append(" or ");
                        sb.append("lower(").append(field.getName()).append(") like '%").append(s.toLowerCase()).append("%'");
                        notFirst = true;
                    }
                }
                searchType = searchType.getSuperclass();
        }
        if(!notFirst)
            return null;
        return getSession().createQuery(sb.toString()).list();
    }
}