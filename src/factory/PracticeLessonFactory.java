package factory;

import decorator.*;

public class PracticeLessonFactory extends LessonFactory {
    @Override
    public Lesson createLesson(String topic, String exercises) {
        return new PracticeLesson(topic, exercises);
    }
}
