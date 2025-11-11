package decorator;

public class AudioLessonDecorator extends LessonDecorator {
    public AudioLessonDecorator(Lesson lesson) {
        super(lesson);
    }
    @Override
    public void start() {
        super.start();
        System.out.println("Audio playback enabled for new words");
    }
}
