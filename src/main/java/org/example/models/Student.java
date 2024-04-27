package org.example.models;

import java.util.Objects;

public class Student extends Person{
    private Parent parent;
    private Group group;
    public Student(Long id, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Parent parent, Group group) {
        super(id, firstName, lastName, dateOfBirth, email, phoneNumber);
        this.parent = parent;
        this.group = group;
    }

    public Student(Student student) {
        super(student);
        this.parent = student.parent;
        this.group = student.group;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(parent, student.parent) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parent, group);
    }


    @Override
    public String toString() {
        return "Student ID: " + super.getId() + "\n" +
                super.toString() +
                "Parent: " + (parent != null ? (parent.getFirstName() == null ? parent.getId() : parent.getFirstName() + " " + parent.getLastName()) : "None") + "\n" +
                "Group: " + (group != null ? (group.getName() == null ? group.getId() : group.getName()) : "None") + "\n\n";
    }

}
