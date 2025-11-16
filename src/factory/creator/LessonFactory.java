package factory.creator;

import decorator.component.Lesson;

public abstract class LessonFactory {
    public abstract Lesson createLesson(String topic, String additionalInfo);
    public abstract String getResourceTransform(String resource);
}
