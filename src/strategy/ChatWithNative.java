package strategy;



public class ChatWithNative implements LearningStrategy {
    private String learningMessage = "practicing with native speaker about:";

    public void learn(String topic) {
        System.out.println(learningMessage + topic);

    }


    public void setLearningMessage(String learningMessage) {
        this.learningMessage = learningMessage;
    }
}
