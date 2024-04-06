package org.example.models;

public class Course{
    private Long courseId;
    private static Long ID_SEQ = 1L;
    private String courseName;
    private int numberOfCredits;

    public Course(String courseName, int numberOfCredits) {
        this.courseId = ID_SEQ++;
        this.courseName = courseName;
        this.numberOfCredits = numberOfCredits;
    }
}
