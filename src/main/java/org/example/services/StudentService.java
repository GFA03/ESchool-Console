package org.example.services;

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
        updateStudent(student);

    }
    public void updateStudentLastName(Student student, String lastName) {
        student.setLastName(lastName);
        updateStudent(student);
    }
    public void updateStudentDateOfBirth(Student student, String dateOfBirth) {
        student.setDateOfBirth(dateOfBirth);
        updateStudent(student);
    }
    public void updateStudentEmail(Student student, String email) {
        student.setEmail(email);
        updateStudent(student);
    }
    public void updateStudentPhoneNumber(Student student, String phoneNumber) {
        student.setPhoneNumber(phoneNumber);
        updateStudent(student);
    }
    public void updateStudentParent(Student student, Parent parent) {
        student.setParent(parent);
        updateStudent(student);
    }
    public void updateStudentGroup(Student student, Group group) {
        student.setGroup(group);
        updateStudent(student);
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
