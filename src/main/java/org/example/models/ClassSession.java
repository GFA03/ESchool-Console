package org.example.models;

import java.time.LocalDate;

public class ClassSession {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String name;
    private Course course;
    private Group group;
    private LocalDate sessionDate;

    public ClassSession(String name, Course course, Group group, LocalDate sessionDate) {
        this.id = ID_SEQ++;
        this.name = name;
        this.course = course;
        this.group = group;
        this.sessionDate = sessionDate;
    }
}
