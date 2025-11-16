package strategy;

import factory.*;

public class VideoLearning implements LearningStrategy {
    @Override
    public void learn(String topic) {
        System.out.println("Learning through video lesson on: " + topic);
    }

    @Override
    public String getMethodName() {
        return "Video Learning";
    }
    
    @Override
    public LessonFactory getFactory() {
        return new VideoLessonFactory();
    }
}
