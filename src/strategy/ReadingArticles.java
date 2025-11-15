package strategy;

public class ReadingArticles implements LearningStrategy {
    @Override
    public void learn(String topic) {
        System.out.println("Learning by reading articles about: " + topic);
    }

    @Override
    public String getMethodName() {
        return "Reading Articles";
    }
}
