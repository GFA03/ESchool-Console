package org.example.services;

import org.example.models.*;
import org.example.repositories.GroupCourseRepository;

import java.util.List;

public class GroupCourseService {
    private final GroupCourseRepository groupCourseRepository;

    public GroupCourseService(GroupCourseRepository groupCourseRepository) {
        this.groupCourseRepository = groupCourseRepository;
    }

    public void addGroupCourse(GroupCourse groupCourse) {
        groupCourseRepository.add(groupCourse);
    }

    public GroupCourse getGroupCourse(Long groupCourseId) {
        return groupCourseRepository.get(groupCourseId);
    }

    public List<GroupCourse> getAllGroupCourses() {
        return groupCourseRepository.getAll();
    }

    public List<Teacher> getStudentTeachers(Student student) {
        return groupCourseRepository.getTeachersByStudent(student);
    }

    public void updateGroupCourse(GroupCourse updatedGroupCourse) {
        groupCourseRepository.update(updatedGroupCourse);
    }

    public void updateTeacher(GroupCourse groupCourse, Teacher newTeacher) {
        groupCourse.setTeacher(newTeacher);
        updateGroupCourse(groupCourse);
    }

    public void deleteGroupCourse(GroupCourse groupCourse) {
        groupCourseRepository.delete(groupCourse.getId());
    }

    public void deleteGroupCourse(Long id) { groupCourseRepository.delete(id);}

    public int getSize() {
        return groupCourseRepository.getSize();
    }
}
