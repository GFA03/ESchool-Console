package org.example.repositories;

import org.example.models.ClassSession;
import org.example.models.Course;
import org.example.models.Group;

import java.util.ArrayList;
import java.util.List;

public class ClassSessionRepository implements GenericRepository<ClassSession> {
    private final List<ClassSession> classes;

    public ClassSessionRepository() {
        classes = new ArrayList<>();
    }

    @Override
    public void add(ClassSession cls) {
        classes.add(cls);
    }

    public void create(String name, Course course, Group group, String sessionDate) {
        classes.add(new ClassSession(name, course, group, sessionDate));
    }

    @Override
    public List<ClassSession> getAll() {
        return classes;
    }

    @Override
    public ClassSession get(Long classId) {
        for (ClassSession cls : classes) {
            if (cls.getId().equals(classId))
                return cls;
        }
        return null;
    }

    @Override
    public void update(ClassSession updatedClass) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getId().equals(updatedClass.getId())) {
                classes.set(i, updatedClass);
                return;
            }
        }
    }

    public void updateName(ClassSession classSession, String name) {
        classSession.setName(name);
        update(classSession);
    }

    public void updateCourse(ClassSession classSession, Course course) {
        classSession.setCourse(course);
        update(classSession);
    }

    public void updateGroup(ClassSession classSession, Group group) {
        classSession.setGroup(group);
        update(classSession);
    }

    public void updateSessionDate(ClassSession classSession, String sessionDate) {
        classSession.setSessionDate(sessionDate);
        update(classSession);
    }

    @Override
    public void delete(Long classId) {
        classes.removeIf(cls -> cls.getId().equals(classId));
    }

    @Override
    public int getSize() {
        return classes.size();
    }
}
