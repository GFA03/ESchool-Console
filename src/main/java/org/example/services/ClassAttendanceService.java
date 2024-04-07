package org.example.services;

import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.models.Student;
import org.example.repositories.ClassAttendanceRepository;

import java.util.List;

public class ClassAttendanceService {
    private final ClassAttendanceRepository classAttendanceRepository;

    public ClassAttendanceService(ClassAttendanceRepository classAttendanceRepository) {
        this.classAttendanceRepository = classAttendanceRepository;
    }

    public void addClassAttendance(ClassAttendance classAttendance) {
        classAttendanceRepository.add(classAttendance);
    }

    public ClassAttendance getClassAttendance(Long id) {
        return classAttendanceRepository.get(id);
    }
    public ClassAttendance getClassAttendanceByStudentAndClassSession(Student student, ClassSession classSession) {
        return classAttendanceRepository.get(student, classSession);
    }

    public List<ClassAttendance> getAllClassAttendances() {
        return classAttendanceRepository.getAll();
    }

    public void updateClassAttendance(ClassAttendance updatedClassAttendance) {
        classAttendanceRepository.update(updatedClassAttendance);
    }

    public void deleteClassAttendance(Student student, ClassSession classSession) {
        classAttendanceRepository.delete(student, classSession);
    }
    public void deleteClassAttendance(Long id) {
        classAttendanceRepository.delete(id);
    }
}
