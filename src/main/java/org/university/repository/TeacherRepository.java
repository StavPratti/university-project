package org.university.repository;

import org.university.model.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class TeacherRepository extends GenericRepositoryImpl<Teacher> {

    @PersistenceContext
    private EntityManager entityManager;

    public TeacherRepository() {
        super(Teacher.class);
    }

    @Transactional // Ensure transactional context
    public Teacher findTeacherWithClasses(Long id) {
        TypedQuery<Teacher> query = entityManager.createQuery(
                "SELECT t FROM Teacher t LEFT JOIN FETCH t.classes WHERE t.id = :teacherId", Teacher.class);
        query.setParameter("teacherId", id);

        return query.getSingleResult(); // Ensure you handle NoResultException if needed
    }

    // Teachers without registered class
    @Transactional
    public Set<Teacher> findTeachersWithoutClasses() {
        String jpql = "SELECT t FROM Teacher t LEFT JOIN FETCH t.classes c WHERE c IS NULL";

        // Create a TypedQuery for Teacher with eager fetching
        TypedQuery<Teacher> query = entityManager.createQuery(jpql, Teacher.class);

        // Return as a Set to match the return type
        return new HashSet<>(query.getResultList());
    }
}
