package org.example.view;

import org.example.exceptions.InvalidOption;
import org.example.models.*;
import org.example.services.StudentService;
import org.example.services.ParentService;
import org.example.services.TeacherService;
import org.example.services.ClassAttendanceService;
import org.example.services.ClassSessionService;
import org.example.services.CourseService;
import org.example.services.GroupCourseService;
import org.example.services.GroupService;

import static org.example.utils.ReaderUtils.readOption;

public class ConsoleApp {
    private final StudentService studentService;
    private final ParentService parentService;
    private final TeacherService teacherService;
    private final ClassAttendanceService classAttendanceService;
    private final ClassSessionService classSessionService;
    private final CourseService courseService;
    private final GroupCourseService groupCourseService;
    private final GroupService groupService;

    public ConsoleApp(StudentService studentService, ParentService parentService, TeacherService teacherService, ClassAttendanceService classAttendanceService,
                      ClassSessionService classSessionService, CourseService courseService, GroupCourseService groupCourseService, GroupService groupService) {
        this.studentService = studentService;
        this.parentService = parentService;
        this.teacherService = teacherService;
        this.classAttendanceService = classAttendanceService;
        this.classSessionService = classSessionService;
        this.courseService = courseService;
        this.groupCourseService = groupCourseService;
        this.groupService = groupService;
    }

    public void run() {
        this.populateTables();
        while(true) {
            this.showMenu();
            try {
                int option = readOption();
                this.execute(option);
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
                manageClassAttendances();
                break;
            case 6:
                manageCourses();
                break;
            case 7:
                manageGroupCourses();
                break;
            case 8:
                manageGroups();
                break;
            case 9:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void manageStudents() {
        StudentView view = new StudentView(studentService, groupService, parentService, groupCourseService, classAttendanceService);
        view.run();
    }

//TODO: Make each view Singleton
    private void manageParents() {
        ParentView view = new ParentView(parentService, studentService);
        view.run();
    }


    private void manageTeachers() {
        TeacherView view = new TeacherView(teacherService);
        view.run();
    }


    private void manageCourses() {
        CourseView view = new CourseView(courseService);
        view.run();
    }

    private void manageClassSessions() {
        ClassSessionView view = new ClassSessionView(classSessionService, courseService, studentService, classAttendanceService, groupService);
        view.run();
    }

    private void manageClassAttendances() {
        ClassAttendanceView view = new ClassAttendanceView(classAttendanceService, classSessionService);
        view.run();
    }

    private void manageGroups() {
        GroupView view = new GroupView(groupService);
        view.run();
    }


    private void manageGroupCourses() {
        GroupCourseView view = new GroupCourseView(groupCourseService, teacherService, courseService, groupService);
        view.run();
    }

    private void populateTables() {
        populateGroups();
        populateCourses();
        populateTeachers();
        populateGroupCourses();
        populateParentsAndStudents();
    }


    private void populateGroups() {
        if (groupService.getSize() != 0) {
            return;
        }
        Group group = new Group(1L, "XII A");
        Group group2 = new Group(1L, "XII B");
        Group group3 = new Group(1L, "XII C");
        Group group4 = new Group(1L,"XII D");
        groupService.addGroup(group);
        groupService.addGroup(group2);
        groupService.addGroup(group3);
        groupService.addGroup(group4);
    }
//TODO: If there is none in getSize and you print all, print "There is none"
    private void populateCourses() {
        if (courseService.getSize() != 0) {
            return;
        }
        Course course1 = new Course(1L, "Mathematics");
        Course course2 = new Course(2L,"Physics");
        Course course3 = new Course(3L, "Chemistry");
        Course course4 = new Course(4L, "Biology");
        courseService.addCourse(course1);
        courseService.addCourse(course2);
        courseService.addCourse(course3);
        courseService.addCourse(course4);
    }

    private void populateTeachers() {
        if (teacherService.getSize() != 0) {
            return;
        }
        Teacher teacher1 = new Teacher(1L, "John", "Doe", "1990-05-15", "john.doe@example.com", "0712345678");
        Teacher teacher2 = new Teacher(2L, "Jane", "Smith", "1985-10-20", "jane.smith@example.com", "0762325678");
        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);
    }

    private void populateGroupCourses() {
        if (groupCourseService.getSize() != 0) {
            return;
        }
        GroupCourse groupCourse1 = new GroupCourse(1L, groupService.getGroupById(1L), courseService.getCourseById(1L), teacherService.getTeacherById(1L));
        GroupCourse groupCourse2 = new GroupCourse(2L, groupService.getGroupById(2L), courseService.getCourseById(2L), teacherService.getTeacherById(1L));
        GroupCourse groupCourse3 = new GroupCourse(3L, groupService.getGroupById(3L), courseService.getCourseById(3L), teacherService.getTeacherById(2L));
        GroupCourse groupCourse4 = new GroupCourse(4L, groupService.getGroupById(4L), courseService.getCourseById(4L), teacherService.getTeacherById(2L));
        groupCourseService.addGroupCourse(groupCourse1);
        groupCourseService.addGroupCourse(groupCourse2);
        groupCourseService.addGroupCourse(groupCourse3);
        groupCourseService.addGroupCourse(groupCourse4);
    }

    private void populateParentsAndStudents() {
        if (parentService.getSize() != 0) {
            return;
        }
        Parent parent1 = new Parent(1L, "Parent1", "Lastname1", "1970-01-01", "parent1@example.com", "111111111");
        Parent parent2 = new Parent(2L, "Parent2", "Lastname2", "1975-02-02", "parent2@example.com", "222222222");
        Parent parent3 = new Parent(3L, "Parent3", "Lastname3", "1980-03-03", "parent3@example.com", "333333333");
        parentService.addParent(parent1);
        parentService.addParent(parent2);
        parentService.addParent(parent3);
        Student student1 = new Student(1L, "John", "Doe", "2005-04-04", "john.doe@student.com", "444444444", parent1, groupService.getGroupById(1L));
        Student student2 = new Student(2L, "Jane", "Smith", "2006-05-05", "jane.smith@student.com", "555555555", parent2, groupService.getGroupById(1L));
        Student student3 = new Student(3L, "Alice", "Johnson", "2007-06-06", "alice.johnson@student.com", "666666666", parent3, groupService.getGroupById(2L));
        studentService.add(student1);
        studentService.add(student2);
        studentService.add(student3);
    }

    public static void showUpdatePersonMenu() {
        System.out.println("1. Edit first name");
        System.out.println("2. Edit last name");
        System.out.println("3. Edit date of birth");
        System.out.println("4. Edit email");
        System.out.println("5. Edit phone number");
    }

    public static void showUpdateStudentMenu() {
        showUpdatePersonMenu();
        System.out.println("6. Edit parent");
        System.out.println("7. Edit group");
        System.out.println("9. Exit");
    }

    public static void showPersonByIdMenu(Person person) {
        System.out.println(person.getFirstName() + " " + person.getLastName() + " menu:");
    }
}