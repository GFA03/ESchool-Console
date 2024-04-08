package org.example.services;

import org.example.exceptions.InvalidEmail;
import org.example.models.Group;
import org.example.models.Parent;
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

    public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        studentRepository.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent, Group group) {
        studentRepository.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, parent, group);
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

    public void updateStudentFirstName(Student student, String firstName) { studentRepository.updateStudentFirstName(student, firstName);}
    public void updateStudentLastName(Student student, String lastName) { studentRepository.updateStudentLastName(student, lastName);}
    public void updateStudentDateOfBirth(Student student, String dateOfBirth) { studentRepository.updateStudentDateOfBirth(student, dateOfBirth);}
    public void updateStudentEmail(Student student, String email) throws InvalidEmail { studentRepository.updateStudentEmail(student, email);}
    public void updateStudentPhoneNumber(Student student, String phoneNumber) { studentRepository.updateStudentPhoneNumber(student, phoneNumber);}
    public void updateStudentParent(Student student, Parent parent) { studentRepository.updateStudentParent(student, parent);}
    public void updateStudentGroup(Student student, Group group) { studentRepository.updateStudentGroup(student, group);}

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }

    public int getSize() {
        return studentRepository.getSize();
    }
}
