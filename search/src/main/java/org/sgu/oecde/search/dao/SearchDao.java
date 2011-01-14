package org.sgu.oecde.search.dao;

import java.util.List;
import org.sgu.oecde.search.SearchType;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * {@inheritDoc }
 */
@Repository
public class SearchDao extends HibernateTemplate implements ISearchDao{

    /**
     * {@inheritDoc }
     */
    public List search(SearchType type,String[] words) throws DataAccessException{
        Assert.notNull(type);
        Assert.notEmpty(words);
        StringBuilder sb = new StringBuilder("from").append(" ").append(type.toClass()).append(" where ");
        boolean notFirst = false;
        boolean notFirtsField = false;
        for(String w:words){
            if(notFirst)
                sb.append(" and ");
            sb.append("(");
            for(String s:type.getSearchableFields()){
                if(notFirtsField)
                    sb.append(" or ");
                sb.append("lower(").append(s).append(") like '%").append(w.toLowerCase()).append("%'");
                notFirtsField = true;
            }
            sb.append(")");
            notFirtsField = false;
            notFirst = true;
        }
        if(!notFirst)
            return null;
        return getSession().createQuery(sb.toString()).setCacheable(false).list();
    }
}