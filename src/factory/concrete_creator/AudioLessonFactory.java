package factory.concrete_creator;

import factory.creator.LessonFactory;
import decorator.component.Lesson;
import decorator.concrete_component.AudioLesson;

public class AudioLessonFactory extends LessonFactory {
    @Override
    public Lesson createLesson(String topic, String audioResource) {
        return new AudioLesson(topic, audioResource);
    }
    
    @Override
    public String getResourceTransform(String resource) {
        return "Audio: " + resource.replace("youtu.be", "audio.com");
    }
}
