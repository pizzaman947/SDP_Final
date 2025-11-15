package strategy;


public class LanguageLearningApp {
    private LearningStrategy learningStrategy;
    private Language language;

    public void startLearning(String topic) {
        if (learningStrategy == null) {
            String noMethodMessage = "No learning method selected!";
            System.out.println(noMethodMessage);
        } else if (language == null) {
            String noLanguageMessage = "No language selected!";
            System.out.println(noLanguageMessage);
        } else {
            System.out.println("Language: " + language.getLanguageName());
            learningStrategy.learn(topic);
        }
    }

    public void setLearningStrategy(LearningStrategy strategy) {
        this.learningStrategy = strategy;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


}
