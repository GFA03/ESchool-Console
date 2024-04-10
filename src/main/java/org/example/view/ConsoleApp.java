package org.example.view;

import org.example.exceptions.InvalidEmail;
import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.exceptions.InvalidRequest;
import org.example.models.*;
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

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
                int option = app.readOption();
                app.execute(option);
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option");
            }
        }
    }


    public void showMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Parents");
        System.out.println("3. Manage Teachers");
        System.out.println("4. Manage Class Sessions");
        System.out.println("5. Manage Class Attendance");
        System.out.println("6. Manage Courses");
        System.out.println("7. Manage Group Courses");
        System.out.println("8. Manage Groups");
        System.out.println("9. Exit");
    }

    private int readOption() throws InvalidOption {
        System.out.println("Enter your option: ");
        int option = readInt();
        if(option >= 1 && option <= 9)
            return option;
        throw new InvalidOption("Invalid option!");
    }


    private void execute(int option) {
        switch (option) {
            case 1:
                manageStudents();
                break;
            case 2:
                manageParents();
                break;
            case 3:
                manageTeachers();
                break;
            case 4:
                manageClassSessions();
                break;
            case 5:
                // Implement manageClassAttendance method
                break;
            case 6:
                manageCourses();
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

    private long readLong() throws InvalidId {
        String line = scanner.nextLine();
        if(line.matches("^\\d+$"))
            return Long.parseLong(line);
        throw new InvalidId("Invalid id!");
    }

    private void manageStudents() {
        while(true) {
            showStudentsMenu();
            try {
                int option = readOption();
                int status = executeStudentsOptions(option);
                if(status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            } catch (InvalidId e) {
                System.out.println("Invalid id!");
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

    private int executeStudentsOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                System.out.println(studentService.getAllStudents());
                break;
            case 2:
                showStudent();
                break;
            case 3:
                manageStudentById();
                break;
            case 4:
                addStudent();
                System.out.println("Student created successfully!");
                break;
            case 5:
                deleteStudent();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showStudent() throws InvalidId {
        System.out.println("Enter student id: ");
        Long studentId = readLong();
        if(studentService.getStudentById(studentId) != null)
            System.out.println(studentService.getStudentById(studentId));
        else
            System.out.println("Student doesn't exist!");
    }
    private void addStudent() throws InvalidId {
        String firstName = readName("Enter first name:");
        String lastName = readName("Enter last name:");
        String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
        String email = readEmail();
        String phoneNumber = readPhone();
        Parent parent = readParent();
        Group group = readGroup();
        studentService.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, parent, group);
    }

    private void deleteStudent() throws InvalidId {
        System.out.println("Enter student id:");
        Long studId = readLong();
        if(studentService.getStudentById(studId) != null) {
            studentService.deleteStudent(studId);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student id incorrect!");
        }
    }

    private String readName(String inputText) {
        System.out.println(inputText);
        return scanner.nextLine();
    }

    private String readDate(String inputText) {
        String dateOfBirth;
        while(true) {
            System.out.println(inputText);
            dateOfBirth = scanner.nextLine();
            try {
                LocalDate dob = LocalDate.parse(dateOfBirth);
                return dateOfBirth;
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please try again!");
            }
        }
    }

    private String readEmail() {
        String email;
        while(true) {
            System.out.println("Enter email:");
            email = scanner.nextLine().trim();
            if(Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")
                    .matcher(email).matches())
                return email;
            System.out.println("Invalid email! Please try again!");
        }
    }

    private String readPhone() {
        String phoneNumber;
        while(true) {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.nextLine().trim();
            if(Pattern.compile("\\d{10}").matcher(phoneNumber).matches())
                return phoneNumber;
            System.out.println("Invalid phone number! Please try again!");
        }
    }

    private Parent readParent() throws InvalidId {
        while (true) {
            System.out.println("Enter parent id(Or 0 for NULL):");
            Long parentId = readLong();
            if(parentId == 0L)
                return null;
            if(parentService.getParentById(parentId) != null)
                return parentService.getParentById(parentId);
            System.out.println("Invalid parent id! Please try again!");
        }
    }

    private Group readGroup() throws InvalidId {
        while (true) {
            System.out.println("Enter group id(Or 0 for NULL):");
            Long groupId = readLong();
            if(groupId == 0L)
                return null;
            if(groupService.getGroupById(groupId) != null)
                return groupService.getGroupById(groupId);
            System.out.println("Invalid group id! Please try again");
        }
    }

    private Group readGroupNotNull() throws InvalidId, InvalidRequest {
        if (groupService.getSize() == 0)
            throw new InvalidRequest("Invalid request! There are no groups!");
        while (true) {
            System.out.println("Enter group id:");
            Long groupId = readLong();
            if(groupService.getGroupById(groupId) != null)
                return groupService.getGroupById(groupId);
            System.out.println("Invalid group id! Please try again");
        }
    }

    private void manageStudentById() throws InvalidId {
        if(studentService.getSize() == 0) {
            System.out.println("No students yet!");
            return;
        }
        Student student = readStudentById();
        while (true) {
            assert student != null;
            showStudentByIdMenu(student);
            try {
                int option = readOption();
                int status = executeStudentByIdOptions(option, student);
                if(status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            }
            catch (InvalidEmail invalidEmail) {
                System.out.println("Invalid email!");
            }
        }
    }

    private void showPersonByIdMenu(Person person) {
        System.out.println(person.getFirstName() + " " + person.getLastName() + " menu:");
    }

    private void showStudentByIdMenu(Student student) {
        showPersonByIdMenu(student);
        System.out.println("1. Show grades");
        System.out.println("2. Show class attendance");
        System.out.println("3. Show teachers");
        System.out.println("4. Update student");
        System.out.println("9. Exit");
    }

    private Student readStudentById() throws InvalidId {
        while (true) {
            System.out.println("Enter student id(Or 0 for NULL):");
            Long studentId = readLong();
            if(studentService.getStudentById(studentId) != null)
                return studentService.getStudentById(studentId);
            System.out.println("Invalid student id! Please try again!");
        }
    }

    private int executeStudentByIdOptions(int option, Student student) throws InvalidOption, InvalidId, InvalidEmail {
        switch (option) {
            case 1:
                showStudentGrades(student);
                break;
            case 2:
                showStudentClassAttendance(student);
                break;
            case 3:
                showStudentTeachers(student);
                break;
            case 4:
                updateStudent(student);
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showStudentGrades(Student student) {
        System.out.println(classAttendanceService.getStudentGrades(student));
    }

    private void showStudentClassAttendance(Student student) {
        System.out.println(classAttendanceService.getStudentActivity(student));
    }

    private void showStudentTeachers(Student student) {
        System.out.println(groupCourseService.getStudentTeachers(student));
    }

    private void updateStudent(Student student) throws InvalidOption, InvalidId, InvalidEmail {
        while(true) {
            showUpdateStudentMenu();
            int option = readOption();
            int execute = executeStudentUpdateOption(option, student);
            if(execute == -1)
                break;
        }
    }


    private void showUpdatePersonMenu() {
        System.out.println("1. Edit first name");
        System.out.println("2. Edit last name");
        System.out.println("3. Edit date of birth");
        System.out.println("4. Edit email");
        System.out.println("5. Edit phone number");
    }

    private void showUpdateStudentMenu() {
        showUpdatePersonMenu();
        System.out.println("6. Edit parent");
        System.out.println("7. Edit group");
        System.out.println("9. Exit");
    }

    private int executeStudentUpdateOption(int option, Student student) throws InvalidId, InvalidEmail {
        switch (option) {
            case 1:
                System.out.println("Enter new first name:");
                String firstName = scanner.nextLine();
                studentService.updateStudentFirstName(student, firstName);
                break;
            case 2:
                System.out.println("Enter new last name:");
                String lastName = scanner.nextLine();
                studentService.updateStudentLastName(student, lastName);
                break;
            case 3:
                String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
                studentService.updateStudentDateOfBirth(student, dateOfBirth);
                break;
            case 4:
                String email = readEmail();
                studentService.updateStudentEmail(student, email);
                break;
            case 5:
                String phoneNumber = readPhone();
                studentService.updateStudentPhoneNumber(student, phoneNumber);
                break;
            case 6:
                System.out.println("Enter new parent id:");
                Long parentId = readLong();
                studentService.updateStudentParent(student, parentService.getParentById(parentId));
                break;
            case 7:
                System.out.println("Enter new group id:");
                Long groupId = readLong();
                studentService.updateStudentGroup(student, groupService.getGroupById(groupId));
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void manageParents() {
        while (true) {
            showParentsMenu();
            try {
                int option = readOption();
                int status = executeParentsOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            } catch (InvalidId e) {
                System.out.println("Invalid id!");
            }
        }
    }

    private void showParentsMenu() {
        System.out.println("Parents menu:");
        System.out.println("1. Show all parents");
        System.out.println("2. Show parent by ID");
        System.out.println("3. Enter parent ID");
        System.out.println("4. Create parent");
        System.out.println("5. Delete parent by ID");
        System.out.println("9. Exit");
    }

    private int executeParentsOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllParents();
                break;
            case 2:
                showParent();
                break;
            case 3:
                manageParentById();
                break;
            case 4:
                addParent();
                System.out.println("Parent created successfully!");
                break;
            case 5:
                deleteParent();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllParents() {
        System.out.println(parentService.getAllParents());
    }

    private void showParent() throws InvalidId {
        System.out.println("Enter parent id: ");
        Long parentId = readLong();
        if(parentService.getParentById(parentId) != null)
            System.out.println(parentService.getParentById(parentId));
        else
            System.out.println("Parent doesn't exist!");
    }

    private void addParent() {
        String firstName = readName("Enter first name");
        String lastName = readName("Enter last name");
        String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
        String email = readEmail();
        String phoneNumber = readPhone();
        parentService.createParent(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    private void deleteParent() throws InvalidId {
        System.out.println("Enter parent id:");
        Long parentId = readLong();
        if(parentService.getParentById(parentId) != null) {
            parentService.deleteParent(parentId);
            System.out.println("Parent deleted successfully!");
        } else {
            System.out.println("Parent id incorrect!");
        }
    }

    private void manageParentById() throws InvalidId {
        if(parentService.getSize() == 0) {
            System.out.println("No parents yet!");
            return;
        }
        Parent parent = readParent();
        while (true) {
            assert (parent != null);
            showParentByIdMenu(parent);
            try {
                int option = readOption();
                int status = executeParentByIdOptions(option, parent);
                if (status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            }
        }
    }

    private void showParentByIdMenu(Parent parent) {
        showPersonByIdMenu(parent);
        System.out.println("1. Show child(ren)");
        System.out.println("2. Update parent");
        System.out.println("9. Exit");
    }


    private int executeParentByIdOptions(int option, Parent parent) throws InvalidOption, InvalidId {
        switch (option) {
            case 1:
                parentChildrenOptions(parent);
                break;
            case 2:
                updateParent(parent);
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void parentChildrenOptions(Parent parent) throws InvalidId {
        List<Student> children = getChildren(parent);
        if (children.isEmpty())
            return;
        int status = 0;
        while(true) {
            showChildren(children);
            System.out.println("Please choose a student to show activity or 0 to leave");
            Long studentId = readLong();
            if (studentId == 0L)
                break;
            for (Student student: children) {
                if (student.getId().equals(studentId)) {
                    status = executeParentChildren(student);
                }
            }
            if (status == -1)
                break;
        }
    }

    private int executeParentChildren(Student student) throws InvalidId {
        while (true) {
            assert student != null;
            showStudentByIdMenu(student);
            try {
                int option = readOption();
                int status = executeStudentByIdOptions(option, student);
                if(status == -1)
                    return -1;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            }
            catch (InvalidEmail invalidEmail) {
                System.out.println("Invalid email!");
            }
        }
    }
    private List<Student> getChildren(Parent parent) {
        return studentService.getAfterParent(parent);
    }

    private void showChildren(List<Student> children) {
        System.out.println(children);
    }

    private void updateParent(Parent parent) throws InvalidOption {
        while(true) {
            showUpdateParentMenu();
            int option = readOption();
            int execute = executeParentUpdateOption(option, parent);
            if(execute == -1)
                break;
        }
    }

    private void showUpdateParentMenu() {
        showUpdatePersonMenu();
    }

    private int executeParentUpdateOption(int option, Parent parent) {
        switch (option) {
            case 1:
                System.out.println("Enter new first name:");
                String firstName = scanner.nextLine();
                parentService.updateParentFirstName(parent, firstName);
                break;
            case 2:
                System.out.println("Enter new last name:");
                String lastName = scanner.nextLine();
                parentService.updateParentLastName(parent, lastName);
                break;
            case 3:
                String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
                parentService.updateParentDateOfBirth(parent, dateOfBirth);
                break;
            case 4:
                String email = readEmail();
                parentService.updateParentEmail(parent, email);
                break;
            case 5:
                String phoneNumber = readPhone();
                parentService.updateParentPhoneNumber(parent, phoneNumber);
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }

        return 0;
    }

    private void manageTeachers() {
        while (true) {
            showTeachersMenu();
            try {
                int option = readOption();
                int status = executeTeachersOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            } catch (InvalidId invalidId) {
                System.out.println("Invalid id!");
            }
        }
    }

    private void showTeachersMenu() {
        System.out.println("Teachers menu:");
        System.out.println("1. Show all teachers");
        System.out.println("2. Show teacher by ID");
        System.out.println("3. Create teacher");
        System.out.println("4. Delete teacher by ID");
        System.out.println("9. Exit");
    }

    private int executeTeachersOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllTeachers();
                break;
            case 2:
                showTeacherById();
                break;
            case 3:
                createTeacher();
                System.out.println("Teacher created successfully!");
                break;
            case 4:
                deleteTeacherById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllTeachers() {
        System.out.println(teacherService.getAllTeachers());
    }

    private void showTeacherById() throws InvalidId {
        System.out.println("Enter teacher ID: ");
        Long teacherId = readLong();
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher != null)
            System.out.println(teacher);
        else
            System.out.println("Teacher doesn't exist!");
    }

    private void createTeacher() {
        System.out.println("Enter teacher details:");
        String firstName = readName("Enter first name");
        String lastName = readName("Enter last name");
        String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
        String email = readEmail();
        String phoneNumber = readPhone();
        teacherService.createTeacher(firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    private void deleteTeacherById() throws InvalidId {
        System.out.println("Enter teacher ID:");
        Long teacherId = readLong();
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher != null) {
            teacherService.deleteTeacher(teacherId);
            System.out.println("Teacher deleted successfully!");
        } else {
            System.out.println("Teacher ID incorrect!");
        }
    }

    private void manageCourses() {
        while (true) {
            showCoursesMenu();
            try {
                int option = readOption();
                int status = executeCoursesOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption invalidOption) {
                System.out.println("Invalid option!");
            } catch (InvalidId invalidId) {
                System.out.println("Invalid id!");
            }
        }
    }

    private void showCoursesMenu() {
        System.out.println("Courses menu:");
        System.out.println("1. Show all courses");
        System.out.println("2. Show course by ID");
        System.out.println("3. Create course");
        System.out.println("4. Delete course by ID");
        System.out.println("9. Exit");
    }

    private int executeCoursesOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllCourses();
                break;
            case 2:
                showCourseById();
                break;
            case 3:
                createCourse();
                System.out.println("Course created successfully!");
                break;
            case 4:
                deleteCourseById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllCourses() {
        System.out.println(courseService.getAllCourses());
    }

    private void showCourseById() throws InvalidId {
        System.out.println("Enter course ID: ");
        Long courseId = readLong();
        Course course = courseService.getCourseById(courseId);
        if (course != null)
            System.out.println(course);
        else
            System.out.println("Course doesn't exist!");
    }

    private void createCourse() {
        System.out.println("Enter course name: ");
        String courseName = scanner.nextLine();
        courseService.createCourse(courseName);
    }

    private void deleteCourseById() throws InvalidId {
        System.out.println("Enter course ID:");
        Long courseId = readLong();
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            courseService.deleteCourse(courseId);
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Course ID incorrect!");
        }
    }

    private void manageClassSessions() {
        while (true) {
            showClassSessionsMenu();
            try {
                int option = readOption();
                int status = executeClassSessionsOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidRequest | InvalidId e) {
                System.out.println(e.toString());
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
                createClassSession();
                System.out.println("Class session created successfully!");
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
        System.out.println("Enter class session ID: ");
        Long classSessionId = readLong();
        ClassSession classSession = classSessionService.getClassSessionById(classSessionId);
        if (classSession != null)
            System.out.println(classSession);
        else
            System.out.println("Class session doesn't exist!");
    }

    private void createClassSession() throws InvalidId, InvalidRequest {
        System.out.println("Enter class session details:");
        String name = readName("Enter class session name:");
        Course course = readCourseNotNull();
        Group group = readGroupNotNull();
        String sessionDate = readDate("Enter session date (YYYY-MM-DD): ");
        classSessionService.createClassSession(name, course, group, sessionDate);
    }

    private Course readCourse() throws InvalidId {
        while (true) {
            System.out.println("Enter course id(Or 0 for NULL):");
            Long courseId = readLong();
            if(courseId == 0L)
                return null;
            if(courseService.getCourseById(courseId) != null)
                return courseService.getCourseById(courseId);
            System.out.println("Invalid course id! Please try again");
        }
    }

    private Course readCourseNotNull() throws InvalidId, InvalidRequest {
        if (courseService.getSize() == 0)
            throw new InvalidRequest("Invalid request! There are no courses!");
        while (true) {
            System.out.println("Enter course id:");
            Long courseId = readLong();
            if(courseService.getCourseById(courseId) != null)
                return courseService.getCourseById(courseId);
            System.out.println("Invalid course id! Please try again");
        }
    }

    private void deleteClassSessionById() throws InvalidId {
        System.out.println("Enter class session ID:");
        Long classSessionId = readLong();
        ClassSession classSession = classSessionService.getClassSessionById(classSessionId);
        if (classSession != null) {
            classSessionService.deleteClassSession(classSessionId);
            System.out.println("Class session deleted successfully!");
        } else {
            System.out.println("Class session ID incorrect!");
        }
    }

}

