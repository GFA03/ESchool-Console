package org.example.models;

import java.util.Objects;

public class ClassAttendance {
    private Long id;
    private Student student;
    private ClassSession classSession;
    boolean present;
    Integer grade;

    public ClassAttendance(Long id, Student student, ClassSession classSession) {
        this.id = id;
        this.student = student;
        this.classSession = classSession;
        this.present = true;
        this.grade = null;
    }

    public ClassAttendance(Long id, Student student, ClassSession classSession, boolean present, Integer grade) {
        this.id = id;
        this.student = student;
        this.classSession = classSession;
        this.present = present;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        if(!present) return;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAttendance that = (ClassAttendance) o;
        return present == that.present && Objects.equals(grade, that.grade) && Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(classSession, that.classSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, classSession, present, grade);
    }

    @Override
    public String toString() {
        return "Class Attendance ID: " + id + "\n" +
                "Student ID: " + student.getId() + "\n" +
                "ClassSession ID: " + classSession.getId() + "\n" +
                "Present attendance: " + present + "\n" +
                "Grade attendance: " + (grade != null ? grade : "None") + "\n\n";
    }
}
