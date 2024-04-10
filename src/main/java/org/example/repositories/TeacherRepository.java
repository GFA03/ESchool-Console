package org.example.repositories;

import org.example.exceptions.InvalidEmail;
import org.example.models.Student;
import org.example.models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements GenericRepository<Teacher> {
    private final List<Teacher> teachers;

    public TeacherRepository() {
        teachers = new ArrayList<>();
    }
    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    public void create(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        teachers.add(new Teacher(firstName, lastName, dateOfBirth, email, phoneNumber));
    }

    @Override
    public List<Teacher> getAll() {
        return teachers;
    }

    @Override
    public Teacher get(Long teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId))
                return teacher;
        }
        return null;
    }

    @Override
    public void update(Teacher updatedTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(updatedTeacher.getId())) {
                teachers.set(i, updatedTeacher);
                return;
            }
        }
    }

    public void updateFirstName(Teacher teacher, String firstName) {
        teacher.setFirstName(firstName);
        update(teacher);
    }

    public void updateLastName(Teacher teacher, String lastName) {
        teacher.setLastName(lastName);
        update(teacher);
    }

    public void updateDateOfBirth(Teacher teacher, String dateOfBirth) {
        teacher.setDateOfBirth(dateOfBirth);
        update(teacher);
    }

    public void updateEmail(Teacher teacher, String email) {
        teacher.setEmail(email);
        update(teacher);
    }

    public void updatePhoneNumber(Teacher teacher, String phoneNumber) {
        teacher.setPhoneNumber(phoneNumber);
        update(teacher);
    }


    @Override
    public void delete(Long teacherId) {
        teachers.removeIf(teacher -> teacher.getId().equals(teacherId));
    }

    @Override
    public int getSize() {
        return teachers.size();
    }

}
