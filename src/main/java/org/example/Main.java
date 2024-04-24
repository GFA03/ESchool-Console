package org.example;

import org.example.repositories.*;
import org.example.services.*;
import org.example.view.ConsoleApp;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            Connection connection = databaseManager.getConnection();

            ParentRepository parentRepository = new ParentRepository(connection);
            ParentService parentService = new ParentService(parentRepository);

            TeacherRepository teacherRepository = new TeacherRepository();
            TeacherService teacherService = new TeacherService(teacherRepository);

            ClassAttendanceRepository classAttendanceRepository = new ClassAttendanceRepository();
            ClassAttendanceService classAttendanceService = new ClassAttendanceService(classAttendanceRepository);

            StudentRepository studentRepository = new StudentRepository();
            StudentService studentService = new StudentService(studentRepository, classAttendanceService);

            ClassSessionRepository classSessionRepository = new ClassSessionRepository();
            ClassSessionService classSessionService = new ClassSessionService(classSessionRepository);

            CourseRepository courseRepository = new CourseRepository();
            CourseService courseService = new CourseService(courseRepository);

            GroupCourseRepository groupCourseRepository = new GroupCourseRepository();
            GroupCourseService groupCourseService = new GroupCourseService(groupCourseRepository);

            GroupRepository groupRepository = new GroupRepository();
            GroupService groupService = new GroupService(groupRepository);

            ConsoleApp consoleApp = new ConsoleApp(studentService, parentService, teacherService, classAttendanceService, classSessionService, courseService
                    , groupCourseService, groupService);
            consoleApp.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}