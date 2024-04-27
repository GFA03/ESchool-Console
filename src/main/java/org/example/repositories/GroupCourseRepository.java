package org.example.repositories;

import org.example.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCourseRepository implements GenericRepository<GroupCourse> {
    private final Connection connection;

    public GroupCourseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(GroupCourse groupCourse) {
        String query = "INSERT INTO group_courses (group_id, course_id, teacher_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupCourse.getGroup().getId());
            statement.setLong(2, groupCourse.getCourse().getId());
            statement.setLong(3, groupCourse.getTeacher().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GroupCourse> getAll() {
        List<GroupCourse> groupCourses = new ArrayList<>();
        String query = "SELECT * FROM group_courses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long groupId = resultSet.getLong("group_id");
                Long courseId = resultSet.getLong("course_id");
                Long teacherId = resultSet.getLong("teacher_id");
                Group group = new GroupRepository(connection).get(groupId);
                Course course = new CourseRepository(connection).get(courseId);
                Teacher teacher = new TeacherRepository(connection).get(teacherId);
                groupCourses.add(new GroupCourse(id, group, course, teacher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupCourses;
    }

    @Override
    public GroupCourse get(Long groupCourseId) {
        String query = "SELECT * FROM group_courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupCourseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long groupId = resultSet.getLong("group_id");
                Long courseId = resultSet.getLong("course_id");
                Long teacherId = resultSet.getLong("teacher_id");
                Group group = new GroupRepository(connection).get(groupId);
                Course course = new CourseRepository(connection).get(courseId);
                Teacher teacher = new TeacherRepository(connection).get(teacherId);
                return new GroupCourse(groupCourseId, group, course, teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Teacher> getTeachersByStudent(Student student) {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT teacher_id FROM group_courses WHERE group_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, student.getGroup().getId());
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long teacherId = resultSet.getLong("teacher_id");
                Teacher teacher = new TeacherRepository(connection).get(teacherId);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public void update(GroupCourse groupCourse) {
        String query = "UPDATE group_courses SET group_id = ?, course_id = ?, teacher_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupCourse.getGroup().getId());
            statement.setLong(2, groupCourse.getCourse().getId());
            statement.setLong(3, groupCourse.getTeacher().getId());
            statement.setLong(4, groupCourse.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM group_courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        String query = "SELECT COUNT(*) AS count FROM group_courses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                size = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }
}
