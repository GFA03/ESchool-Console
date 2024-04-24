package org.example.services;

import org.example.models.Parent;
import org.example.repositories.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParentServiceTests {

    private ParentService parentService;
    private ParentRepository parentRepository;

    @BeforeEach
    void setUp() {
//        parentRepository = new ParentRepository(connection);
//        parentService = new ParentService(parentRepository);
    }

    @Test
    void testAddParent() {
        Parent parent = new Parent("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890");
        parentService.addParent(parent);

        List<Parent> parents = parentRepository.getAll();
        assertEquals(1, parents.size());
        assertEquals(parent, parents.getFirst());
    }

    @Test
    void testGetParentById() {
        Parent parent = new Parent("John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890");
        parentRepository.add(parent);

        Parent retrievedParent = parentService.getParentById(parent.getId());

        assertEquals(parent, retrievedParent);
    }


}

