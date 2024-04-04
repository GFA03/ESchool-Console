package org.example.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTests {
    @Test
    public void givenDateOfBirth_whenConstructing_thenCheckDateOfBirth() {
        Person person = new Person("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890");

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(LocalDate.parse("1990-01-01"), person.getDateOfBirth());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("1234567890", person.getPhoneNumber());
    }

    @Test
    public void givenPerson_whenGetAge_thenReturnsAge() {
        Person a = new Person("John", "Doe", "2003-03-23", "someemail@someemail.com", "0722222222");
        int expectedAge = Math.abs(Period.between(LocalDate.now(), a.getDateOfBirth()).getYears());
        assertEquals(expectedAge, a.getAge());
    }
}
