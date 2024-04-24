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

    public void add(Student student) {
        studentRepository.add(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.get(id);
    }

    public List<Student> getAfterParent(Parent parent) { return studentRepository.getByParent(parent);}

    public List<Student> getAfterGroup(Group group) { return studentRepository.getByGroup(group);}

    public void updateStudent(Student updatedStudent) {
        studentRepository.update(updatedStudent);
    }

    public void updateStudentFirstName(Student student, String firstName) { studentRepository.updateFirstName(student, firstName);}
    public void updateStudentLastName(Student student, String lastName) { studentRepository.updateLastName(student, lastName);}
    public void updateStudentDateOfBirth(Student student, String dateOfBirth) { studentRepository.updateDateOfBirth(student, dateOfBirth);}
    public void updateStudentEmail(Student student, String email) { studentRepository.updateEmail(student, email);}
    public void updateStudentPhoneNumber(Student student, String phoneNumber) { studentRepository.updatePhoneNumber(student, phoneNumber);}
    public void updateStudentParent(Student student, Parent parent) { studentRepository.updateParent(student, parent);}
    public void updateStudentGroup(Student student, Group group) { studentRepository.updateGroup(student, group);}

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }

    public int getSize() {
        return studentRepository.getSize();
    }
}
