package org.example.repositories;

import org.example.models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements GenericRepository<Teacher> {
    private final List<Teacher> teachers;

    public TeacherRepository() {
        teachers = new ArrayList<>();
    }

    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    public List<Teacher> getAll() {
        return teachers;
    }

    public Teacher get(Long teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId))
                return teacher;
        }
        return null;
    }

    public void update(Teacher updatedTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(updatedTeacher.getId())) {
                teachers.set(i, updatedTeacher);
                return;
            }
        }
    }

    public void delete(Long teacherId) {
        teachers.removeIf(teacher -> teacher.getId().equals(teacherId));
    }

    public int getSize() {
        return teachers.size();
    }
}
