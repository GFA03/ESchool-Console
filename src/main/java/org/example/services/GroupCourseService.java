package org.example.services;

import org.example.models.GroupCourse;
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

    public GroupCourse getGroupCourseByGroupId(Long groupId) {
        return groupCourseRepository.get(groupId);
    }

    public GroupCourse getGroupCourseByCourseId(Long courseId) {
        return groupCourseRepository.getByCourseId(courseId);
    }

    public List<GroupCourse> getAllGroupCourses() {
        return groupCourseRepository.getAll();
    }

    public void updateGroupCourse(GroupCourse updatedGroupCourse) {
        groupCourseRepository.update(updatedGroupCourse);
    }

    public void deleteGroupCourse(GroupCourse groupCourse) {
        groupCourseRepository.delete(groupCourse);
    }
}
