package org.example.repositories;

import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassAttendanceRepository implements GenericRepository<ClassAttendance> {
    private final Connection connection;

    public ClassAttendanceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(ClassAttendance classAttendance) {
        String query = "INSERT INTO class_attendances (student_id, class_session_id, present, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, classAttendance.getStudent().getId());
            statement.setLong(2, classAttendance.getClassSession().getId());
            statement.setBoolean(3, classAttendance.isPresent());
            statement.setInt(4, classAttendance.getGrade());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createForGroup(List<Student> studentByGroup, ClassSession classSession) {
        for(Student student: studentByGroup)
            add(new ClassAttendance(1L, student, classSession));
    }

    @Override
    public List<ClassAttendance> getAll() {
        List<ClassAttendance> classAttendances = new ArrayList<>();
        String query = "SELECT * FROM class_attendances";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long studentId = resultSet.getLong("student_id");
                Long classSessionId = resultSet.getLong("class_session_id");
                boolean present = resultSet.getBoolean("present");
                Integer grade = resultSet.getInt("grade");
                Student student = new StudentRepository(connection).get(studentId);
                ClassSession classSession = new ClassSessionRepository(connection).get(classSessionId);
                classAttendances.add(new ClassAttendance(id, student, classSession, present, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classAttendances;
    }


    public List<ClassAttendance> getByStudent(Student student) {
        List<ClassAttendance> classAttendances = new ArrayList<>();
        String query = "SELECT * FROM class_attendances where student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, student.getId());
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long classSessionId = resultSet.getLong("class_session_id");
                boolean present = resultSet.getBoolean("present");
                Integer grade = resultSet.getInt("grade");
                ClassSession classSession = new ClassSessionRepository(connection).get(classSessionId);
                classAttendances.add(new ClassAttendance(id, student, classSession, present, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classAttendances;
    }

    @Override
    public ClassAttendance get(Long id) {
        String query = "SELECT * FROM class_attendances WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long studentId = resultSet.getLong("student_id");
                Long classSessionId = resultSet.getLong("class_session_id");
                boolean present = resultSet.getBoolean("present");
                Integer grade = resultSet.getInt("grade");
                Student student = new StudentRepository(connection).get(studentId);
                ClassSession classSession = new ClassSessionRepository(connection).get(classSessionId);
                return new ClassAttendance(id, student, classSession, present, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClassAttendance get(Student student, ClassSession classSession) {
        String query = "SELECT * FROM class_attendances WHERE student_id = ? and class_session_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, student.getId());
            statement.setLong(2, classSession.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                boolean present = resultSet.getBoolean("present");
                Integer grade = resultSet.getInt("grade");
                return new ClassAttendance(id, student, classSession, present, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(ClassAttendance classAttendance) {
        String query = "UPDATE class_attendances SET present = ?, grade = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, classAttendance.isPresent());
            statement.setInt(2, classAttendance.getGrade());
            statement.setLong(3, classAttendance.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM class_attendances WHERE id = ?";
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
        String query = "SELECT COUNT(*) AS count FROM class_attendances";
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
