package decorator;

public class TimedLessonDecorator extends LessonDecorator {
    public TimedLessonDecorator(Lesson lesson) {
        super(lesson);
    }
    @Override
    public void start() {
        System.out.println("Timer: You have 5 minutes for this lesson");
        super.start();
    }
}
