package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.Course;
import org.example.services.CourseService;

import static org.example.utils.ReaderUtils.readLong;
import static org.example.utils.ReaderUtils.readName;
import static org.example.utils.ReaderUtils.readOption;

public class CourseView {
    private final CourseService courseService;

    public CourseView(CourseService courseService) {
        this.courseService = courseService;
    }

    void run () {
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
        Long courseId = readLong("Enter course ID: ");
        Course course = courseService.getCourseById(courseId);
        if (course != null)
            System.out.println(course);
        else
            System.out.println("Course doesn't exist!");
    }

    private void createCourse() {
        String courseName = readName("Enter course name: ");
        courseService.createCourse(courseName);
    }

    private void deleteCourseById() throws InvalidId {
        Long courseId = readLong("Enter course ID:");
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            courseService.deleteCourse(courseId);
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Course ID incorrect!");
        }
    }
}
