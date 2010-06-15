package org.sgu.oecde.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * базовый дженерик дао. получает в качестве параметра тип сущности, с которой будет производиться работа
 * @author shihovmy
 * @param <T>
 */
public class BasicDao<T extends BasicItem> extends HibernateDaoSupport implements IBasicDao<T>{
    /**
     * тип сущности
     */
    protected Class<T> type;

    protected BasicDao(Class<T> type) {
        this.type = type;
    }

    protected BasicDao() {
    }

    @Override
    protected void initDao(){
        Assert.notNull(type, "type is null");
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getById(final Long id) throws DataAccessException{
        T item = (T) getSession().get(type, id);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() throws DataAccessException{
        return  getSession().createCriteria(type).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        return getCriteriaByParametrizedItem(item,cr).list();
    }

    public List<T> getBySimpleExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        return cr.add(Example.create(item).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes()).addOrder(Order.asc("id")).list();
    }

    public List<T> getByFullExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);

        Assert.isInstanceOf(type,item ,"item is not an instance of type "+type);
        Assert.notNull(item,"item can not be null");
        cr.add(Example.create(item).excludeZeroes()).addOrder(Order.asc("id"));

        final FastClass fc = FastClass.create(item.getClass());
        methods:
        for (Method m : item.getClass().getMethods()) {
            if(!m.getName().startsWith("get")||Modifier.isStatic(m.getModifiers())||m.getParameterTypes().length>0)
                continue methods;
            Class clazz = m.getReturnType();
            while(true){
                if(clazz!=null&&clazz.equals(Set.class)){
                    break;
                }else if(clazz!=null&&!(clazz.equals(BasicItem.class)||clazz.equals(Set.class))){
                    clazz = clazz.getSuperclass();
                    if(clazz!=null&&(clazz.equals(BasicItem.class)||clazz.equals(Set.class)))
                        break;
                }else
                    continue methods;
            }
            try {
                final FastMethod fm = fc.getMethod(m);
                final String fieldName = m.getName().substring(3).toLowerCase();
                if(clazz.equals(BasicItem.class)){
                    BasicItem newItem = (BasicItem) fm.invoke(item,new Object[]{});
                    if(newItem!=null&&newItem.getId()!=0){
                        cr.add(Property.forName(fieldName).eq(newItem));
                    }
                }else{
                    final Set newSet = (Set) fm.invoke(item,new Object[]{});
                    if(newSet==null)
                        continue methods;
                    if(newSet!=null&&!newSet.isEmpty()){
                        Object o = newSet.iterator().next();
                        if(o instanceof BasicItem&&((BasicItem)o).getId()!=0)
                            cr.createAlias(fieldName, "alias").add(Restrictions.eq("alias.id", ((BasicItem)o).getId()));
                    }
                }
           }  catch (InvocationTargetException ex) {
                Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return cr.list();
    }

    @SuppressWarnings("unchecked")
    protected Criteria getCriteriaByParametrizedItem(final T item,final Criteria cr){
        Assert.isInstanceOf(type,item ,"item is not an instance of type "+type);
        Assert.notNull(item,"item can not be null");
        cr.add(Example.create(item).excludeZeroes()).addOrder(Order.asc("id"));

        final FastClass fc = FastClass.create(item.getClass());
        methods:
        for (Method m : item.getClass().getMethods()) {
            if(!m.getName().startsWith("get")||Modifier.isStatic(m.getModifiers())||m.getParameterTypes().length>0)
                continue methods;
            Class clazz = m.getReturnType();
            while(true){
                if(clazz!=null&&!(clazz.equals(BasicItem.class))){
                    clazz = clazz.getSuperclass();
                    if(clazz!=null&&(clazz.equals(BasicItem.class)))
                        break;
                }else
                    continue methods;
            }
            try {
                final FastMethod fm = fc.getMethod(m);
                final String fieldName = m.getName().substring(3).toLowerCase();
                if(clazz.equals(BasicItem.class)){
                    BasicItem newItem = (BasicItem) fm.invoke(item,new Object[]{});
                    if(newItem!=null&&newItem.getId()!=0){
                        cr.add(Property.forName(fieldName).eq(newItem));
                    }
                }
           }  catch (InvocationTargetException ex) {
                Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return cr;
    }
}