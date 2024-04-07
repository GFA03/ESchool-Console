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

    public void deleteTeacher(Long id) {
        teacherRepository.delete(id);
    }
}
