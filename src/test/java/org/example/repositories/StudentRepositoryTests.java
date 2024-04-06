package org.example.repositories;

import org.example.models.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRepositoryTests {
    @Test
    public void given2Students_whenGettingAll_thenCheckAllThere() {
        Student student1 = new Student("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", null);
        Student student2 = new Student("Dobre", "Alexe", "2004-06-01", "dobre.alexe@example.com", "1234567890", null);
        StudentRepository students = new StudentRepository();
        students.add(student1);
        students.add(student2);
        assertEquals(2, students.getSize());
    }

    @Test
    public void given2Students_whenDeleting_thenCheckDeleteSuccessfully() {
        Student student1 = new Student("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", null);
        Student student2 = new Student("Dobre", "Alexe", "2004-06-01", "dobre.alexe@example.com", "1234567890", null);
        StudentRepository students = new StudentRepository();
        students.add(student1);
        students.add(student2);
        Long studentId = students.getAll().getFirst().getId();
        students.delete(studentId);
        assertEquals(1, students.getSize());
    }
}
