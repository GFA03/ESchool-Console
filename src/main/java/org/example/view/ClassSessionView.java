package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.exceptions.InvalidRequest;
import org.example.models.ClassSession;
import org.example.models.Course;
import org.example.models.Group;
import org.example.models.Student;
import org.example.services.*;

import java.util.List;

import static org.example.utils.ReaderUtils.readLong;
import static org.example.utils.ReaderUtils.readName;
import static org.example.utils.ReaderUtils.readDate;
import static org.example.utils.ReaderUtils.readOption;

public class ClassSessionView {
    private final ClassSessionService classSessionService;
    private final CourseService courseService;
    private final ClassAttendanceService classAttendanceService;
    private final StudentService studentService;
    private final GroupService groupService;

    public ClassSessionView(ClassSessionService classSessionService, CourseService courseService, StudentService studentService, ClassAttendanceService classAttendanceService, GroupService groupService) {
        this.classSessionService = classSessionService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.classAttendanceService = classAttendanceService;
        this.groupService = groupService;
    }

    public void run () {
        while (true) {
            showClassSessionsMenu();
            try {
                int option = readOption();
                int status = executeClassSessionsOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidRequest | InvalidId e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void showClassSessionsMenu() {
        System.out.println("Class sessions menu:");
        System.out.println("1. Show all class sessions");
        System.out.println("2. Show class session by ID");
        System.out.println("3. Create class session");
        System.out.println("4. Delete class session by ID");
        System.out.println("9. Exit");
    }

    private int executeClassSessionsOptions(int option) throws InvalidId, InvalidRequest {
        switch (option) {
            case 1:
                showAllClassSessions();
                break;
            case 2:
                showClassSessionById();
                break;
            case 3:
                ClassSession classSession = createClassSession();
                System.out.println("Class session created successfully!");
                createClassAttendance(classSession);
                System.out.println("Class attendance for class session initialised successfully!");
                break;
            case 4:
                deleteClassSessionById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllClassSessions() {
        System.out.println(classSessionService.getAllClassSessions());
    }

    private void showClassSessionById() throws InvalidId {
        Long classSessionId = readLong("Enter class session ID: ");
        ClassSession classSession = classSessionService.getClassSessionById(classSessionId);
        if (classSession != null)
            System.out.println(classSession);
        else
            System.out.println("Class session doesn't exist!");
    }

    private ClassSession createClassSession() throws InvalidId, InvalidRequest {
        String name = readName("Enter class session name:");
        Course course = getCourse();
        Group group = getGroup();
        String sessionDate = readDate("Enter session date (YYYY-MM-DD): ");
        ClassSession classSession = new ClassSession(1L, name, course, group, sessionDate);
        classSessionService.addClassSession(classSession);
        return classSession;
    }

    private Group getGroup() throws InvalidId, InvalidRequest {
        if (groupService.getSize() == 0)
            throw new InvalidRequest("Invalid request! There are no groups!");
        while (true) {
            Long groupId = readLong("Enter group id:");
            if(groupService.getGroupById(groupId) != null)
                return groupService.getGroupById(groupId);
            System.out.println("Invalid group id! Please try again");
        }
    }


    private void createClassAttendance(ClassSession classSession) {
        List<Student> studentsByGroup = studentService.getAfterGroup(classSession.getGroup());
        classAttendanceService.createForGroup(studentsByGroup, classSession);
    }

    private Course getCourse() throws InvalidId, InvalidRequest {
        if (courseService.getSize() == 0)
            throw new InvalidRequest("Invalid request! There are no courses!");
        while (true) {
            Long courseId = readLong("Enter course id:");
            if(courseService.getCourseById(courseId) != null)
                return courseService.getCourseById(courseId);
            System.out.println("Invalid course id! Please try again");
        }
    }

    private void deleteClassSessionById() throws InvalidId {
        Long classSessionId = readLong("Enter class session ID:");
        ClassSession classSession = classSessionService.getClassSessionById(classSessionId);
        if (classSession != null) {
            classSessionService.deleteClassSession(classSessionId);
            System.out.println("Class session deleted successfully!");
        } else {
            System.out.println("Class session ID incorrect!");
        }
    }

}
