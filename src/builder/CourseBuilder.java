package builder;

import java.util.ArrayList;
import java.util.List;

public class CourseBuilder {
    private String language;
    private String level;
    private List<String> topics = new ArrayList<>();
    private List<String> methods = new ArrayList<>();

    public CourseBuilder setLanguage(String lang) {
        this.language = lang;
        return this;
    }

    public CourseBuilder setLevel(String level) {
        this.level = level;
        return this;
    }

    public CourseBuilder addTopic(String topic) {
        this.topics.add(topic);
        return this;
    }

    public CourseBuilder addMethod(String method) {
        this.methods.add(method);
        return this;
    }

    public Course build() {
        return new Course(language, level, topics, methods);
    }
}