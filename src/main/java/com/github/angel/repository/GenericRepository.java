package com.github.angel.repository;

import java.util.List;

public interface GenericRepository<T, K> {
    List<T> findAll();
    T findById(K id);
    void save(T entity);
    void delete(T entity);
    void update(T entity, K id);
    boolean deleteById(K id);
}
