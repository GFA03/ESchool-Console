package org.example.models;

public class ClassAttendance {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Student student;
    private ClassSession classSession;
    boolean present;
    int grade;

    public ClassAttendance(Long id, Student student, ClassSession classSession, boolean present, int grade) {
        this.id = ID_SEQ++;
        this.student = student;
        this.classSession = classSession;
        this.present = present;
        this.grade = grade;
    }
}
