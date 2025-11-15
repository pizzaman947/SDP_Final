package strategy;



public class ReadingArticles implements LearningStrategy {
    private String learningMessage = "learning by reading articles about:";

    public void learn(String topic) {
        System.out.println(learningMessage + topic);
    }

    public void setLearningMessage(String learningMessage) {
        this.learningMessage = learningMessage;
    }
}
