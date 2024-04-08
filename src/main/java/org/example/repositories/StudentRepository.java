package org.example.repositories;

import org.example.exceptions.InvalidEmail;
import org.example.models.Group;
import org.example.models.Parent;
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

    public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        students.add(new Student(firstName, lastName, dateOfBirth, email, phoneNumber, null, null));
    }

    public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent, Group group) {
        students.add(new Student(firstName, lastName, dateOfBirth, email, phoneNumber, parent, group));
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

    public void updateStudentFirstName(Student student, String firstName) {
        student.setFirstName(firstName);
    }

    public void updateStudentLastName(Student student, String lastName) {
        student.setLastName(lastName);
    }

    public void updateStudentDateOfBirth(Student student, String dateOfBirth) {
        student.setDateOfBirth(dateOfBirth);
    }

    public void updateStudentEmail(Student student, String email) throws InvalidEmail {
        student.setEmail(email);
    }

    public void updateStudentPhoneNumber(Student student, String phoneNumber) {
        student.setPhoneNumber(phoneNumber);
    }

    public void updateStudentParent(Student student, Parent parent) {
        student.setParent(parent);
    }

    public void updateStudentGroup(Student student, Group group) {
        student.setGroup(group);
    }

    public void delete(Long studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    public int getSize() {
        return students.size();
    }

}
