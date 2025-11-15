package builder;

import decorator.Lesson;
import observer.Course;
import java.util.*;

public class CourseBuilder {
    private String courseName;
    private List<Lesson> lessons;
    
    public CourseBuilder(String courseName) {
        this.courseName = courseName;
        this.lessons = new ArrayList<>();
    }
    
    public CourseBuilder addLesson(Lesson lesson) {
        this.lessons.add(lesson);
        return this;
    }
    
    public CourseBuilder addLessons(List<Lesson> lessons) {
        this.lessons.addAll(lessons);
        return this;
    }
    
    public Course build() {
        Course course = new Course(courseName);
        return course;
    }
    
    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }
    
    public String getCourseName() {
        return courseName;
    }
}
