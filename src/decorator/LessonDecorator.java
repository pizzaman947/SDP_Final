package decorator;

public abstract class LessonDecorator implements Lesson {
    protected Lesson decoratedLesson;
    public LessonDecorator(Lesson lesson) {
        this.decoratedLesson = lesson;
    }
    @Override
    public void start() {
        decoratedLesson.start();
    }
}
