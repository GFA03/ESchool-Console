package org.example.repositories;

import org.example.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements GenericRepository<Course> {
    List<Course> courses;

    public CourseRepository() {
        courses = new ArrayList<>();
    }
    @Override
    public void add(Course entity) {
        courses.add(entity);
    }


    public void create(String name) {
        courses.add(new Course(name));
    }

    @Override
    public Course get(Long id) {
        for(Course course: courses) {
            if(course.getId().equals(id))
                return course;
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public void update(Course entity) {
        for(int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(entity.getId())) {
                courses.set(i, entity);
                return;
            }
        }
    }

    public void updateName(Course course, String name) {
        course.setName(name);
        update(course);
    }

    @Override
    public void delete(Long id) {
        courses.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public int getSize() {
        return courses.size();
    }

}
