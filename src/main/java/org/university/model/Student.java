package org.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Calendar ;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "STUDENT")
public class Student {

    @Id // Special annotation for Hibernate that indicates ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // An ID is automatically created
    @Column(updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

    @Column(name = "MOBILE_PHONE", nullable = false)
    private String mobilePhone;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

}
