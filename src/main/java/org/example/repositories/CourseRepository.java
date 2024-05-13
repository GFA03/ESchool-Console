package org.example.repositories;

import org.example.models.Course;
import org.example.services.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements GenericRepository<Course> {
    private final Connection connection;
    private final AuditService auditService;

    public CourseRepository(Connection connection, AuditService auditService) {
        this.connection = connection;
        this.auditService = auditService;
    }

    @Override
    public void add(Course course) {
        String query = "INSERT INTO course (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.executeUpdate();
            auditService.logAdd("Course", course.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course get(Long id) {
        String query = "SELECT * FROM course WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                auditService.logGet("Courses", id.toString());
                return new Course(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                courses.add(new Course(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        auditService.logGet("All Courses", null);
        return courses;
    }

    @Override
    public void update(Course course) {
        String query = "UPDATE course SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getName());
            statement.setLong(2, course.getId());
            statement.executeUpdate();
            auditService.logUpdate("Courses", course.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM course WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            auditService.logDelete("Courses", id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        String query = "SELECT COUNT(*) AS count FROM course";
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
