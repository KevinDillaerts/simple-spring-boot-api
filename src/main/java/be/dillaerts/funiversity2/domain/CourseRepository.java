package be.dillaerts.funiversity2.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CourseRepository {
    private final ConcurrentHashMap<String, Course> courses;

    public CourseRepository() {
        this.courses = new ConcurrentHashMap<>();
    }

    public Collection<Course> getCourses() {
        return courses.values();
    }

    public Course createCourse(Course newCourse) {
        courses.put(newCourse.getId(), newCourse);
        return newCourse;
    }
}
