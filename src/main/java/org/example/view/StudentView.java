package org.example.view;

import org.example.exceptions.InvalidEmail;
import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.exceptions.InvalidRequest;
import org.example.models.Group;
import org.example.models.Parent;
import org.example.models.Person;
import org.example.models.Student;
import org.example.services.*;

import static org.example.utils.ReaderUtils.*;

public class StudentView {
    private final StudentService studentService;
    private final GroupService groupService;
    private final ParentService parentService;
    private final GroupCourseService groupCourseService;
    private final ClassAttendanceService classAttendanceService;

    public StudentView (StudentService studentService, GroupService groupService, ParentService parentService, GroupCourseService groupCourseService, ClassAttendanceService classAttendanceService) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.parentService = parentService;
        this.groupCourseService = groupCourseService;
        this.classAttendanceService = classAttendanceService;
    }

    public void run () {
        while(true) {
            showStudentsMenu();
            try {
                int option = readOption();
                int status = executeStudentsOptions(option);
                if(status == -1)
                    break;
            } catch (InvalidOption | InvalidId | InvalidRequest e) {
                System.out.println(e.toString());
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

    private int executeStudentsOptions(int option) throws InvalidId, InvalidRequest {
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
        Long studentId = readLong("Enter student id:");
        if(studentService.getStudentById(studentId) != null)
            System.out.println(studentService.getStudentById(studentId));
        else
            System.out.println("Student doesn't exist!");
    }
    private void addStudent() throws InvalidId, InvalidRequest {
        String firstName = readName("Enter first name:");
        String lastName = readName("Enter last name:");
        String dateOfBirth = readDate("Enter date of birth(Format like: 2000-12-30):");
        String email = readEmail();
        String phoneNumber = readPhone();
        Parent parent = readParent();
        Group group = getGroup();
        Student student = new Student(firstName, lastName, dateOfBirth, email, phoneNumber, parent, group);
        studentService.add(student);
    }

    private Parent readParent() throws InvalidId, InvalidRequest {
        if (parentService.getSize() == 0)
            throw new InvalidRequest("Invalid request! There are no parent!");
        while (true) {
            Long parentId = readLong("Enter parent id:");
            if(parentService.getParentById(parentId) != null)
                return parentService.getParentById(parentId);
            System.out.println("Invalid parent id! Please try again!");
        }
    }

    private void deleteStudent() throws InvalidId {
        Long studId = readLong("Enter student id:");
        if(studentService.getStudentById(studId) != null) {
            studentService.deleteStudent(studId);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student id incorrect!");
        }
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

    private void manageStudentById() throws InvalidId {
        if(studentService.getSize() == 0) {
            System.out.println("No students yet!");
            return;
        }
        Student student = getStudent();
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

    private Student getStudent() throws InvalidId {
        while (true) {
            Long studentId = readLong("Enter student id:");
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

    private void updateStudent(Student student) throws InvalidOption, InvalidId {
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

    private int executeStudentUpdateOption(int option, Student student) throws InvalidId {
        switch (option) {
            case 1:
                String firstName = readName("Enter new first name:");
                studentService.updateStudentFirstName(student, firstName);
                break;
            case 2:
                String lastName = readName("Enter new last name:");
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
                Long parentId = readLong("Enter new parent id:");
                studentService.updateStudentParent(student, parentService.getParentById(parentId));
                break;
            case 7:
                Long groupId = readLong("Enter new group id:");
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
}
