package org.university.controller;

import lombok.Data;
import org.university.model.Classes;
import org.university.model.Teacher;
import org.university.service.ClassesServiceImpl;
import org.university.service.IClassesService;
import org.university.service.ITeacherService;
import org.university.service.TeacherServiceImpl;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@ApplicationScoped
@Data
public class TeacherController implements Serializable {

    @Inject
    private ITeacherService teacherService;

    @Inject
    private IClassesService classesService;

    private List<Teacher> teachers;
    private Teacher selectedTeacher;
    private Teacher newTeacher;

    @PostConstruct
    public void init() {
        // Load teachers when the bean is created
        teachers = teacherService.findAllTeachers();
        newTeacher = new Teacher();
    }



    public void deleteTeacher(Teacher teacher) {

        selectedTeacher = teacherService.findTeacherWithClasses(teacher.getId());
        if (selectedTeacher == null) {
            System.out.println("No teacher found with ID: " + teacher.getId());
            return;
        }
        Set<Classes> classes = selectedTeacher.getClasses();

        if (classes == null || classes.isEmpty()) {
            System.out.println("Teacher " + selectedTeacher.getName() + " has no classes!");
        } else {
            System.out.println("Teacher " + selectedTeacher.getName() + " has classes!");
            System.out.println("Classes number: " + classes.size());
        }
        teacherService.deleteTeacherById(teacher.getId());
        teachers.remove(selectedTeacher);
        this.selectedTeacher = null;
    }

    // Set selected teacher
    public void selectTeacher(Teacher teacher) {
        this.selectedTeacher = teacher;
    }

    public void updateTeacher() {
        teacherService.updateTeacher(selectedTeacher);
    }

    public void addNewTeacher() {
        this.teacherService.saveTeacher(this.newTeacher);
        teachers.add(this.newTeacher);
        this.newTeacher = new Teacher();
    }
}

