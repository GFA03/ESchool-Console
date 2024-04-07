package org.example.repositories;

import org.example.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GenericRepository<Group> {
    private final List<Group> groups;

    public GroupRepository() {
        groups = new ArrayList<>();
    }

    public void add(Group group) {
        groups.add(group);
    }

    public List<Group> getAll() {
        return groups;
    }

    public Group get(Long groupId) {
        for (Group group : groups) {
            if (group.getId().equals(groupId))
                return group;
        }
        return null;
    }

    public void update(Group updatedGroup) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(updatedGroup.getId())) {
                groups.set(i, updatedGroup);
                return;
            }
        }
    }

    public void delete(Long groupId) {
        groups.removeIf(group -> group.getId().equals(groupId));
    }

    public int getSize() {
        return groups.size();
    }
}
