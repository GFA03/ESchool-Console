package org.example;

import org.example.repositories.*;
import org.example.services.*;
import org.example.view.ConsoleApp;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        AuditService auditService = AuditService.getInstance("/home/gfa/Desktop/FMI-Unibuc-Work/An2/Semestrul2/PAO/Project/ESchool-System/audit.csv");
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        try {
            Connection connection = databaseManager.getConnection();

            ParentRepository parentRepository = new ParentRepository(connection, auditService);
            ParentService parentService = new ParentService(parentRepository);

            TeacherRepository teacherRepository = new TeacherRepository(connection, auditService);
            TeacherService teacherService = new TeacherService(teacherRepository);

            ClassAttendanceRepository classAttendanceRepository = new ClassAttendanceRepository(connection, auditService);
            ClassAttendanceService classAttendanceService = new ClassAttendanceService(classAttendanceRepository);

            StudentRepository studentRepository = new StudentRepository(connection, auditService);
            StudentService studentService = new StudentService(studentRepository, classAttendanceService);

            ClassSessionRepository classSessionRepository = new ClassSessionRepository(connection, auditService);
            ClassSessionService classSessionService = new ClassSessionService(classSessionRepository);

            CourseRepository courseRepository = new CourseRepository(connection, auditService);
            CourseService courseService = new CourseService(courseRepository);

            GroupCourseRepository groupCourseRepository = new GroupCourseRepository(connection, auditService);
            GroupCourseService groupCourseService = new GroupCourseService(groupCourseRepository);

            GroupRepository groupRepository = new GroupRepository(connection, auditService);
            GroupService groupService = new GroupService(groupRepository);

            ConsoleApp consoleApp = new ConsoleApp(studentService, parentService, teacherService, classAttendanceService, classSessionService, courseService
                    , groupCourseService, groupService);
            consoleApp.run();
            databaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}