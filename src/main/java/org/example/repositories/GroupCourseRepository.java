package org.example.repositories;

import org.example.models.*;

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


    public void create(Group group, Course course, Teacher teacher) {
        groupCourses.add(new GroupCourse(group, course, teacher));
    }
    public List<GroupCourse> getAll() {
        return new ArrayList<>(groupCourses);
    }

    public GroupCourse get(Long groupCourseId) {
        for (GroupCourse groupCourse : groupCourses) {
            if (groupCourse.getId().equals(groupCourseId))
                return groupCourse;
        }
        return null;
    }

    public List<Teacher> getTeachersByStudent(Student student) {
        List<Teacher> teachers = new ArrayList<>();
        for (GroupCourse groupCourse: groupCourses) {
            if (groupCourse.getGroup() == student.getGroup())
                teachers.add(groupCourse.getTeacher());
        }
        return teachers;
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


    public void updateTeacher(GroupCourse groupCourse, Teacher newTeacher) {
        groupCourse.setTeacher(newTeacher);
        update(groupCourse);
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
