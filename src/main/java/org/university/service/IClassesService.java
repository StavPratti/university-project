package org.university.service;

import org.university.model.Classes;
import org.university.utility.SemesterEnum;

import java.util.List;

public interface IClassesService {

    Classes findClassesById(Long id);

    List<Classes> findAllClasses();

    void saveClasses(Classes newClasses);

    void deleteClassesById(Long id);

    void updateClasses(Classes updateClass);

    List<Classes> findClassesBySemester(SemesterEnum semester);

    List<Classes> findAllClassesWithTeachers();

    List<Classes> findAllClassesByStudentId(Long studentId);

}


