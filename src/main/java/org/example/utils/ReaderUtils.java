package org.example.utils;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReaderUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readOption() throws InvalidOption {
        System.out.println("Enter your option: ");
        int option = readInt();
        if (option >= 1 && option <= 9)
            return option;
        throw new InvalidOption("Invalid option!");
    }

    public static int readInt() throws InvalidOption {
        String line = scanner.nextLine();
        if (line.matches("^\\d+$"))
            return Integer.parseInt(line);
        throw new InvalidOption("Invalid option!");
    }

    public static long readLong(String inputText) throws InvalidId {
        System.out.println(inputText);
        String line = scanner.nextLine();
        if (line.matches("^\\d+$"))
            return Long.parseLong(line);
        throw new InvalidId("Invalid id!");
    }

    public static String readName(String inputText) {
        System.out.println(inputText);
        return scanner.nextLine();
    }

    public static String readDate(String inputText) {
        String dateOfBirth;
        while (true) {
            System.out.println(inputText);
            dateOfBirth = scanner.nextLine();
            try {
                LocalDate dob = LocalDate.parse(dateOfBirth);
                return dateOfBirth;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please try again!");
            }
        }
    }

    public static String readEmail() {
        String email;
        while (true) {
            System.out.println("Enter email:");
            email = scanner.nextLine().trim();
            if (Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")
                    .matcher(email).matches())
                return email;
            System.out.println("Invalid email! Please try again!");
        }
    }

    public static String readPhone() {
        String phoneNumber;
        while (true) {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.nextLine().trim();
            if (Pattern.compile("\\d{10}").matcher(phoneNumber).matches())
                return phoneNumber;
            System.out.println("Invalid phone number! Please try again!");
        }
    }

    public static boolean readBoolean(String inputText) {
        while (true) {
            System.out.println(inputText);
            String input = scanner.nextLine();
            if (Objects.equals(input.trim().toLowerCase(), "true") || Objects.equals(input.trim(), "1"))
                return true;
            if (Objects.equals(input.trim().toLowerCase(), "false") || Objects.equals(input.trim(), "0"))
                return false;
            System.out.println("Invalid input! Please write \"true\" or \"false\"!");
        }
    }


    public static Integer readGrade() {
        while (true) {
            System.out.println("Enter student grade: ");
            String input = scanner.nextLine();
            if (input.matches("[1-9]|10"))
                return Integer.parseInt(input);
            System.out.println("Invalid grade!");
        }
    }
}