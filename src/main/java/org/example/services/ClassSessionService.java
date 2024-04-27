package org.example.services;

import org.example.models.ClassSession;
import org.example.models.Course;
import org.example.models.Group;
import org.example.repositories.ClassSessionRepository;

import java.util.List;

public class ClassSessionService {
    private final ClassSessionRepository classSessionRepository;

    public ClassSessionService(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    public void addClassSession(ClassSession classSession) {
        classSessionRepository.add(classSession);
    }

    public ClassSession getClassSessionById(Long id) {
        return classSessionRepository.get(id);
    }

    public List<ClassSession> getAllClassSessions() {
        return classSessionRepository.getAll();
    }

    public void updateClassSession(ClassSession updatedClassSession) {
        classSessionRepository.update(updatedClassSession);
    }

    public void updateClassSessionName(ClassSession classSession, String name) {
        classSession.setName(name);
        this.updateClassSession(classSession);
    }

    public void updateClassSessionSessionDate(ClassSession classSession, String sessionDate) {
        classSession.setSessionDate(sessionDate);
        this.updateClassSession(classSession);
    }

    public void deleteClassSession(Long id) {
        classSessionRepository.delete(id);
    }

    public int getSize() {
        return classSessionRepository.getSize();
    }
}
