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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }
}
