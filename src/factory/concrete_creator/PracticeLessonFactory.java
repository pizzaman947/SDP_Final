package factory.concrete_creator;

import factory.creator.LessonFactory;
import decorator.component.Lesson;
import decorator.concrete_component.PracticeLesson;

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
