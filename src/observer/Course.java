package observer;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private List<CourseObserver> observers;
    private int lessonsCompleted;
    private List<decorator.Lesson> lessons;

    public Course(String courseName) {
        this.courseName = courseName;
        this.observers = new ArrayList<>();
        this.lessonsCompleted = 0;
        this.lessons = new ArrayList<>();
    }
    
    public Course(String courseName, List<decorator.Lesson> lessons) {
        this.courseName = courseName;
        this.observers = new ArrayList<>();
        this.lessonsCompleted = 0;
        this.lessons = new ArrayList<>(lessons);
    }

    public void subscribe(CourseObserver observer) {
        observers.add(observer);
        System.out.println(observer.getStudentName() + " subscribed to course \"" + courseName + "\"");
    }

    public void unsubscribe(CourseObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getStudentName() + " unsubscribed from course \"" + courseName + "\"");
    }

    public void notifyObservers(String message) {
        for (CourseObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addNewLesson(String lessonName) {
        lessonsCompleted++;
        String message = "New lesson available: \"" + lessonName + "\" (Course: " + courseName + ")";
        notifyObservers(message);
    }

    public void announceEvent(String event) {
        String message = "Announcement for course \"" + courseName + "\": " + event;
        notifyObservers(message);
    }

    public String getCourseName() {
        return courseName;
    }

    public int getLessonsCompleted() {
        return lessonsCompleted;
    }

    public int getObserversCount() {
        return observers.size();
    }
    
    public List<decorator.Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }
    
    public void addLesson(decorator.Lesson lesson) {
        lessons.add(lesson);
    }
}
