package org.university.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.university.utility.SemesterEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Use only explicitly included fields
@Table(name = "CLASSES")
public class Classes {

    @Id // Special annotation for Hibernate that indicates ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // An ID is automatically created
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Optimization
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SEMESTER", nullable = false)
    @Enumerated(EnumType.STRING)
    private SemesterEnum semester;

    // Relationship for join table
    @OneToMany(mappedBy = "classes")
    private Set<Enrollment> enrollments = new HashSet<>();

}

