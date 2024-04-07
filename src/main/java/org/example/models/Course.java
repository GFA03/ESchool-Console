package org.example.models;

public class Course {
    private final Long courseId;
    private static Long ID_SEQ = 1L;
    private String courseName;

    public Course(String courseName) {
        this.courseId = ID_SEQ++;
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
