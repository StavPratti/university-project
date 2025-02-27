package org.university.controller;

import lombok.Data;
import org.university.model.Classes;
import org.university.model.Enrollment;
import org.university.model.Student;
import org.university.model.Teacher;
import org.university.service.*;
import org.university.utility.SemesterEnum;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@ApplicationScoped
@Data
public class ClassesController implements Serializable {

    @Inject
    private IClassesService classesService;

    @Inject
    private ITeacherService teacherService;

    private List<Classes> classes;
    private Classes selectedClass;
    private Classes newClass;
    private List<Enrollment> enrollments;
    private List<Teacher> teachers;
    private Teacher newTeacher;
    private Teacher teacher;
    private List<Classes> enrolledClasses;

    @PostConstruct
    public void init() {
        // Load teachers when the bean is created
        classes = classesService.findAllClassesWithTeachers();
        extractTeachersFromsClasses();
        newClass = new Classes();
        newClass.setTeacher(new Teacher());
    }

    private void extractTeachersFromsClasses() {
        teachers = teacherService.findAllTeachers();
//        teachers = new HashSet<>();
//        teachers = teacherService.findTeachersWithoutClasses();
//        for (Classes classes : classes) {
//            Teacher teacher = new Teacher();
//            teacher = classes.getTeacher();
//            if (teacher != null) { // Ensure teacher is not null
//                teachers.add(teacher); // Adds to the Set, avoiding duplicates
//                System.out.println("teacher added " + teacher.getName());
//            }
//        }
//        System.out.println("teachers extracted : " + teachers.size());
    }

    public void deleteClass(Classes classes) {
        selectedClass = classes;
        classesService.deleteClassesById(classes.getId());
        this.classes.remove(this.selectedClass);
        this.selectedClass = null;
    }

    // Set selected teacher
    public void selectClass(Classes classes) {
        this.selectedClass = classes;
        System.out.println("selected teacher : " + selectedClass.getTeacher().getName());
        System.out.println("selected class to edit : " + classes.getName());
    }

    public void updateClass() {
        this.selectedClass.setTeacher(teacherService.findTeacherById(selectedClass.getTeacher().getId()));
        classesService.updateClasses(this.selectedClass);
        System.out.println("updated to " + this.selectedClass.getTeacher().getName());
    }

    private SemesterEnum getCurrentSemester() {
        int month = LocalDate.now().getMonthValue();
        return (month >= 9 || month <= 1) ? SemesterEnum.A : SemesterEnum.B;
    }

    public void addNewClass() {
        Teacher teacher = teacherService.findTeacherById(newClass.getTeacher().getId());
        this.newClass.setTeacher(teacher);
        this.classesService.saveClasses(this.newClass);
        this.classes.add(this.newClass);
    }
}
