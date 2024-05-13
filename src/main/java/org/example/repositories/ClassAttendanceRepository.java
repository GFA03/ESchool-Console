package org.example.repositories;

import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.models.Student;
import org.example.services.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassAttendanceRepository implements GenericRepository<ClassAttendance> {
    private final Connection connection;
    private final AuditService auditService;

    public ClassAttendanceRepository(Connection connection, AuditService auditService) {
        this.connection = connection;
        this.auditService = auditService;
    }

    @Override
    public void add(ClassAttendance classAttendance) {
        String query = "INSERT INTO class_attendances (student_id, class_session_id, present, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, classAttendance.getStudent().getId());
            statement.setLong(2, classAttendance.getClassSession().getId());
            statement.setBoolean(3, classAttendance.isPresent());
            if (classAttendance.getGrade() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, classAttendance.getGrade());
            }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                classAttendance.setId(resultSet.getLong(1));
            }
            auditService.logAdd("Class Attendance", classAttendance.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createForGroup(List<Student> studentByGroup, ClassSession classSession) {
        for(Student student: studentByGroup)
            add(new ClassAttendance(1L, student, classSession));
        auditService.logAdd("Class Attendance for Class Session", classSession.getId().toString());
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
                Student student = new StudentRepository(connection, auditService).get(studentId);
                ClassSession classSession = new ClassSessionRepository(connection, auditService).get(classSessionId);
                classAttendances.add(new ClassAttendance(id, student, classSession, present, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        auditService.logGet("All Class Attendances", null);
        return classAttendances;
    }


    public List<ClassAttendance> getByStudent(Student student) {
        List<ClassAttendance> classAttendances = new ArrayList<>();
        String query = "SELECT * FROM class_attendances where student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, student.getId());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    Long classSessionId = resultSet.getLong("class_session_id");
                    boolean present = resultSet.getBoolean("present");
                    Integer grade = resultSet.getInt("grade");
                    ClassSession classSession = new ClassSessionRepository(connection, auditService).get(classSessionId);
                    classAttendances.add(new ClassAttendance(id, student, classSession, present, grade));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        auditService.logGet("Class Attendances for Student", student.getId().toString());
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
                Student student = new StudentRepository(connection, auditService).get(studentId);
                ClassSession classSession = new ClassSessionRepository(connection, auditService).get(classSessionId);
                auditService.logGet("Class Attendance", id.toString());
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
            if (classAttendance.getGrade() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, classAttendance.getGrade());
            }
            statement.setLong(3, classAttendance.getId());
            statement.executeUpdate();
            auditService.logUpdate("Class Attendance", classAttendance.getId().toString());
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
            auditService.logDelete("Class Attendance", id.toString());
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
