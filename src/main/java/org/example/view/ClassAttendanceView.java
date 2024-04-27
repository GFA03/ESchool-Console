package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.ClassAttendance;
import org.example.models.ClassSession;
import org.example.services.ClassAttendanceService;
import org.example.services.ClassSessionService;

import java.util.Objects;

import static org.example.utils.ReaderUtils.*;

public class ClassAttendanceView {
    private final ClassAttendanceService classAttendanceService;
    private final ClassSessionService classSessionService;

    public ClassAttendanceView(ClassAttendanceService classAttendanceService, ClassSessionService classSessionService) {
        this.classAttendanceService = classAttendanceService;
        this.classSessionService = classSessionService;
    }

    public void run() {
        while (true) {
            showClassAttendancesMenu();
            try {
                int option = readOption();
                int status = executeClassAttendancesOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidId e) {
                System.out.println(e.toString());
            }
        }
    }


    private void showClassAttendancesMenu() {
        System.out.println("Class attendances menu:");
        System.out.println("1. Show all class attendances");
        System.out.println("2. Show class attendance by ID");
        System.out.println("3. Update class attendance");
        System.out.println("4. Delete class attendance by ID");
        System.out.println("9. Exit");
    }

    private int executeClassAttendancesOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllClassAttendances();
                break;
            case 2:
                showClassAttendanceById();
                break;
            case 3:
                updateClassAttendance();
                break;
            case 4:
                deleteClassAttendanceById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllClassAttendances() {
        System.out.println(classAttendanceService.getAllClassAttendances());
    }

    private void showClassAttendanceById() throws InvalidId {
        Long classAttendanceId = readLong("Enter class attendance ID:");
        ClassAttendance classAttendance = classAttendanceService.getClassAttendance(classAttendanceId);
        if (classAttendance != null)
            System.out.println(classAttendance);
        else
            System.out.println("Class attendance doesn't exist!");
    }

    private ClassSession getClassSession() throws InvalidId {
        while (true) {
            Long classSessionId = readLong("Enter class session id:");
            if(classSessionService.getClassSessionById(classSessionId) != null)
                return classSessionService.getClassSessionById(classSessionId);
            System.out.println("Invalid class session id! Please try again!");
        }
    }

    private void updateClassAttendance() throws InvalidId {
        Long classAttendanceId = readLong("Enter class attendance ID to update:");
        ClassAttendance classAttendance = classAttendanceService.getClassAttendance(classAttendanceId);
        if (classAttendance != null) {
            System.out.println("Enter updated class attendance details:");
            boolean present = readBoolean("Is the student present? (true/false): ");
            Integer grade = null;
            if (present) {
                grade = readGrade();
            }
            classAttendance.setPresent(present);
            classAttendance.setGrade(grade);
            classAttendanceService.updateClassAttendance(classAttendance);
            System.out.println("Class attendance updated successfully!");
        } else {
            System.out.println("Class attendance ID incorrect!");
        }
    }




    private void deleteClassAttendanceById() throws InvalidId {
        Long classAttendanceId = readLong("Enter class attendance ID:");
        ClassAttendance classAttendance = classAttendanceService.getClassAttendance(classAttendanceId);
        if (classAttendance != null) {
            classAttendanceService.deleteClassAttendance(classAttendanceId);
            System.out.println("Class attendance deleted successfully!");
        } else {
            System.out.println("Class attendance ID incorrect!");
        }
    }

}
