package org.example.view;

import org.example.exceptions.InvalidEmail;
import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.Group;
import org.example.models.Parent;
import org.example.models.Person;
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
        System.out.println("4. Manage Class Attendance");
        System.out.println("5. Manage Class Sessions");
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
        String firstName = readFirstName();
        String lastName = readLastName();
        String dateOfBirth = readDateOfBirth();
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

    private String readFirstName() {
        System.out.println("Enter first name:");
        return scanner.nextLine();
    }

    private String readLastName() {
        System.out.println("Enter last name:");
        return scanner.nextLine();
    }

    private String readDateOfBirth() {
        String dateOfBirth;
        while(true) {
            System.out.println("Enter date of birth(Format like: 2000-12-30):");
            dateOfBirth = scanner.nextLine();
            if(Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-\\d{2}").matcher(dateOfBirth).matches())
                return dateOfBirth;
            System.out.println("Invalid date! Please try again!");
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
                String dateOfBirth = readDateOfBirth();
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
                System.out.println(parentService.getAllParents());
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

    private void showParent() throws InvalidId {
        System.out.println("Enter parent id: ");
        Long parentId = readLong();
        if(parentService.getParentById(parentId) != null)
            System.out.println(parentService.getParentById(parentId));
        else
            System.out.println("Parent doesn't exist!");
    }

    private void addParent() {
        String firstName = readFirstName();
        String lastName = readLastName();
        String dateOfBirth = readDateOfBirth();
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
                String dateOfBirth = readDateOfBirth();
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

}

