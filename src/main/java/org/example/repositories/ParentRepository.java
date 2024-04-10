package org.example.repositories;

import org.example.models.Parent;

import java.util.ArrayList;
import java.util.List;

public class ParentRepository implements GenericRepository<Parent> {
    private final List<Parent> parents;

    public ParentRepository() {
        parents = new ArrayList<>();
    }

    @Override
    public void add(Parent parent) {
        parents.add(parent);
    }


    public void create(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        parents.add(new Parent(firstName, lastName, dateOfBirth, email, phoneNumber));
    }

    @Override
    public List<Parent> getAll() {
        return parents;
    }

    @Override
    public Parent get(Long parentId) {
        for (Parent parent : parents) {
            if (parent.getId().equals(parentId))
                return parent;
        }
        return null;
    }

    @Override
    public void update(Parent updatedParent) {
        for (int i = 0; i < parents.size(); i++) {
            if (parents.get(i).getId().equals(updatedParent.getId())) {
                parents.set(i, updatedParent);
                return;
            }
        }
    }

    public void updateFirstName(Parent parent, String firstName) {
        parent.setFirstName(firstName);
        update(parent);
    }

    public void updateLastName(Parent parent, String lastName) {
        parent.setLastName(lastName);
        update(parent);
    }

    public void updateDateOfBirth(Parent parent, String dateOfBirth) {
        parent.setDateOfBirth(dateOfBirth);
        update(parent);
    }

    public void updateEmail(Parent parent, String email) {
        parent.setEmail(email);
        update(parent);
    }

    public void updatePhoneNumber(Parent parent, String phoneNumber) {
        parent.setPhoneNumber(phoneNumber);
        update(parent);
    }

    @Override
    public void delete(Long parentId) {
        parents.removeIf(parent -> parent.getId().equals(parentId));
    }

    @Override
    public int getSize() {
        return parents.size();
    }

}

