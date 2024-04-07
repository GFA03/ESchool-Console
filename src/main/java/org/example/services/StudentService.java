package org.example.services;

import org.example.models.Student;
import org.example.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.add(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.get(id);
    }

    public void updateStudent(Student updatedStudent) {
        studentRepository.update(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }
}
