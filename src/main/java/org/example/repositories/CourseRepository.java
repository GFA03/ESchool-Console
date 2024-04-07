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

    @Override
    public Course get(Long id) {
        for(Course course: courses) {
            if(course.getCourseId().equals(id))
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
            if (courses.get(i).getCourseId().equals(entity.getCourseId())) {
                courses.set(i, entity);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        courses.removeIf(c -> c.getCourseId().equals(id));
    }

    @Override
    public int getSize() {
        return courses.size();
    }
}
