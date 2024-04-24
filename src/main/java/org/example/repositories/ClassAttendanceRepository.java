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

    public void create(Student student, ClassSession classSession) {
        classAttendances.add(new ClassAttendance(1L, student, classSession));
    }

    public void create(Student student, ClassSession classSession, boolean present, Integer grade) {
        classAttendances.add(new ClassAttendance(1L, student, classSession, present, grade));
    }

    public void createForGroup(List<Student> studentByGroup, ClassSession classSession) {
        for(Student student: studentByGroup)
            create(student, classSession);
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

    public void update(ClassAttendance classAttendance, boolean present, Integer grade) {
        updatePresence(classAttendance, present);
        updateGrade(classAttendance, grade);
    }

    public void updatePresence(ClassAttendance classAttendance, boolean present) {
        classAttendance.setPresent(present);
        update(classAttendance);
    }

    public void updateGrade(ClassAttendance classAttendance, Integer grade) {
        classAttendance.setGrade(grade);
        update(classAttendance);
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
