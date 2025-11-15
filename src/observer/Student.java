package observer;

public class Student implements CourseObserver {
    private String name;
    private int notificationsReceived;

    public Student(String name) {
        this.name = name;
        this.notificationsReceived = 0;
    }

    @Override
    public void update(String message) {
        notificationsReceived++;
        System.out.println("[" + name + "] received notification: " + message);
    }

    @Override
    public String getStudentName() {
        return name;
    }

    public int getNotificationsReceived() {
        return notificationsReceived;
    }
}
