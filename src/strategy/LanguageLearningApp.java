package strategy;

public class LanguageLearningApp {
    private LearningStrategy learningStrategy;
    private String currentTopic;

    public void setLearningStrategy(LearningStrategy strategy) {
        this.learningStrategy = strategy;
        System.out.println("Learning method changed to: " + strategy.getMethodName());
    }

    public void startLearning(String topic) {
        if (learningStrategy == null) {
            System.out.println("No learning method selected!");
        } else {
            this.currentTopic = topic;
            learningStrategy.learn(topic);
        }
    }

    public LearningStrategy getLearningStrategy() {
        return learningStrategy;
    }

    public String getCurrentTopic() {
        return currentTopic;
    }
}
