package decorator.concrete_component;

import decorator.component.Lesson;

public class PracticeLesson implements Lesson {
    private String topic;
    private String exercises;

    public PracticeLesson(String topic, String exercises) {
        this.topic = topic;
        this.exercises = exercises;
    }

    @Override
    public void start() {
        System.out.println("Practice Lesson: " + topic);
        System.out.println("Exercises: " + exercises);
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }
}
