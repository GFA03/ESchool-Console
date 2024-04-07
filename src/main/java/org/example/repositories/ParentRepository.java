package org.example.repositories;

import org.example.models.Parent;

import java.util.ArrayList;
import java.util.List;

public class ParentRepository implements GenericRepository<Parent> {
    private final List<Parent> parents;

    public ParentRepository() {
        parents = new ArrayList<>();
    }

    public void add(Parent parent) {
        parents.add(parent);
    }

    public List<Parent> getAll() {
        return parents;
    }

    public Parent get(Long parentId) {
        for (Parent parent : parents) {
            if (parent.getId().equals(parentId))
                return parent;
        }
        return null;
    }

    public void update(Parent updatedParent) {
        for (int i = 0; i < parents.size(); i++) {
            if (parents.get(i).getId().equals(updatedParent.getId())) {
                parents.set(i, updatedParent);
                return;
            }
        }
    }

    public void delete(Long parentId) {
        parents.removeIf(parent -> parent.getId().equals(parentId));
    }

    public int getSize() {
        return parents.size();
    }
}

