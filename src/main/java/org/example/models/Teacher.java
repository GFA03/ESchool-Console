package org.example.models;

public class Teacher extends Person{
    public Teacher(Long id, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        super(id, firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Teacher ID: " + super.getId() + "\n" +
                super.toString() + "\n";
    }
}
