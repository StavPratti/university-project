package org.university.service;

import org.university.model.Classes;
import org.university.repository.ClassesRepository;
import org.university.utility.SemesterEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Stateless
public class ClassesServiceImpl implements IClassesService {

    // Dependency of Injection, calling the corresponding Repository
    @Inject
    private ClassesRepository classesRepository;

    @Inject
    public ClassesServiceImpl() {
    }

    @Override
    public Classes findClassesById(Long id) {
        return classesRepository.findById(id);
    }

    @Override
    public List<Classes> findAllClasses() {
        return classesRepository.findAll();
    }

    @Override
    public void saveClasses(Classes newClasses) {
        classesRepository.save(newClasses);
    }

    @Override
    public void deleteClassesById(Long id) {
        classesRepository.deleteById(id);
    }

    @Override
    public void updateClasses(Classes updateClass) {
        classesRepository.update(updateClass);
    }

    @Override
    public List<Classes> findClassesBySemester(SemesterEnum semester) {
        return classesRepository.findClassesBySemester(semester);
    }

    @Override
    public List<Classes> findAllClassesWithTeachers() {
        return classesRepository.findAllClassesWithTeachers();
    }

    @Override
    public List<Classes> findAllClassesByStudentId(Long studentId) {
        return classesRepository.findAllClassesByStudentId(studentId);
    }

}
