package strategy.strategy;

import factory.creator.LessonFactory;
import factory.concrete_creator.AudioLessonFactory;

public class AudioPractice implements LearningStrategy {
    @Override
    public void learn(String topic) {
        System.out.println("Learning through audio practice on: " + topic);
    }

    @Override
    public String getMethodName() {
        return "Audio Practice";
    }
    
    @Override
    public LessonFactory getFactory() {
        return new AudioLessonFactory();
    }
}
