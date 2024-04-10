package org.example.repositories;

import org.example.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassAttendanceRepositoryTests {
    private ClassAttendanceRepository classAttendanceRepository;

    @BeforeEach
    void setUp() {
        classAttendanceRepository = new ClassAttendanceRepository();
    }

    @Test
    void addAndGetAll() {
        Group group = new Group("232");
        Course course = new Course("Matematica XII");
        ClassSession classSession = new ClassSession("Curs 1", course, group, "2024-04-03");
        Student student = new Student("John", "Doe", "2000-01-01", "john@example.com", "123456789", null, null);
        ClassAttendance classAttendance = new ClassAttendance(student, classSession, true, 90);

        classAttendanceRepository.add(classAttendance);
        assertEquals(1, classAttendanceRepository.getSize());

        assertEquals(classAttendance, classAttendanceRepository.getAll().getFirst());
    }

    @Test
    void getByStudentAndClassSession() {
        Group group = new Group("232");
        Course course = new Course("Matematica XII");
        ClassSession classSession = new ClassSession("Curs 1", course, group, "2024-04-03");
        Student student = new Student("John", "Doe", "2000-01-01", "john@example.com", "123456789", null, null);
        ClassAttendance classAttendance = new ClassAttendance(student, classSession, true, 2);

        classAttendanceRepository.add(classAttendance);

        assertEquals(classAttendance, classAttendanceRepository.get(student, classSession));
    }

    @Test
    void update() {
        Group group = new Group("232");
        Course course = new Course("Matematica XII");
        ClassSession classSession = new ClassSession("Curs 1", course, group, "2024-04-03");
        Student student = new Student("John", "Doe", "2000-01-01", "john@example.com", "123456789", null, null);
        ClassAttendance classAttendance = new ClassAttendance(student, classSession, true, 90);

        classAttendanceRepository.add(classAttendance);

        classAttendance.setGrade(95);
        classAttendanceRepository.update(classAttendance);

        assertEquals(95, classAttendanceRepository.get(student, classSession).getGrade());
    }

    @Test
    void delete() {
        Group group = new Group("232");
        Course course = new Course("Matematica XII");
        ClassSession classSession = new ClassSession("Curs 1", course, group, "2024-04-03");
        Student student = new Student("John", "Doe", "2000-01-01", "john@example.com", "123456789", null, null);
        ClassAttendance classAttendance = new ClassAttendance(student, classSession, true, 90);

        classAttendanceRepository.add(classAttendance);
        assertEquals(1, classAttendanceRepository.getSize());

        classAttendanceRepository.delete(student, classSession);
        assertEquals(0, classAttendanceRepository.getSize());
    }
}
