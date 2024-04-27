package org.example.repositories;

import org.example.models.ClassSession;
import org.example.models.Course;
import org.example.models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassSessionRepository implements GenericRepository<ClassSession> {
    private final Connection connection;

    public ClassSessionRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(ClassSession classSession) {
        String query = "INSERT INTO class_sessions (name, course_id, group_id, session_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, classSession.getName());
            statement.setLong(2, classSession.getCourse().getId());
            statement.setLong(3, classSession.getGroup().getId());
            statement.setString(4, classSession.getSessionDate().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<ClassSession> getAll() {
        List<ClassSession> classSessions = new ArrayList<>();
        String query = "SELECT * FROM class_sessions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long courseId = resultSet.getLong("course_id");
                Long groupId = resultSet.getLong("group_id");
                String sessionDate = resultSet.getString("session_date");
                Course course = new Course(courseId);
                Group group = new Group(groupId);
                classSessions.add(new ClassSession(id, name, course, group, sessionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classSessions;
    }

    @Override
    public ClassSession get(Long classId) {
        String query = "SELECT * FROM class_sessions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, classId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Long courseId = resultSet.getLong("course_id");
                Long groupId = resultSet.getLong("group_id");
                String sessionDate = resultSet.getString("session_date");
                Course course = new Course(courseId);
                Group group = new Group(groupId);
                return new ClassSession(classId, name, course, group, sessionDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(ClassSession classSession) {
        String query = "UPDATE class_sessions SET name = ?, course_id = ?, group_id = ?, session_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, classSession.getName());
            statement.setLong(2, classSession.getCourse().getId());
            statement.setLong(3, classSession.getGroup().getId());
            statement.setString(4, classSession.getSessionDate().toString());
            statement.setLong(5, classSession.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long classId) {
        String query = "DELETE FROM class_sessions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, classId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        String query = "SELECT COUNT(*) AS count FROM class_sessions";
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
