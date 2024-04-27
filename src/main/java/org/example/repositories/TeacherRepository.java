package org.example.repositories;

import org.example.models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements GenericRepository<Teacher> {
    private final Connection connection;

    public TeacherRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Teacher teacher) {
        String query = "INSERT INTO teachers (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getDateOfBirth());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getPhoneNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to add teacher, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Teacher teacher = new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Teacher get(Long teacherId) {
        String query = "SELECT * FROM teachers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Teacher(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("date_of_birth"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Teacher updatedTeacher) {
        String query = "UPDATE teachers SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedTeacher.getFirstName());
            statement.setString(2, updatedTeacher.getLastName());
            statement.setString(3, updatedTeacher.getDateOfBirth());
            statement.setString(4, updatedTeacher.getEmail());
            statement.setString(5, updatedTeacher.getPhoneNumber());
            statement.setLong(6, updatedTeacher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long teacherId) {
        String query = "DELETE FROM teachers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, teacherId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        String query = "SELECT COUNT(*) AS count FROM teachers";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
