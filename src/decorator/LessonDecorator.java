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

    @Override
    public String getTopic() {
        return decoratedLesson.getTopic();
    }

    @Override
    public void setTopic(String topic) {
        decoratedLesson.setTopic(topic);
    }
}