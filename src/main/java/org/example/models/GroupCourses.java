package org.example.models;

public class GroupCourses {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Group group;
    private Course course;
    private Teacher teacher;

    public GroupCourses(Long id, Group group, Course course, Teacher teacher) {
        this.id = ID_SEQ++;
        this.group = group;
        this.course = course;
        this.teacher = teacher;
    }
}
