package org.example.models;

public class Parent extends Person{
    public Parent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Parent ID: " + super.getId() + "\n" +
                super.toString() + "\n";
    }
}
