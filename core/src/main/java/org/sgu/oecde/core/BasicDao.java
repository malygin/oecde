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
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc}
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
        return  getSession().createCriteria(type).setCacheable(true).list();
    }

    /**
     * {@inheritDoc}
     * @see #getCriteriaByParametrizedItem(item, Criteria)
     */
    @Override
    public List<T> getByExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        return getCriteriaByParametrizedItem(item,cr).list();
    }

    /**
     * {@inheritDoc}
     * @see org.hibernate.criterion.Example
     */
    public List<T> getBySimpleExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        return cr.add(Example.create(item).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes()).addOrder(Order.asc("id")).list();
    }

    /**
     * {@inheritDoc}
     * @see org.hibernate.criterion.Example
     */
    public List<T> getByFullExample(final T item) throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);

        Assert.isInstanceOf(type,item ,"item is not an instance of type "+type);
        Assert.notNull(item,"item can not be null");
        cr.add(Example.create(item).excludeZeroes()).addOrder(Order.asc("id")).setCacheable(true);
        if(item.getId()!=null&&item.getId()!=0)
            cr.add(Restrictions.idEq(item.getId()));
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
                    if(!CollectionUtils.isEmpty(newSet)){
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
    /**
     * сначала в критерию заносится условие, составленное по полям-примитивам сущности айтем
     * с помощью {@code org.hibernate.criterion.Example}. Затем метод пробегается
     * по остальным полям сущности, которые являются наследниками {@code org.sgu.oecde.core.BasicItem}
     * и добавляет в условие ненулевые значения айди этих полей. Кроме того, класс этого поля должен
     * быть связан с сущностью-образцом по айди.
     * В итоге получается критерий с условиями, составленными из ненулевых полей сущности-образца.
     * Например, если сущность - Учебный план (Curriculum), у которой есть поля семестр, год,
     * специальность, дисциплина, и нужно найти все Curriculums для конкретной дисциплины за конкретный год,
     * то в сущность-образец нужно поместить дисциплину с искомым айди, а у поля год задать необходимое
     * значение года. В результате будет получен критерий с этим условием, по которому можно получить лист
     * Curriculums.
     * @param item сущность-образец
     * @param cr критерий
     * @return критерий
     * @see org.hibernate.criterion.Example
     * @see org.sgu.oecde.core.BasicItem
     */
    @SuppressWarnings("unchecked")
    protected Criteria getCriteriaByParametrizedItem(final T item,final Criteria cr){
        Assert.notNull(item,"item can not be null");
        Assert.isInstanceOf(type,item ,"item is not an instance of type "+type.getSimpleName()+" ");
        cr.add(Example.create(item).excludeZeroes().ignoreCase()).addOrder(Order.asc("id"));
        if(item.getId()!=null&&item.getId()!=0)
            cr.add(Restrictions.idEq(item.getId()));
        cr.setCacheable(true);
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