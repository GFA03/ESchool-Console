package org.example.repositories;

import org.example.models.Group;
import org.example.models.Parent;
import org.example.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements GenericRepository<Student> {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Student student) {
        String query = "INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number, parent_id, group_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getDateOfBirth());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhoneNumber());
            if (student.getParent() != null) {
                statement.setLong(6, student.getParent().getId());
            } else {
                statement.setNull(6, Types.NULL);
            }
            if (student.getGroup() != null) {
                statement.setLong(7, student.getGroup().getId());
            } else {
                statement.setNull(7, Types.NULL);
            }
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to add student, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Failed to add student, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        null,
                        null
                );
                long parentId = resultSet.getLong("parent_id");
                if (!resultSet.wasNull()) {
                    student.setParent(new Parent(parentId));
                }
                long groupId = resultSet.getLong("group_id");
                if (!resultSet.wasNull()) {
                    student.setGroup(new Group(groupId));
                }
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student get(Long studentId) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("date_of_birth"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            null,
                            null
                    );
                    long parentId = resultSet.getLong("parent_id");
                    if (!resultSet.wasNull()) {
                        student.setParent(new Parent(parentId));
                    }
                    long groupId = resultSet.getLong("group_id");
                    if (!resultSet.wasNull()) {
                        student.setGroup(new Group(groupId)); // Assuming you have a Group constructor that takes an ID
                    }
                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getByParent(Parent parent) {
        String query = "SELECT * FROM students WHERE parent_id = ?";
        List<Student> students = new ArrayList<>();
        long parentId = parent.getId();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, parentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        new Parent(parentId),
                        null
                );
                long groupId = resultSet.getLong("group_id");
                if (!resultSet.wasNull()) {
                    student.setGroup(new Group(groupId));
                }
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getByGroup(Group group) {
        String query = "SELECT * FROM students WHERE group_id = ?";
        List<Student> students = new ArrayList<>();
        long groupId = group.getId();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        null,
                        new Group(groupId)
                );
                long parentId = resultSet.getLong("parent_id");
                if (!resultSet.wasNull()) {
                    student.setParent(new Parent(parentId));
                }
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student updatedStudent) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ?, parent_id = ?, group_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedStudent.getFirstName());
            statement.setString(2, updatedStudent.getLastName());
            statement.setString(3, updatedStudent.getDateOfBirth());
            statement.setString(4, updatedStudent.getEmail());
            statement.setString(5, updatedStudent.getPhoneNumber());
            if (updatedStudent.getParent() != null) {
                statement.setLong(6, updatedStudent.getParent().getId());
            } else {
                statement.setNull(6, Types.NULL);
            }
            if (updatedStudent.getGroup() != null) {
                statement.setLong(7, updatedStudent.getGroup().getId());
            } else {
                statement.setNull(7, Types.NULL);
            }
            statement.setLong(8, updatedStudent.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long studentId) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        String query = "SELECT COUNT(*) AS count FROM students";
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
