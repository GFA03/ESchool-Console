package org.example.models;

public class Student extends Person{
    private Parent parent;
    public Student(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber);
        this.parent = parent;
    }
}
