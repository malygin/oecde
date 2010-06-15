package org.sgu.oecde.core;

import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IBasicDao <T extends BasicItem>{
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

   /**
     * возвращает коллекцию сущностей по образцу. 
     * берёт из образца параметры, которые не равные 0 и null, и подставляет в запрос.
     * Кроме этого, разрешён запрос {@code like} для параметров типа {@code String}
     * @param item образец сущности
     * @return коллекция сущностей, отвечающих критерию поиска
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    List<T> getByExample(final T item) throws DataAccessException;

    @SuppressWarnings("unchecked")
    List<T> getBySimpleExample(final T item) throws DataAccessException;

    @SuppressWarnings("unchecked")
    List<T> getByFullExample(final T item) throws DataAccessException;
}
