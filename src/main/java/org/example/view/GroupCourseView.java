package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.Course;
import org.example.models.Group;
import org.example.models.GroupCourse;
import org.example.models.Teacher;
import org.example.services.CourseService;
import org.example.services.GroupCourseService;
import org.example.services.GroupService;
import org.example.services.TeacherService;

import static org.example.utils.ReaderUtils.readLong;
import static org.example.utils.ReaderUtils.readOption;

public class GroupCourseView {
    private final GroupCourseService groupCourseService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final GroupService groupService;

    public GroupCourseView(GroupCourseService groupCourseService, TeacherService teacherService, CourseService courseService, GroupService groupService) {
        this.groupCourseService = groupCourseService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    public void run () {
        while (true) {
            showGroupCoursesMenu();
            try {
                int option = readOption();
                int status = executeGroupCoursesOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidId e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void showGroupCoursesMenu() {
        System.out.println("Group Courses menu:");
        System.out.println("1. Show all group courses");
        System.out.println("2. Show group course by ID");
        System.out.println("3. Create group course");
        System.out.println("4. Update group course's teacher");
        System.out.println("5. Delete group course by ID");
        System.out.println("9. Exit");
    }

    private int executeGroupCoursesOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllGroupCourses();
                break;
            case 2:
                showGroupCourseById();
                break;
            case 3:
                createGroupCourse();
                System.out.println("Group course created successfully!");
                break;
            case 4:
                updateGroupCourseTeacher();
                break;
            case 5:
                deleteGroupCourseById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllGroupCourses() {
        if (groupCourseService.getAllGroupCourses().isEmpty()) {
            System.out.println("No group courses found.");
            return;
        }
        System.out.println(groupCourseService.getAllGroupCourses());
    }

    private void showGroupCourseById() throws InvalidId {
        Long groupCourseId = readLong("Enter group course ID:");
        GroupCourse groupCourse = groupCourseService.getGroupCourse(groupCourseId);
        if (groupCourse != null)
            System.out.println(groupCourse);
        else
            System.out.println("Group course doesn't exist!");
    }

    private void createGroupCourse() throws InvalidId {
        Long groupId = readLong("Enter group ID:");
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            System.out.println("Group ID incorrect!");
            return;
        }

        Long courseId = readLong("Enter course ID:");
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("Course ID incorrect!");
            return;
        }

        Long teacherId = readLong("Enter teacher ID:");
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher == null) {
            System.out.println("Teacher ID incorrect!");
            return;
        }
        GroupCourse groupCourse = new GroupCourse(1L, group, course, teacher);
        groupCourseService.addGroupCourse(groupCourse);
    }

    private void updateGroupCourseTeacher() throws InvalidId {
        Long groupCourseId = readLong("Enter group course ID:");
        GroupCourse groupCourse = groupCourseService.getGroupCourse(groupCourseId);
        if (groupCourse == null) {
            System.out.println("Group course doesn't exist!");
            return;
        }
        Long newTeacherId = readLong("Enter new teacher's ID:");
        Teacher newTeacher = teacherService.getTeacherById(newTeacherId);
        if (newTeacher == null) {
            System.out.println("Teacher ID incorrect!");
            return;
        }
        groupCourseService.updateTeacher(groupCourse, newTeacher);
    }

    private void deleteGroupCourseById() throws InvalidId {
        Long groupCourseId = readLong("Enter group course ID:");
        GroupCourse groupCourse = groupCourseService.getGroupCourse(groupCourseId);
        if (groupCourse != null) {
            groupCourseService.deleteGroupCourse(groupCourseId);
            System.out.println("Group course deleted successfully!");
        } else {
            System.out.println("Group course ID incorrect!");
        }
    }
}
