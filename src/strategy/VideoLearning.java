package strategy;


public class VideoLearning implements LearningStrategy {

    public void learn(String topic) {
        String learningMessage = "learning through video lesson on:";
        System.out.println(learningMessage + topic);
    }



    public void setLearningStrategy(VideoLearning ignoredVideoLesson) {
    }
}