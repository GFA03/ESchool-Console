package org.example.models;

public class Parent extends Person{
    public Parent(Long id, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        super(id, firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    public Parent(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Parent ID: " + super.getId() + "\n" +
                super.toString() + "\n";
    }
}
