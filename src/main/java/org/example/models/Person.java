package org.example.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Person {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;

    public Person(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        this.id = ID_SEQ++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    protected Person(Person person) {
        this.id = person.id;
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.dateOfBirth = person.dateOfBirth;
        this.email = person.email;
        this.phoneNumber = person.phoneNumber;
    }

    public int getAge() {
        return Math.abs(Period.between(LocalDate.now(), this.dateOfBirth).getYears());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(email, person.email) && Objects.equals(phoneNumber, person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Email: " + email + "\n" +
                "Phone Number: " + phoneNumber + "\n";
    }
}
