package org.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@Embeddable
// Just a plain Java Bean that represents the compose key
public class EnrollmentId implements java.io.Serializable {

    //private static final long serialVersionUID = -2474843182060818660L;
    //@Column(name = "STUDENT_ID")
    protected Long studentId;

    //@Column(name = "CLASS_ID")
    protected Long classId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnrollmentId that = (EnrollmentId) o;

        if (!studentId.equals(that.studentId)) return false;
        return classId.equals(that.classId);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + classId.hashCode();
        return result;
    }
}
