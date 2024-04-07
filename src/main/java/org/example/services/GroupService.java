package org.example.services;

import org.example.models.Group;
import org.example.repositories.GroupRepository;

import java.util.List;

public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addGroup(Group group) {
        groupRepository.add(group);
    }

    public Group getGroupById(Long id) {
        return groupRepository.get(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.getAll();
    }

    public void updateGroup(Group updatedGroup) {
        groupRepository.update(updatedGroup);
    }

    public void deleteGroup(Long id) {
        groupRepository.delete(id);
    }
}
