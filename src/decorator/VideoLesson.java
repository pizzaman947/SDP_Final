package decorator;

public class VideoLesson implements Lesson {
    private String topic;
    private String videoUrl;

    public VideoLesson(String topic, String videoUrl) {
        this.topic = topic;
        this.videoUrl = videoUrl;
    }
    @Override
    public void start() {
        System.out.println("Video Lesson: " + topic + "\nWatch: " + videoUrl);
    }
    @Override
    public String getTopic() {
        return topic;
    }
    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String url) {
        this.videoUrl = url;
    }
}
