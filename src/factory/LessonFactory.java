package factory;

import decorator.*;

public abstract class LessonFactory {
    public abstract Lesson createLesson(String topic, String additionalInfo);
}
