package org.example.services;

import org.apache.commons.lang3.tuple.Pair;
import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.models.Student;
import org.example.repositories.ClassAttendanceRepository;

import java.util.ArrayList;
import java.util.List;

public class ClassAttendanceService {
    private final ClassAttendanceRepository classAttendanceRepository;

    public ClassAttendanceService(ClassAttendanceRepository classAttendanceRepository) {
        this.classAttendanceRepository = classAttendanceRepository;
    }

    public void addClassAttendance(ClassAttendance classAttendance) {
        classAttendanceRepository.add(classAttendance);
    }

    public void createForGroup(List<Student> studentsByGroup, ClassSession classSession) {
        classAttendanceRepository.createForGroup(studentsByGroup, classSession);
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

    public List<Pair<Integer, Long>> getStudentGrades(Student student) {
        List<ClassAttendance> studentAttendances = classAttendanceRepository.getByStudent(student);
        List<Pair<Integer, Long>> studentGrades = new ArrayList<>();
        for(ClassAttendance classAtt: studentAttendances) {
            Integer grade = classAtt.getGrade();
            Long classId = classAtt.getClassSession().getId();
            if(grade != null)
                studentGrades.add(Pair.of(grade, classId));
        }
        return studentGrades;
    }
    public List<ClassAttendance> getStudentActivity(Student student) {
        return classAttendanceRepository.getByStudent(student);
    }

    public void updateClassAttendance(ClassAttendance updatedClassAttendance) {
        classAttendanceRepository.update(updatedClassAttendance);
    }

    public void updateClassAttendance(ClassAttendance classAttendance, boolean present, Integer grade) {
        classAttendanceRepository.update(classAttendance, present, grade);
    }

    public void deleteClassAttendance(Student student, ClassSession classSession) {
        classAttendanceRepository.delete(student, classSession);
    }
    public void deleteClassAttendance(Long id) {
        classAttendanceRepository.delete(id);
    }

    public int getSize() {
        return classAttendanceRepository.getSize();
    }
}
