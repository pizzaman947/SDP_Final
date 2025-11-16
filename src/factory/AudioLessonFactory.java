package factory;

import decorator.*;

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
