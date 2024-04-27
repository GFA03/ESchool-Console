package org.example.services;

import org.example.exceptions.InvalidEmail;
import org.example.models.ClassAttendance;
import org.example.models.Group;
import org.example.models.Parent;
import org.example.models.Student;
import org.example.repositories.StudentRepository;

import java.util.Arrays;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassAttendanceService classAttendanceService;

    public StudentService(StudentRepository studentRepository, ClassAttendanceService classAttendanceService) {
        this.studentRepository = studentRepository;
        this.classAttendanceService = classAttendanceService;
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

    public void updateStudentFirstName(Student student, String firstName) {
        student.setFirstName(firstName);
        studentRepository.update(student);

    }
    public void updateStudentLastName(Student student, String lastName) {
        student.setLastName(lastName);
        studentRepository.update(student);
    }
    public void updateStudentDateOfBirth(Student student, String dateOfBirth) {
        student.setDateOfBirth(dateOfBirth);
        studentRepository.update(student);
    }
    public void updateStudentEmail(Student student, String email) {
        student.setEmail(email);
        studentRepository.update(student);
    }
    public void updateStudentPhoneNumber(Student student, String phoneNumber) {
        student.setPhoneNumber(phoneNumber);
        studentRepository.update(student);
    }
    public void updateStudentParent(Student student, Parent parent) {
        student.setParent(parent);
        studentRepository.update(student);
    }
    public void updateStudentGroup(Student student, Group group) {
        student.setGroup(group);
        studentRepository.update(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }

    public int getSize() {
        return studentRepository.getSize();
    }

    public void showStats(Student student) {
        System.out.println("Stats for: " + student.getFirstName() + " " + student.getLastName());
        List<ClassAttendance> activity = classAttendanceService.getStudentActivity(student);
        System.out.println(Arrays.toString(activity.toArray()));
    }
}
