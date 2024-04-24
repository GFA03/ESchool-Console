package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.Teacher;
import org.example.services.TeacherService;

import static org.example.utils.ReaderUtils.*;

public class TeacherView {
    private final TeacherService teacherService;

    public TeacherView(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    void run() {
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
        Long teacherId = readLong("Enter teacher ID:");
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
        Teacher teacher = new Teacher(1L, firstName, lastName, dateOfBirth, email, phoneNumber);
        teacherService.addTeacher(teacher);
    }

    private void deleteTeacherById() throws InvalidId {
        Long teacherId = readLong("Enter teacher ID:");
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher != null) {
            teacherService.deleteTeacher(teacherId);
            System.out.println("Teacher deleted successfully!");
        } else {
            System.out.println("Teacher ID incorrect!");
        }
    }
}
