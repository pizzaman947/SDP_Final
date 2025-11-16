package factory;

import decorator.*;

public class PracticeLessonFactory extends LessonFactory {
    @Override
    public Lesson createLesson(String topic, String exercises) {
        return new PracticeLesson(topic, exercises);
    }
    
    @Override
    public String getResourceTransform(String resource) {
        return "Practice exercises";
    }
}
