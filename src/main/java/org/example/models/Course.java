package org.example.models;

import java.util.Objects;

public class Course {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String name;

    public Course(String name) {
        this.id = ID_SEQ++;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Course ID: " + id + "\n" +
                "Name: " + name + "\n\n";
    }
}
