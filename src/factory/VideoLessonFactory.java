package factory;

import decorator.*;

public class VideoLessonFactory extends LessonFactory {
    @Override
    public Lesson createLesson(String topic, String videoUrl) {
        return new VideoLesson(topic, videoUrl);
    }
}
