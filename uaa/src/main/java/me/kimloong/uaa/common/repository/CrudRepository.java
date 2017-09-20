package me.kimloong.uaa.common.repository;

import java.io.Serializable;

/**
 * DAO CRUD interface
 *
 * @author KimLoong
 */
public interface CrudRepository<T,ID extends Serializable> {

    T findOne(ID id);

    T add(T t);
}
