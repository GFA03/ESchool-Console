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

    public void createParent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) {
        parentRepository.create(firstName, lastName, dateOfBirth, email, phoneNumber);
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

    public void updateParentFirstName(Parent parent, String firstName) { parentRepository.updateFirstName(parent, firstName);}
    public void updateParentLastName(Parent parent, String lastName) { parentRepository.updateLastName(parent, lastName);}
    public void updateParentDateOfBirth(Parent parent, String dateOfBirth) { parentRepository.updateDateOfBirth(parent, dateOfBirth);}
    public void updateParentEmail(Parent parent, String email) { parentRepository.updateEmail(parent, email);}
    public void updateParentPhoneNumber(Parent parent, String phoneNumber) { parentRepository.updatePhoneNumber(parent, phoneNumber);}


    public void deleteParent(Long id) {
        parentRepository.delete(id);
    }

    public int getSize() {
        return parentRepository.getSize();
    }
}
