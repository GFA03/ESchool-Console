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

    public void deleteParent(Long id) {
        parentRepository.delete(id);
    }
}
