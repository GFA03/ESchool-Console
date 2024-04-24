package org.example.view;

import org.example.exceptions.InvalidEmail;
import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.exceptions.InvalidRequest;
import org.example.models.Parent;
import org.example.models.Student;
import org.example.services.ParentService;
import org.example.services.StudentService;

import java.util.List;

import static org.example.utils.ReaderUtils.*;
import static org.example.view.ConsoleApp.showPersonByIdMenu;
import static org.example.view.ConsoleApp.showUpdatePersonMenu;

public class ParentView {
    private final ParentService parentService;
    private final StudentService studentService;

    public ParentView (ParentService parentService, StudentService studentService) {
        this.parentService = parentService;
        this.studentService = studentService;
    }

    public void run () {
        while (true) {
            showParentsMenu();
            try {
                int option = readOption();
                int status = executeParentsOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidId | InvalidRequest e) {
                System.out.println(e.getMessage());
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

    private int executeParentsOptions(int option) throws InvalidId, InvalidRequest {
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
        Long parentId = readLong("Enter parent id: ");
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
        Parent parent = new Parent(1L, firstName, lastName, dateOfBirth, email, phoneNumber);
        parentService.addParent(parent);
    }

    private void deleteParent() throws InvalidId {
        Long parentId = readLong("Enter parent id:");
        if(parentService.getParentById(parentId) != null) {
            parentService.deleteParent(parentId);
            System.out.println("Parent deleted successfully!");
        } else {
            System.out.println("Parent id incorrect!");
        }
    }

    private void manageParentById() throws InvalidId, InvalidRequest {
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
//TODO: find class for readParent
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
            Long studentId = readLong("Please choose a child to show activity or 0 to leave");
            if (studentId == 0L)
                return;
            for (Student student: children) {
                if (student.getId().equals(studentId)) {
                    viewChildrenStats(student);
                    return;
                }
            }
        }
    }

    private void viewChildrenStats(Student student) {
        studentService.showStats(student);
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
                String firstName = readName("Enter new first name:");
                parentService.updateParentFirstName(parent, firstName);
                break;
            case 2:
                String lastName = readName("Enter new last name:");
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
}
