package org.example.models;

public class Teacher extends Person{
    public Teacher(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Teacher ID: " + super.getId() + "\n" +
                super.toString() + "\n";
    }
}
