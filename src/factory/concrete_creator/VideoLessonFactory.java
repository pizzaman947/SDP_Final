package factory.concrete_creator;

import factory.creator.LessonFactory;
import decorator.component.Lesson;
import decorator.concrete_component.VideoLesson;

public class VideoLessonFactory extends LessonFactory {
    @Override
    public Lesson createLesson(String topic, String videoUrl) {
        return new VideoLesson(topic, videoUrl);
    }
    
    @Override
    public String getResourceTransform(String resource) {
        return resource;
    }
}
