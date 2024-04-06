package org.example.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTests {
    @Test
    public void givenStudent_whenConstructing_thenCheckEveryField() {
        Student student = new Student("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", null);
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals(LocalDate.parse("1990-01-01"), student.getDateOfBirth());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals("1234567890", student.getPhoneNumber());
    }

    @Test
    public void givenStudent_whenCopyConstructing_thenCheckEqual() {
        Student student1 = new Student("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", null);
        Student student2 = new Student(student1);
        assertEquals(student1, student2);
    }

    @Test
    public void givenStudent_whenCopyConstructing_thenCheckNotEqualIfDataChanged() {
        Student student1 = new Student("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", null);
        Student student2 = new Student(student1);
        student2.setEmail("test");
        Assertions.assertNotEquals(student1, student2);
    }
}
