package org.example.view;

import org.example.exceptions.InvalidOption;
import org.example.models.Student;
import org.example.repositories.StudentRepository;
import org.example.services.StudentService;
import org.example.repositories.ParentRepository;
import org.example.services.ParentService;
import org.example.repositories.TeacherRepository;
import org.example.services.TeacherService;
import org.example.repositories.ClassAttendanceRepository;
import org.example.services.ClassAttendanceService;
import org.example.repositories.ClassSessionRepository;
import org.example.services.ClassSessionService;
import org.example.repositories.CourseRepository;
import org.example.services.CourseService;
import org.example.repositories.GroupCourseRepository;
import org.example.services.GroupCourseService;
import org.example.repositories.GroupRepository;
import org.example.services.GroupService;

import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentRepository studentRepository = new StudentRepository();
    private final StudentService studentService = new StudentService(studentRepository);
    private final ParentRepository parentRepository = new ParentRepository();
    private final ParentService parentService = new ParentService(parentRepository);
    private final TeacherRepository teacherRepository = new TeacherRepository();
    private final TeacherService teacherService = new TeacherService(teacherRepository);
    private final ClassAttendanceRepository classAttendanceRepository = new ClassAttendanceRepository();
    private final ClassAttendanceService classAttendanceService = new ClassAttendanceService(classAttendanceRepository);
    private final ClassSessionRepository classSessionRepository = new ClassSessionRepository();
    private final ClassSessionService classSessionService = new ClassSessionService(classSessionRepository);
    private final CourseRepository courseRepository = new CourseRepository();
    private final CourseService courseService = new CourseService(courseRepository);
    private final GroupCourseRepository groupCourseRepository = new GroupCourseRepository();
    private final GroupCourseService groupCourseService = new GroupCourseService(groupCourseRepository);
    private final GroupRepository groupRepository = new GroupRepository();
    private final GroupService groupService = new GroupService(groupRepository);

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        while(true) {
            app.showMenu();
            try {
                int option = app.readOption(1, 9);
                app.execute(option);
            } catch (InvalidOption invalidOption) {
                System.out.println(invalidOption);
            }
        }
    }


    public void showMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Parents");
        System.out.println("3. Manage Teachers");
        System.out.println("4. Manage Class Attendance");
        System.out.println("5. Manage Class Sessions");
        System.out.println("6. Manage Courses");
        System.out.println("7. Manage Group Courses");
        System.out.println("8. Manage Groups");
        System.out.println("9. Exit");
    }

    private int readOption(int low, int high) throws InvalidOption {
        System.out.println("Enter your option: ");
        int option = readInt();
        if(option >= low && option <= high)
            return option;
        throw new InvalidOption("Invalid option!");
    }


    private void execute(int option) {
        switch (option) {
            case 1:
                manageStudents();
                break;
            case 2:
                // Implement manageParents method
                break;
            case 3:
                // Implement manageTeachers method
                break;
            case 4:
                // Implement manageClassAttendance method
                break;
            case 5:
                // Implement manageClassSessions method
                break;
            case 6:
                // Implement manageCourses method
                break;
            case 7:
                // Implement manageGroupCourses method
                break;
            case 8:
                // Implement manageGroups method
                break;
            case 9:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private int readInt() throws InvalidOption {
        String line = scanner.nextLine();
        if(line.matches("^\\d+$"))
            return Integer.parseInt(line);
        throw new InvalidOption("Invalid option!");
    }

    private long readLong() throws InvalidOption {
        String line = scanner.nextLine();
        if(line.matches("^\\d+$"))
            return Long.parseLong(line);
        throw new InvalidOption("Invalid option!");
    }

    private void manageStudents() {
        while(true) {
            showStudentsMenu();
            try {
                int option = readOption(1, 9);
                int status = executeStudentsOptions(option);
                if(status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invaid option!");
            }
        }
    }


    private void showStudentsMenu() {
        System.out.println("Students menu:");
        System.out.println("1. Show all students");
        System.out.println("2. Show student by ID");
        System.out.println("3. Enter student ID");
        System.out.println("4. Create student");
        System.out.println("5. Delete student by ID");
        System.out.println("9. Exit");
    }

    private int executeStudentsOptions(int option) throws InvalidOption {
        switch (option) {
            case 1:
                System.out.println(studentService.getAllStudents());
                break;
            case 2:
                System.out.println("Enter student id: ");
                Long studentId = readLong();
                if(studentService.getStudentById(studentId) != null)
                    System.out.println(studentService.getStudentById(studentId));
                else
                    System.out.println("Student doesn't exist!");
                break;
            case 3:
                // Implement enter student id method
                break;
            case 4:
                System.out.println("Enter first name:");
                String firstName = scanner.nextLine();
                System.out.println("Enter last name:");
                String lastName = scanner.nextLine();
                System.out.println("Enter date of birth(Format like: 2000-12-30):");
                String dateOfBirth = scanner.nextLine();
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter phone number:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Enter parent id(Or 0 for NULL):");
                Long parentId = readLong();
                System.out.println("Enter group id(Or 0 for NULL):");
                Long groupId = readLong();
                studentService.addStudent(new Student(firstName, lastName, dateOfBirth, email, phoneNumber, parentService.getParentById(parentId), groupService.getGroupById(groupId)));
                System.out.println("Student created successfully!");
                break;
            case 5:
                System.out.println("Enter student id:");
                Long studId = readLong();
                if(studentService.getStudentById(studId) != null) {
                    studentService.deleteStudent(studId);
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Student id incorrect!");
                }
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showStudentMenu(Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName() + " menu:");
        System.out.println("1. Show grades");
        System.out.println("2. Show class attendance");
        System.out.println("3. Show teachers");
    }
}

