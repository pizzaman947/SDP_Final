package decorator;

public class AudioLesson implements Lesson {
    private String topic;
    private String audioResource;

    public AudioLesson(String topic, String audioResource) {
        this.topic = topic;
        this.audioResource = audioResource;
    }

    @Override
    public void start() {
        System.out.println("Audio Practice: " + topic);
        System.out.println("Listen: " + audioResource);
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAudioResource() {
        return audioResource;
    }

    public void setAudioResource(String audioResource) {
        this.audioResource = audioResource;
    }
}
