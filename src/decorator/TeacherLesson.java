package decorator;


public class TeacherLesson implements Lesson {
    private String topic;
    private String teacherName;

    public TeacherLesson(String topic, String teacherName) {
        this.topic = topic;
        this.teacherName = teacherName;
    }
    @Override
    public void start() {
        System.out.println("👨‍🏫 Урок с учителем: " + topic + "\nПреподаватель: " + teacherName);
    }
    @Override
    public String getTopic() { return topic; }
    @Override
    public void setTopic(String topic) { this.topic = topic; }
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
}
