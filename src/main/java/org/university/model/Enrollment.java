package org.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "ENROLLMENT")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

//    @Id // Special annotation for Hibernate that indicates ID
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // An ID is automatically created
//    @Column(updatable = false)
//    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date enrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("classId")
    @JoinColumn(name = "CLASSES_ID")
    private Classes classes;

}
