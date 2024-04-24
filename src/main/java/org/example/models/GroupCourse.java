package org.example.models;

import java.util.Objects;

public class GroupCourse {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Group group;
    private Course course;
    private Teacher teacher;

    public GroupCourse(Group group, Course course, Teacher teacher) {
        this.id = ID_SEQ++;
        this.group = group;
        this.course = course;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCourse that = (GroupCourse) o;
        return Objects.equals(id, that.id) && Objects.equals(group, that.group) && Objects.equals(course, that.course) && Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, course, teacher);
    }

    @Override
    public String toString() {
        return "GroupCourse ID: " + id + "\n" +
                "Group: " + group.getId() + "\n" +
                "Course: " + course.getId() + "\n" +
                "Teacher: " + teacher.getId() + "\n\n";
    }
}
