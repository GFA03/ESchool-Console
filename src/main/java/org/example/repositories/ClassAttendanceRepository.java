package org.example.repositories;

import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.models.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassAttendanceRepository implements GenericRepository<ClassAttendance>{
    private final List<ClassAttendance> classAttendances;

    public ClassAttendanceRepository() {
        classAttendances = new ArrayList<>();
    }

    public void add(ClassAttendance classAttendance) {
        classAttendances.add(classAttendance);
    }

    public List<ClassAttendance> getAll() {
        return new ArrayList<>(classAttendances);
    }

    public ClassAttendance get(Long id) {
        for (ClassAttendance classAttendance : classAttendances) {
            if (classAttendance.getId().equals(id))
                return classAttendance;
        }
        return null;
    }
    public ClassAttendance get(Student student, ClassSession classSession) {
        for (ClassAttendance classAttendance : classAttendances) {
            if (classAttendance.getStudent().equals(student) && classAttendance.getClassSession().equals(classSession))
                return classAttendance;
        }
        return null;
    }

    public List<ClassAttendance> getByStudent(Student student) {
        List<ClassAttendance> studentClasses = new ArrayList<>();
        for(ClassAttendance classAtt: classAttendances) {
            if(classAtt.getStudent().equals(student))
                studentClasses.add(classAtt);
        }
        return studentClasses;
    }

    public void update(ClassAttendance updatedClassAttendance) {
        for (int i = 0; i < classAttendances.size(); i++) {
            ClassAttendance classAttendance = classAttendances.get(i);
            if (classAttendance.getStudent().equals(updatedClassAttendance.getStudent())
                    && classAttendance.getClassSession().equals(updatedClassAttendance.getClassSession())) {
                classAttendances.set(i, updatedClassAttendance);
                return;
            }
        }
    }

    public void delete(Long id) {
        classAttendances.removeIf(classAttendance ->
                classAttendance.getId().equals(id));
    }
    public void delete(Student student, ClassSession classSession) {
        classAttendances.removeIf(classAttendance ->
                classAttendance.getStudent().equals(student) && classAttendance.getClassSession().equals(classSession));
    }

    public int getSize() {
        return classAttendances.size();
    }

}
