package strategy.strategy;

import factory.creator.LessonFactory;

public interface LearningStrategy {
    void learn(String topic);
    String getMethodName();
    LessonFactory getFactory();
}
