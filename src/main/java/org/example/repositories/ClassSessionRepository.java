package org.example.repositories;

import org.example.models.ClassSession;

import java.util.ArrayList;
import java.util.List;

public class ClassSessionRepository implements GenericRepository<ClassSession> {
    private final List<ClassSession> classes;

    public ClassSessionRepository() {
        classes = new ArrayList<>();
    }

    public void add(ClassSession cls) {
        classes.add(cls);
    }

    public List<ClassSession> getAll() {
        return classes;
    }

    public ClassSession get(Long classId) {
        for (ClassSession cls : classes) {
            if (cls.getId().equals(classId))
                return cls;
        }
        return null;
    }

    public void update(ClassSession updatedClass) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getId().equals(updatedClass.getId())) {
                classes.set(i, updatedClass);
                return;
            }
        }
    }

    public void delete(Long classId) {
        classes.removeIf(cls -> cls.getId().equals(classId));
    }

    public int getSize() {
        return classes.size();
    }
}
