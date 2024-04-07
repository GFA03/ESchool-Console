package org.example.models;

public class GroupCourse {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Group group;
    private Course course;
    private Teacher teacher;

    public GroupCourse(Long id, Group group, Course course, Teacher teacher) {
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
}
