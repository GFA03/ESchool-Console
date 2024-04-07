package org.example.repositories;

import org.example.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements GenericRepository<Student>{
    private final List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
    }

    public void add (Student student) {
        students.add(student);
    }

    public List<Student> getAll() {
        return students;
    }

    public Student get(Long studentId) {
        for(Student student: students) {
            if(student.getId().equals(studentId))
                return student;
        }
        return null;
    }

    public void update(Student updatedStudent) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    public void delete(Long studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    public int getSize() {
        return students.size();
    }
}
