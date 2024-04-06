package org.example.models;

import java.util.Objects;

public class Student extends Person{
    private Parent parent;
    public Student(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber);
        this.parent = parent;
    }

    public Student(Student student) {
        super((Person)(student));
        this.parent = student.parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(parent, student.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parent);
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "parent=" + parent +
                '}';
    }
}
