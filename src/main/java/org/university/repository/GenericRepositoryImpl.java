package org.university.repository;

import org.apache.poi.ss.formula.functions.T;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericRepositoryImpl<T> implements IGeneric<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> type;

    public GenericRepositoryImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T findById(Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
    }

    //@Transactional -> it means that it runs within a transactional context

    @Override
    @Transactional
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        entityManager.merge(t);
    }

    // Maybe unnecessary
    @Override
    @Transactional
    public void delete(T t) {
        entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        T t = findById(id);
        if (t != null) {
            delete(t);
        }
    }
}
