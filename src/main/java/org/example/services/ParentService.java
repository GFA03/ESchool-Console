package org.example.services;

import org.example.models.Parent;
import org.example.repositories.ParentRepository;

import java.util.List;

public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void addParent(Parent parent) {
        parentRepository.add(parent);
    }

    public Parent getParentById(Long id) {
        return parentRepository.get(id);
    }

    public List<Parent> getAllParents() {
        return parentRepository.getAll();
    }

    public void updateParent(Parent updatedParent) {
        parentRepository.update(updatedParent);
    }

    public void updateParentFirstName(Parent parent, String firstName) {
        parent.setFirstName(firstName);
        updateParent(parent);
    }
    public void updateParentLastName(Parent parent, String lastName) {
        parent.setLastName(lastName);
        updateParent(parent);
    }
    public void updateParentDateOfBirth(Parent parent, String dateOfBirth) {
        parent.setDateOfBirth(dateOfBirth);
        updateParent(parent);
    }
    public void updateParentEmail(Parent parent, String email) {
        parent.setEmail(email);
        updateParent(parent);
    }
    public void updateParentPhoneNumber(Parent parent, String phoneNumber) {
        parent.setPhoneNumber(phoneNumber);
        updateParent(parent);
    }


    public void deleteParent(Long id) {
        parentRepository.delete(id);
    }

    public int getSize() {
        return parentRepository.getSize();
    }
}
