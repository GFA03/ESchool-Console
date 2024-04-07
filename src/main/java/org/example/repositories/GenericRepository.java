package org.example.repositories;

import org.example.models.Person;
import org.example.models.Student;

import java.util.ArrayList;
import java.util.List;

public interface GenericRepository<T>{
    public void add(T entity);

    public T get(Long id);

    public List<T> getAll();

    public void update(T entity);

    public void delete(Long id);

    public int getSize();
}
