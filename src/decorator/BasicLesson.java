package decorator;

public class BasicLesson implements Lesson {
    @Override
    public void start() {
        System.out.println("Starting Lesson");
    }
}
