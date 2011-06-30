package org.sgu.oecde.core;

import java.io.Serializable;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * базовый дженерик дао. получает в качестве параметра тип сущности, с которой будет производиться работа
 * @author shihovmy
 * @param <T>
 *
 * @author ShihovMY
 */
public interface IBasicDao <T extends BasicItem> extends Serializable{
    /**
     * метод получает любую сущность типа {@code T} по айди
     * @param id айди сущности
     * @return сущность
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    T getById(final Long id) throws DataAccessException;

    /**
     * 
     * @return все сущности типа {@code T}
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    List<T> getAll() throws DataAccessException;
    
    /*
     * получение списка сущностей по номеру страницы, @todo - передавать параметр - поле по которому сортировать 
     */
    
    List<T> getByPage(int OnPage, int numPage, String order)  throws DataAccessException;
    /**
     * количество сущностей
     * @return
     * @throws DataAccessException 
     */
   
    int getCount() throws DataAccessException;
   /**
     * возвращает коллекцию сущностей по образцу. 
     * берёт из образца параметры, которые не равные 0 и null, и подставляет в запрос.
     * <b>Особенность</b>. Если сущность пустая, то есть ни одно из полей не задано,
     * то метод возвращает все объекты данного типа
     * @param item образец сущности
     * @return коллекция сущностей, отвечающих критерию поиска
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    List<T> getByExample(final T item) throws DataAccessException;
   
    /**
     * получает список по образцу и можно добавить строку для порядку
     */
    public List<T> getByExampleAndOrder(final T item, String order); 

    /**
     * получает лист объектов, по критерию, составленному из полей примитивов сущности образца
     * с использованием {@link org.hibernate.criterion.Example}.
     * Кроме этого, разрешён запрос {@code like} для параметров типа {@code String}
     * @param item сущность-образец
     * @return лист полученных объектов данного типа
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    List<T> getBySimpleExample(final T item) throws DataAccessException;

    /**
     * получает лист объектов, по критерию, составленному из полей примитивов сущности образца
     * с использованием {@code Example}. Кроме того, метод пробегается по полям-наследникам {@code BasicItem},
     * которые не null и их айди не равны null или 0, а так же по 1м элементам {@code Set}'ов, которые так же
     * не должны быть равны null и их айди  не равны null или 0.
     * @param item сущность-образец
     * @return лист полученных объектов данного типа
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    List<T> getByFullExample(final T item) throws DataAccessException;
}
