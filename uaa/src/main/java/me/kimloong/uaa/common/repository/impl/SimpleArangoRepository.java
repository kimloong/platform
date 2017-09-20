package me.kimloong.uaa.common.repository.impl;

import com.arangodb.ArangoDatabase;
import me.kimloong.uaa.common.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Queue;

/**
 * Created by Zhang JinLong(150429) on 2017-09-20.
 */
public abstract class SimpleArangoRepository<T,ID extends Serializable> implements CrudRepository<T,ID> {

    @Autowired
    private Queue<ArangoDatabase> queue;

    protected abstract String getCollectionName();

    protected abstract Class<T> getEntityClass();

    @Override
    public T findOne(ID id) {
        ArangoDatabase database = queue.peek();
        try {
            return database.getDocument(getCollectionName() + "/" + id, getEntityClass());
        } finally {
            queue.add(database);
        }
    }

    @Override
    public T add(T model) {
        ArangoDatabase database = queue.peek();
        try {
            return database.collection(getCollectionName()).insertDocument(model).getNew();
        } finally {
            queue.add(database);
        }
    }
}
