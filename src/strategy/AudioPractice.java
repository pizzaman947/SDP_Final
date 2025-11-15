package strategy;



public class AudioPractice implements LearningStrategy {
    private String learningMessage = "learning through audio practice on:";

    public void learn(String topic) {
        System.out.println(learningMessage + topic);
    }

    public void setLearningStrategy(String message) {
        this.learningMessage = message;
    }
}
