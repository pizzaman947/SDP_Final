package builder;

import java.util.List;

public class Course {
    private String language;
    private String level;
    private List<String> topics;
    private List<String> learningMethods;

    public Course(String language, String level, List<String> topics, List<String> learningMethods) {
        this.language = language;
        this.level = level;
        this.topics = topics;
        this.learningMethods = learningMethods;
    }

    public void show() {
        System.out.println("\n📘 COURSE CREATED");
        System.out.println("Language: " + language);
        System.out.println("Level: " + level);

        System.out.println("Topics:");
        for (String t : topics) System.out.println("  - " + t);

        System.out.println("Methods:");
        for (String m : learningMethods) System.out.println("  - " + m);
    }
}