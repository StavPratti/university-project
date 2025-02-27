package org.university.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

import java.util.HashSet;

@Data
@Entity // Table in Database
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Use only explicitly included fields
@Table(name = "TEACHER") // Table's name
public class Teacher {

    @Id // Special annotation for Hibernate that indicates ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // An ID is automatically created
    @Column(updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<Classes> classes = new HashSet<>();

}