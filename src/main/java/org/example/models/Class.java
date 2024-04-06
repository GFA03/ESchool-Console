package org.example.models;

public class Class {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String name;

    private Course course;

    public Class(String name, Course course) {
        this.id = ID_SEQ++;
        this.name = name;
        this.course = course;
    }
}
