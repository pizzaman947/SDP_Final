package strategy;

import factory.LessonFactory;

public interface LearningStrategy {
    void learn(String topic);
    String getMethodName();
    LessonFactory getFactory();
}
