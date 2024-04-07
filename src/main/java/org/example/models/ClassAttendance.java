package org.example.models;

import java.util.Objects;

public class ClassAttendance {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Student student;
    private ClassSession classSession;
    boolean present;
    int grade;

    public ClassAttendance(Student student, ClassSession classSession, boolean present, int grade) {
        this.id = ID_SEQ++;
        this.student = student;
        this.classSession = classSession;
        this.present = present;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassSession getClassSession() {
        return classSession;
    }

    public void setClassSession(ClassSession classSession) {
        this.classSession = classSession;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAttendance that = (ClassAttendance) o;
        return present == that.present && grade == that.grade && Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(classSession, that.classSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, classSession, present, grade);
    }
}
