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

    @Override
    public void add (Student student) {
        students.add(student);
    }

    public void create(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        students.add(new Student(firstName, lastName, dateOfBirth, email, phoneNumber, null, null));
    }

    public void create(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent, Group group) {
        students.add(new Student(firstName, lastName, dateOfBirth, email, phoneNumber, parent, group));
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public Student get(Long studentId) {
        for(Student student: students) {
            if(student.getId().equals(studentId))
                return student;
        }
        return null;
    }

    public List<Student> getByParent(Parent parent) {
        List<Student> children = new ArrayList<>();
        for (Student student: students) {
            if (student.getParent().equals(parent)) {
               children.add(student);
            }
        }
        return children;
    }

    public List<Student> getByGroup(Group group) {
        List<Student> studentsByGroup = new ArrayList<>();
        for (Student student: students) {
            if (student.getGroup().equals(group)) {
                studentsByGroup.add(student);
            }
        }
        return studentsByGroup;
    }

    @Override
    public void update(Student updatedStudent) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    public void updateFirstName(Student student, String firstName) {
        student.setFirstName(firstName);
        update(student);
    }

    public void updateLastName(Student student, String lastName) {
        student.setLastName(lastName);
        update(student);
    }

    public void updateDateOfBirth(Student student, String dateOfBirth) {
        student.setDateOfBirth(dateOfBirth);
        update(student);
    }

    public void updateEmail(Student student, String email) {
        student.setEmail(email);
        update(student);
    }

    public void updatePhoneNumber(Student student, String phoneNumber) {
        student.setPhoneNumber(phoneNumber);
        update(student);
    }

    public void updateParent(Student student, Parent parent) {
        student.setParent(parent);
        update(student);
    }

    public void updateGroup(Student student, Group group) {
        student.setGroup(group);
        update(student);
    }

    @Override
    public void delete(Long studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    @Override
    public int getSize() {
        return students.size();
    }

}
