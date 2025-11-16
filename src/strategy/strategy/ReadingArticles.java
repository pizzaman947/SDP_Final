package strategy.strategy;

import factory.creator.LessonFactory;
import factory.concrete_creator.PracticeLessonFactory;

public class ReadingArticles implements LearningStrategy {
    @Override
    public void learn(String topic) {
        System.out.println("Learning by practice lessons about: " + topic);
    }

    @Override
    public String getMethodName() {
        return "Practice Lessons";
    }
    
    @Override
    public LessonFactory getFactory() {
        return new PracticeLessonFactory();
    }
}
