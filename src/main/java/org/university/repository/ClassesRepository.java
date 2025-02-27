package org.university.repository;

import org.apache.poi.ss.formula.functions.T;
import org.university.model.Classes;
import org.university.model.Enrollment;
import org.university.utility.SemesterEnum;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClassesRepository extends GenericRepositoryImpl<Classes>{

    @PersistenceContext
    private EntityManager entityManager;

    public ClassesRepository() {
        super(Classes.class);
    }

    public ClassesRepository(Class<Classes> type) {
        super(type);
    }

    // Classes by semester
    public List<Classes> findClassesBySemester(SemesterEnum semester) {
        String jpql = "SELECT c FROM Classes c WHERE c.semester = :semester";
        TypedQuery<Classes> query = entityManager.createQuery(jpql, Classes.class);
        query.setParameter("semester", semester);
        return query.getResultList();
    }

    @Transactional
    public List<Classes> findAllClassesByStudentId(Long studentId) {
        // JPQL query to fetch enrollments along with their associated classes
        String query = "SELECT e FROM Enrollment e JOIN FETCH e.classes WHERE e.student.id = :studentId";
        TypedQuery<Enrollment> typedQuery = entityManager.createQuery(query, Enrollment.class);
        typedQuery.setParameter("studentId", studentId);
        List<Enrollment> enrollments = typedQuery.getResultList();
        // Extract classes from enrollments
        List<Classes> classes = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            classes.add(enrollment.getClasses());
        }

        return classes;
    }

    @Transactional
    public List<Classes> findAllClassesWithTeachers() {
        return entityManager.createQuery(
                "SELECT c FROM Classes c " +
                        "LEFT JOIN FETCH c.teacher t",
                Classes.class
        ).getResultList();
    }

    @Transactional
    public List<Classes> findAllByStudentId(Long studentId) {
        String query ="SELECT e FROM Enrollment e JOIN FETCH e.classes where e.student.id = :studentId";
        List<Enrollment> enrollments = entityManager.createQuery(query, Enrollment.class).
                setParameter("studentId",studentId).getResultList();
        List<Classes> classes = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            classes.add(enrollment.getClasses());
        }
        return classes;
    }
}


