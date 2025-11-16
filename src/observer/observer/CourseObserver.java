package observer.observer;

public interface CourseObserver {
    void update(String message);
    String getStudentName();
}
