package org.example.models;

import java.time.LocalDate;
import java.util.Objects;

public class ClassSession {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String name;
    private Course course;
    private Group group;
    private LocalDate sessionDate;

    public ClassSession(String name, Course course, Group group, String sessionDate) {
        this.id = ID_SEQ++;
        this.name = name;
        this.course = course;
        this.group = group;
        this.sessionDate = LocalDate.parse(sessionDate);
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

    public void setSessionDate(String sessionDate) {
        this.sessionDate = LocalDate.parse(sessionDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSession that = (ClassSession) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(course, that.course) && Objects.equals(group, that.group) && Objects.equals(sessionDate, that.sessionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, course, group, sessionDate);
    }

    @Override
    public String toString() {
        return "ClassSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", group=" + group +
                ", sessionDate=" + sessionDate +
                '}';
    }
}
