package org.university.repository;

import org.university.model.Enrollment;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnrollmentRepository extends GenericRepositoryImpl<Enrollment> {

        public EnrollmentRepository() { super(Enrollment.class);
    }
}
