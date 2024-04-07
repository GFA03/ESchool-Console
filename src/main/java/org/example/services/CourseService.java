package org.example.services;

import org.example.models.Course;
import org.example.repositories.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        courseRepository.add(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.get(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAll();
    }

    public void updateCourse(Course updatedCourse) {
        courseRepository.update(updatedCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.delete(id);
    }
}
