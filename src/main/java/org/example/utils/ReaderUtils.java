package org.example.utils;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.exceptions.InvalidRequest;
import org.example.models.Group;
import org.example.models.Parent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReaderUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readOption() throws InvalidOption {
        System.out.println("Enter your option: ");
        int option = readInt();
        if(option >= 1 && option <= 9)
            return option;
        throw new InvalidOption("Invalid option!");
    }

    public static int readInt() throws InvalidOption {
        String line = scanner.nextLine();
        if(line.matches("^\\d+$"))
            return Integer.parseInt(line);
        throw new InvalidOption("Invalid option!");
    }

    public static long readLong(String inputText) throws InvalidId {
        System.out.println(inputText);
        String line = scanner.nextLine();
        if(line.matches("^\\d+$"))
            return Long.parseLong(line);
        throw new InvalidId("Invalid id!");
    }

    public static String readName(String inputText) {
        System.out.println(inputText);
        return scanner.nextLine();
    }

    public static String readDate(String inputText) {
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

    public static String readEmail() {
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

    public static String readPhone() {
        String phoneNumber;
        while(true) {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.nextLine().trim();
            if(Pattern.compile("\\d{10}").matcher(phoneNumber).matches())
                return phoneNumber;
            System.out.println("Invalid phone number! Please try again!");
        }
    }
// TODO: readParent, readTable should be in that tableService

//    public static Parent readParent() throws InvalidId {
//        while (true) {
//            System.out.println("Enter parent id(Or 0 for NULL):");
//            Long parentId = readLong();
//            if(parentId == 0L)
//                return null;
//            if(parentService.getParentById(parentId) != null)
//                return parentService.getParentById(parentId);
//            System.out.println("Invalid parent id! Please try again!");
//        }
//    }
//
//    public static Group getGroup() throws InvalidId {
//        while (true) {
//            System.out.println("Enter group id(Or 0 for NULL):");
//            Long groupId = readLong();
//            if(groupId == 0L)
//                return null;
//            if(groupService.getGroupById(groupId) != null)
//                return groupService.getGroupById(groupId);
//            System.out.println("Invalid group id! Please try again");
//        }
//    }
//
//    public static Group getGroupNotNull() throws InvalidId, InvalidRequest {
//        if (groupService.getSize() == 0)
//            throw new InvalidRequest("Invalid request! There are no groups!");
//        while (true) {
//            System.out.println("Enter group id:");
//            Long groupId = readLong();
//            if(groupService.getGroupById(groupId) != null)
//                return groupService.getGroupById(groupId);
//            System.out.println("Invalid group id! Please try again");
//        }
//    }
}
