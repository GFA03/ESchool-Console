package org.example.models;

import java.util.Objects;

public class ClassAttendance {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Student student;
    private ClassSession classSession;
    boolean present;
    Integer grade;

    public ClassAttendance(Student student, ClassSession classSession) {
        this.id = ID_SEQ++;
        this.student = student;
        this.classSession = classSession;
        this.present = true;
        this.grade = null;
    }

    public ClassAttendance(Student student, ClassSession classSession, boolean present, Integer grade) {
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
//        Cannot change to absent, if he has a grade
        if(grade != 0 && !present)
            return;
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
        return "ClassAttendance{" +
                "id=" + id +
                ", student=" + student +
                ", classSession=" + classSession +
                ", present=" + present +
                ", grade=" + grade +
                '}';
    }
}
