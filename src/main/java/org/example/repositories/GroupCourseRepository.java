package org.example.repositories;

import org.example.models.GroupCourse;

import java.util.ArrayList;
import java.util.List;

public class GroupCourseRepository implements GenericRepository<GroupCourse> {
    private final List<GroupCourse> groupCourses;

    public GroupCourseRepository() {
        groupCourses = new ArrayList<>();
    }

    public void add(GroupCourse groupCourse) {
        groupCourses.add(groupCourse);
    }

    public List<GroupCourse> getAll() {
        return new ArrayList<>(groupCourses);
    }

    public GroupCourse get(Long groupId) {
        for (GroupCourse groupCourse : groupCourses) {
            if (groupCourse.getId().equals(groupId))
                return groupCourse;
        }
        return null;
    }

    public GroupCourse getByCourseId(Long courseId) {
        for (GroupCourse groupCourse : groupCourses) {
            if (groupCourse.getId().equals(courseId))
                return groupCourse;
        }
        return null;
    }

    public void update(GroupCourse updatedGroupCourse) {
        for (int i = 0; i < groupCourses.size(); i++) {
            GroupCourse groupCourse = groupCourses.get(i);
            if (groupCourse.equals(updatedGroupCourse)) {
                groupCourses.set(i, updatedGroupCourse);
                return;
            }
        }
    }

    public void delete(Long id) {
        groupCourses.removeIf(groupCourse -> groupCourse.getId().equals(id));
    }

    public void delete(GroupCourse groupCourse) {
        groupCourses.remove(groupCourse);
    }

    public int getSize() {
        return groupCourses.size();
    }
}
