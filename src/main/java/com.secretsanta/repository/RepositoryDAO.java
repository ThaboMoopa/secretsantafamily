package com.secretsanta.repository;

import java.util.List;

public interface RepositoryDAO<T> {
    void add(T t, long tid);
    void update(T t);
    void delete(long id);
    T getById(long id);
    List<T> getAll();

}
