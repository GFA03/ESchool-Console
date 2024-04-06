package org.example.models;

public class StudentGroup {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private Student student;
    private Group group;

    public StudentGroup(Long id, Student student, Group group) {
        this.id = ID_SEQ++;
        this.student = student;
        this.group = group;
    }
}
