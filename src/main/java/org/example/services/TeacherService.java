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

    public Teacher getTeacherById(Long id) {
        return teacherRepository.get(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAll();
    }

    public void updateTeacher(Teacher updatedTeacher) {
        teacherRepository.update(updatedTeacher);
    }

    public void updateTeacherFirstName(Teacher teacher, String firstName) {
        teacher.setFirstName(firstName);
        teacherRepository.update(teacher);
    }
    public void updateTeacherLastName(Teacher teacher, String lastName) {
        teacher.setLastName(lastName);
        teacherRepository.update(teacher);
    }
    public void updateTeacherDateOfBirth(Teacher teacher, String dateOfBirth) {
        teacher.setDateOfBirth(dateOfBirth);
        teacherRepository.update(teacher);
    }
    public void updateTeacherEmail(Teacher teacher, String email) {
        teacher.setEmail(email);
        teacherRepository.update(teacher);
    }
    public void updateTeacherPhoneNumber(Teacher teacher, String phoneNumber) {
        teacher.setPhoneNumber(phoneNumber);
        teacherRepository.update(teacher);
    }
    public void deleteTeacher(Long id) {
        teacherRepository.delete(id);
    }

    public int getSize() {
        return teacherRepository.getSize();
    }
}
