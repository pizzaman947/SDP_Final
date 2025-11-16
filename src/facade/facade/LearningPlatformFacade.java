package facade.facade;

import adapter.adapter.GradeAdapter;
import adapter.adaptee.PercentageGradingSystem;
import builder.concrete_builder.CourseBuilder;
import decorator.component.Lesson;
import decorator.concrete_component.*;
import decorator.decorator.ChatDecorator;
import factory.creator.LessonFactory;
import factory.concrete_creator.*;
import observer.subject.Course;
import observer.observer.Student;
import strategy.context.LanguageLearningApp;
import strategy.strategy.*;
import util.*;
import java.util.*;

public class LearningPlatformFacade {
    private LanguageLearningApp learningApp;
    private Map<String, List<String>> lessonDatabase;
    private List<Lesson> createdLessons;
    private List<Course> createdCourses;
    private Map<Course, List<Lesson>> courseLessons;
    private List<Student> allStudents;
    private CourseBuilder currentBuilder;
    
    public LearningPlatformFacade() {
        this.learningApp = new LanguageLearningApp();
        this.learningApp.setLearningStrategy(new VideoLearning());
        this.lessonDatabase = new HashMap<>();
        this.createdLessons = new ArrayList<>();
        this.createdCourses = new ArrayList<>();
        this.courseLessons = new HashMap<>();
        this.allStudents = new ArrayList<>();
        initializeLessonDatabase();
    }
    
    private void initializeLessonDatabase() {
        lessonDatabase.put("Beginner", Arrays.asList(
            "Russian Alphabet", "Basic Greetings", "Numbers 1-10", "Colors", "Family Members"
        ));
        lessonDatabase.put("Intermediate", Arrays.asList(
            "Past Tense", "Future Tense", "Verbs of Motion", "Aspect of Verbs", "Cases Review"
        ));
        lessonDatabase.put("Advanced", Arrays.asList(
            "Advanced Syntax", "Literary Analysis", "Business Russian", "Scientific Discourse", "Cultural Context"
        ));
    }
    
    public String getCurrentStrategyName() {
        LearningStrategy strategy = learningApp.getLearningStrategy();
        return strategy != null ? strategy.getMethodName() : "None";
    }
    
    public int getCreatedLessonsCount() { return createdLessons.size(); }
    public int getCreatedCoursesCount() { return createdCourses.size(); }
    public int getAllStudentsCount() { return allStudents.size(); }
    public List<Lesson> getCreatedLessons() { return new ArrayList<>(createdLessons); }
    public List<Course> getCreatedCourses() { return new ArrayList<>(createdCourses); }
    public List<Student> getAllStudents() { return new ArrayList<>(allStudents); }
    public List<String> getTopicsForLevel(Level level) { return new ArrayList<>(lessonDatabase.get(level.getName())); }
    
    public void startLesson(Level level, String topic) {
        LearningStrategy strategy = learningApp.getLearningStrategy();
        if (strategy == null) {
            System.out.println("No learning strategy set!");
            return;
        }
        
        LessonFactory factory = strategy.getFactory();
        String resource = getResourceForTopic(level, topic);
        String transformedResource = factory.getResourceTransform(resource);
        Lesson lesson = factory.createLesson(topic, transformedResource);
        
        System.out.println("\n--- Starting Lesson ---");
        learningApp.startLearning(topic);
        lesson.start();
        System.out.println("\nLesson completed!");
    }
    
    public void changeStrategy(int choice) {
        LearningStrategy strategy = null;
        switch (choice) {
            case 1: strategy = new VideoLearning(); break;
            case 2: strategy = new AudioPractice(); break;
            case 3: strategy = new ReadingArticles(); break;
        }
        if (strategy != null) {
            learningApp.setLearningStrategy(strategy);
            learningApp.startLearning("General learning session");
        }
    }
    
    public Lesson createLesson(int typeChoice, String topic, String resource) {
        LessonFactory factory = null;
        switch (typeChoice) {
            case 1: factory = new VideoLessonFactory(); break;
            case 2: factory = new AudioLessonFactory(); break;
            case 3: factory = new PracticeLessonFactory(); break;
        }
        if (factory != null) {
            Lesson lesson = factory.createLesson(topic, resource);
            createdLessons.add(lesson);
            return lesson;
        }
        return null;
    }
    
    public Lesson deleteLesson(int index) {
        return (index >= 0 && index < createdLessons.size()) ? createdLessons.remove(index) : null;
    }
    
    public String convertGrade(int percentage) {
        return new GradeAdapter(new PercentageGradingSystem()).getLetterGrade(percentage, 100);
    }
    
    public void decorateAndStartLesson(Level level, String topic, int lessonType, boolean addChat) {
        String resource = getResourceForTopic(level, topic);
        Lesson baseLesson = null;
        
        switch (lessonType) {
            case 1: baseLesson = new VideoLesson(topic, resource); break;
            case 2: baseLesson = new AudioLesson(topic, "Audio: " + resource.replace("youtu.be", "audio.com")); break;
            case 3: baseLesson = new PracticeLesson(topic, "Practice exercises for " + topic); break;
        }
        
        Lesson finalLesson = addChat ? new ChatDecorator(baseLesson, "Zoom Chat") : baseLesson;
        if (addChat) System.out.println("\nChat decorator added!");
        
        System.out.println("\n--- Starting Lesson ---");
        finalLesson.start();
    }
    
    public void addStudentToCourse(String studentName, int courseIndex) {
        if (courseIndex >= 0 && courseIndex < createdCourses.size()) {
            Student newStudent = new Student(studentName);
            createdCourses.get(courseIndex).subscribe(newStudent);
            allStudents.add(newStudent);
        } else {
            System.out.println("Invalid course choice");
        }
    }
    
    public void removeStudent(int studentIndex) {
        if (studentIndex >= 0 && studentIndex < allStudents.size()) {
            Student toRemove = allStudents.remove(studentIndex);
            createdCourses.forEach(course -> course.unsubscribe(toRemove));
            System.out.println(toRemove.getStudentName() + " removed from all courses");
        }
    }
    
    public void makeCourseAnnouncement(int courseIndex, String announcement) {
        if (courseIndex >= 0 && courseIndex < createdCourses.size()) {
            createdCourses.get(courseIndex).announceEvent(announcement);
        }
    }
    
    public void startCourseBuilder(String courseName) { currentBuilder = new CourseBuilder(courseName); }
    public void addLessonToBuilder(Lesson lesson) { if (currentBuilder != null) currentBuilder.addLesson(lesson); }
    public List<Lesson> getBuilderLessons() { return currentBuilder != null ? currentBuilder.getLessons() : new ArrayList<>(); }
    public int getBuilderLessonsCount() { return currentBuilder != null ? currentBuilder.getLessons().size() : 0; }
    public void cancelCourseBuilder() { currentBuilder = null; }
    
    public Course finishCourseBuilder() {
        if (currentBuilder != null) {
            Course newCourse = currentBuilder.build();
            createdCourses.add(newCourse);
            courseLessons.put(newCourse, new ArrayList<>(newCourse.getLessons()));
            newCourse.getLessons().forEach(l -> newCourse.addNewLesson(l.getTopic() + " [" + l.getClass().getSimpleName() + "]"));
            currentBuilder = null;
            return newCourse;
        }
        return null;
    }
    
    public int getCourseLessonsCount(Course course) {
        List<Lesson> lessons = courseLessons.get(course);
        return lessons != null ? lessons.size() : 0;
    }
    
    public List<Lesson> getCourseLessons(Course course) {
        List<Lesson> lessons = courseLessons.get(course);
        return lessons != null ? new ArrayList<>(lessons) : new ArrayList<>();
    }
    
    public void addLessonToCourse(Course course, Lesson lesson) {
        List<Lesson> lessons = courseLessons.get(course);
        if (lessons != null) {
            lessons.add(lesson);
            course.addLesson(lesson);
            course.addNewLesson(lesson.getTopic() + " [" + lesson.getClass().getSimpleName() + "]");
        }
    }
    
    public Lesson removeLessonFromCourse(Course course, int index) {
        List<Lesson> lessons = courseLessons.get(course);
        return (lessons != null && index >= 0 && index < lessons.size()) ? lessons.remove(index) : null;
    }
    
    public void deleteCourse(Course course) {
        createdCourses.remove(course);
        courseLessons.remove(course);
    }
    
    public Lesson createLessonFromDatabase(Level level, String topic, int typeChoice) {
        String resource = getResourceForTopic(level, topic);
        switch (typeChoice) {
            case 1: return new VideoLesson(topic, resource);
            case 2: return new AudioLesson(topic, "Audio: " + resource.replace("youtu.be", "audio.com"));
            case 3: return new PracticeLesson(topic, "Practice exercises");
            default: return null;
        }
    }
    
    private String getResourceForTopic(Level level, String topic) {
        List<String> topics = lessonDatabase.get(level.getName());
        int index = topics.indexOf(topic);
        return index >= 0 ? "https://youtu.be/" + level.getName().toLowerCase() + (index + 1) : "https://youtu.be/default";
    }
}
