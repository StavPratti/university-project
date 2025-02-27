package org.university.repository;

import java.util.List;

public interface IGeneric<T> {

    T findById(Long id);

    List<T> findAll();

    void save(T t);

    // Maybe unnecessary
    void delete(T t);

    void update(T t);

    void deleteById(Long id);
}
