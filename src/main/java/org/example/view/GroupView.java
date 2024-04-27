package org.example.view;

import org.example.exceptions.InvalidId;
import org.example.exceptions.InvalidOption;
import org.example.models.Group;
import org.example.services.GroupService;

import static org.example.utils.ReaderUtils.readLong;
import static org.example.utils.ReaderUtils.readName;
import static org.example.utils.ReaderUtils.readOption;

public class GroupView {
    private final GroupService groupService;

    public GroupView(GroupService groupService) {
        this.groupService = groupService;
    }

    public void run () {
        while (true) {
            showGroupsMenu();
            try {
                int option = readOption();
                int status = executeGroupsOptions(option);
                if (status == -1)
                    break;
            } catch (InvalidOption | InvalidId e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void showGroupsMenu() {
        System.out.println("Groups menu:");
        System.out.println("1. Show all groups");
        System.out.println("2. Show group by ID");
        System.out.println("3. Create group");
        System.out.println("4. Update group");
        System.out.println("5. Delete group by ID");
        System.out.println("9. Exit");
    }

    private int executeGroupsOptions(int option) throws InvalidId {
        switch (option) {
            case 1:
                showAllGroups();
                break;
            case 2:
                showGroupById();
                break;
            case 3:
                createGroup();
                System.out.println("Group created successfully!");
                break;
            case 4:
                updateGroup();
                break;
            case 5:
                deleteGroupById();
                break;
            case 9:
                System.out.println("Exiting...");
                return -1;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return 0;
    }

    private void showAllGroups() {
        System.out.println(groupService.getAllGroups());
    }

    private void showGroupById() throws InvalidId {
        Long groupId = readLong("Enter group ID: ");
        Group group = groupService.getGroupById(groupId);
        if (group != null)
            System.out.println(group);
        else
            System.out.println("Group doesn't exist!");
    }

    private void createGroup() {
        String groupName = readName("Enter group name:");
        Group group = new Group(1L, groupName);
        groupService.addGroup(group);
    }

    private void updateGroup() throws InvalidId {
        Long groupId = readLong("Enter group ID to update:");
        Group group = groupService.getGroupById(groupId);
        if (group != null) {
            String groupName = readName("Enter updated group name:");
            groupService.updateGroupName(group, groupName);
            System.out.println("Group updated successfully!");
        } else {
            System.out.println("Group ID incorrect!");
        }
    }

    private void deleteGroupById() throws InvalidId {
        Long groupId = readLong("Enter group ID:");
        Group group = groupService.getGroupById(groupId);
        if (group != null) {
            groupService.deleteGroup(groupId);
            System.out.println("Group deleted successfully!");
        } else {
            System.out.println("Group ID incorrect!");
        }
    }
}
