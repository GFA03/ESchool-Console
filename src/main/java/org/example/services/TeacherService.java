package org.example.services;

import org.example.models.Teacher;
import org.example.repositories.TeacherRepository;

import java.util.List;

public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.add(teacher);
    }

    public void createTeacher(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        teacherRepository.create(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.get(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAll();
    }

    public void updateTeacher(Teacher updatedTeacher) {
        teacherRepository.update(updatedTeacher);
    }

    public void updateTeacherFirstName(Teacher teacher, String firstName) { teacherRepository.updateFirstName(teacher, firstName);}
    public void updateTeacherLastName(Teacher teacher, String lastName) { teacherRepository.updateLastName(teacher, lastName);}
    public void updateTeacherDateOfBirth(Teacher teacher, String dateOfBirth) { teacherRepository.updateDateOfBirth(teacher, dateOfBirth);}
    public void updateTeacherEmail(Teacher teacher, String email) { teacherRepository.updateEmail(teacher, email);}
    public void updateTeacherPhoneNumber(Teacher teacher, String phoneNumber) { teacherRepository.updatePhoneNumber(teacher, phoneNumber);}
    public void deleteTeacher(Long id) {
        teacherRepository.delete(id);
    }

    public int getSize() {
        return teacherRepository.getSize();
    }
}
