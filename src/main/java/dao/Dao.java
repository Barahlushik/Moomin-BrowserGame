package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K,T> {

    Optional<T> findById(K id);

    boolean delete(K id);

    T save (T entity);

}