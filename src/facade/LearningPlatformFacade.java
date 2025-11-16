package facade;

import adapter.*;
import builder.*;
import decorator.*;
import factory.*;
import observer.*;
import strategy.*;
import util.*;

import java.util.*;

public class LearningPlatformFacade {
    private Scanner scanner;
    private LanguageLearningApp learningApp;
    private String currentStrategy;
    
    private Map<String, List<String>> lessonDatabase;
    private List<Lesson> createdLessons;
    private List<Course> createdCourses;
    private Map<Course, List<Lesson>> courseLessons;
    private List<Student> allStudents;
    
    public LearningPlatformFacade(Scanner scanner) {
        this.scanner = scanner;
        this.learningApp = new LanguageLearningApp();
        this.currentStrategy = "Video Learning";
        
        this.lessonDatabase = new HashMap<>();
        this.createdLessons = new ArrayList<>();
        this.createdCourses = new ArrayList<>();
        this.courseLessons = new HashMap<>();
        this.allStudents = new ArrayList<>();
        
        initializeLessonDatabase();
    }
    
    private void initializeLessonDatabase() {
        lessonDatabase.put("Beginner", Arrays.asList(
            "Alphabet |https://youtu.be/beginner1",
            "Basic Greetings|https://youtu.be/beginner2",
            "Numbers 1-10|https://youtu.be/beginner3",
            "Colors|https://youtu.be/beginner4",
            "Family Members|https://youtu.be/beginner5"
        ));
        lessonDatabase.put("Intermediate", Arrays.asList(
            "Past Tense|https://youtu.be/intermediate1",
            "Future Tense|https://youtu.be/intermediate2",
            "Verbs of Motion|https://youtu.be/intermediate3",
            "Aspect of Verbs|https://youtu.be/intermediate4",
            "Cases Review|https://youtu.be/intermediate5"
        ));
        lessonDatabase.put("Advanced", Arrays.asList(
            "Advanced Syntax|https://youtu.be/advanced1",
            "Literary Analysis|https://youtu.be/advanced2",
            "Business |https://youtu.be/advanced3",
            "Scientific Discourse|https://youtu.be/advanced4",
            "Cultural Context|https://youtu.be/advanced5"
        ));
    }
    
    public String getCurrentStrategy() {
        return currentStrategy;
    }
    
    public int getCreatedLessonsCount() {
        return createdLessons.size();
    }
    
    public int getCreatedCoursesCount() {
        return createdCourses.size();
    }
    
    public void startLessonBasedOnStrategy() {
        System.out.println("\n=== START LESSON ===");
        System.out.println("Current Mode: " + currentStrategy);
        
        System.out.println("\nSelect your level:");
        System.out.println("1 — Beginner");
        System.out.println("2 — Intermediate");
        System.out.println("3 — Advanced");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int levelChoice = getIntInput();
        if (levelChoice == 0) return;
        
        Level level = Level.fromInt(levelChoice);
        if (level == null) {
            System.out.println("Invalid level");
            return;
        }
        
        List<String> topics = lessonDatabase.get(level.getName());
        System.out.println("\n=== " + level.getName().toUpperCase() + " TOPICS ===");
        for (int i = 0; i < topics.size(); i++) {
            String[] parts = topics.get(i).split("\\|");
            System.out.println((i + 1) + " — " + parts[0]);
        }
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int topicChoice = getIntInput();
        if (topicChoice == 0) return;
        
        if (topicChoice < 1 || topicChoice > topics.size()) {
            System.out.println("Invalid topic");
            return;
        }
        
        String[] topicData = topics.get(topicChoice - 1).split("\\|");
        String topicName = topicData[0];
        String resource = topicData[1];
        
        Lesson lesson = null;
        
        if (currentStrategy.equals("Video Learning")) {
            LessonFactory factory = new VideoLessonFactory();
            lesson = factory.createLesson(topicName, resource);
        } else if (currentStrategy.equals("Audio Practice")) {
            LessonFactory factory = new AudioLessonFactory();
            lesson = factory.createLesson(topicName, "Audio: " + resource.replace("youtu.be", "audio.com"));
        } else if (currentStrategy.equals("Practice Lessons")) {
            LessonFactory factory = new PracticeLessonFactory();
            lesson = factory.createLesson(topicName, "Practice exercises");
        }
        
        if (lesson != null) {
            System.out.println("\n--- Starting Lesson ---");
            lesson.start();
            System.out.println("\nLesson completed!");
        }
    }
    
    public void changeStrategy() {
        System.out.println("\n=== CHANGE LEARNING STRATEGY ===");
        System.out.println("Current: " + currentStrategy);
        System.out.println("\n1 — Video Learning (watch video lessons)");
        System.out.println("2 — Audio Practice (listen to audio)");
        System.out.println("3 — Practice Lessons (exercises and practice)");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        if (choice == 0) return;
        
        LearningStrategy strategy = null;
        switch (choice) {
            case 1:
                strategy = new VideoLearning();
                currentStrategy = "Video Learning";
                break;
            case 2:
                strategy = new AudioPractice();
                currentStrategy = "Audio Practice";
                break;
            case 3:
                strategy = new ReadingArticles();
                currentStrategy = "Practice Lessons";
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
        
        learningApp.setLearningStrategy(strategy);
        System.out.println("\nStrategy changed to: " + currentStrategy);
        learningApp.startLearning("General learning session");
    }
    
    public void createLesson() {
        System.out.println("\n=== CREATE LESSON (Factory Pattern) ===");
        System.out.println("Factory Pattern creates different lesson types");
        
        System.out.println("\nSelect lesson type to create:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int typeChoice = getIntInput();
        if (typeChoice == 0) return;
        
        String factoryType = "";
        Lesson lesson = null;
        
        System.out.print("\nEnter topic name: ");
        scanner.nextLine();
        String topic = scanner.nextLine();
        
        System.out.print("Enter resource (URL/name): ");
        String resource = scanner.nextLine();
        
        switch (typeChoice) {
            case 1:
                LessonFactory videoFactory = new VideoLessonFactory();
                factoryType = "VideoLessonFactory";
                lesson = videoFactory.createLesson(topic, resource);
                break;
            case 2:
                LessonFactory audioFactory = new AudioLessonFactory();
                factoryType = "AudioLessonFactory";
                lesson = audioFactory.createLesson(topic, resource);
                break;
            case 3:
                LessonFactory practiceFactory = new PracticeLessonFactory();
                factoryType = "PracticeLessonFactory";
                lesson = practiceFactory.createLesson(topic, resource);
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
        
        System.out.println("\n--- Creating lesson using " + factoryType + " ---");
        
        createdLessons.add(lesson);
        
        System.out.println("Lesson created successfully!");
        System.out.println("Saved to 'My Created Lessons'");
        System.out.println("\n--- Starting created lesson ---");
        lesson.start();
    }
    
    public void viewCreatedLessons() {
        System.out.println("\n=== MY CREATED LESSONS ===");
        
        if (createdLessons.isEmpty()) {
            System.out.println("No lessons created yet.");
            System.out.println("Use 'Create Lesson (Factory Pattern)' to create your first lesson!");
            return;
        }
        
        System.out.println("Total lessons: " + createdLessons.size());
        System.out.println("\nYour lessons:");
        
        for (int i = 0; i < createdLessons.size(); i++) {
            Lesson lesson = createdLessons.get(i);
            String lessonType = lesson.getClass().getSimpleName();
            System.out.println((i + 1) + " — " + lesson.getTopic() + " [" + lessonType + "]");
        }
        
        System.out.println("\n1 — Start a lesson");
        System.out.println("2 — Delete a lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        if (choice == 0) return;
        
        switch (choice) {
            case 1:
                System.out.print("Enter lesson number to start: ");
                int startNum = getIntInput();
                if (startNum > 0 && startNum <= createdLessons.size()) {
                    System.out.println("\n--- Starting lesson ---");
                    createdLessons.get(startNum - 1).start();
                } else {
                    System.out.println("Invalid lesson number");
                }
                break;
            case 2:
                System.out.print("Enter lesson number to delete: ");
                int deleteNum = getIntInput();
                if (deleteNum > 0 && deleteNum <= createdLessons.size()) {
                    Lesson removed = createdLessons.remove(deleteNum - 1);
                    System.out.println("Deleted: " + removed.getTopic());
                } else {
                    System.out.println("Invalid lesson number");
                }
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    
    public void convertGrade() {
        System.out.println("\n=== GRADE CONVERSION (Adapter Pattern) ===");
        System.out.println("Convert percentage grades to letter grades");
        System.out.println("0 — Go back");
        System.out.print("\nEnter percentage (0-100): ");
        
        int input = getIntInput();
        if (input == 0) return;
        
        if (input < 0 || input > 100) {
            System.out.println("Invalid percentage");
            return;
        }
        
        PercentageGradingSystem percentageSystem = new PercentageGradingSystem();
        GradeAdapter adapter = new GradeAdapter(percentageSystem);
        
        System.out.println("\nPercentage grade: " + input + "%");
        System.out.println("Letter grade: " + adapter.getLetterGrade(input, 100));
    }
    
    public void decorateLesson() {
        System.out.println("\n=== LESSON DECORATORS ===");
        System.out.println("Add features to a lesson");
        
        System.out.println("\nSelect lesson type:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int lessonType = getIntInput();
        if (lessonType == 0) return;
        
        if (lessonType < 1 || lessonType > 3) {
            System.out.println("Invalid choice");
            return;
        }
        
        System.out.println("\nSelect your level:");
        System.out.println("1 — Beginner");
        System.out.println("2 — Intermediate");
        System.out.println("3 — Advanced");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int levelChoice = getIntInput();
        if (levelChoice == 0) return;
        
        Level level = Level.fromInt(levelChoice);
        if (level == null) {
            System.out.println("Invalid level");
            return;
        }
        
        List<String> topics = lessonDatabase.get(level.getName());
        System.out.println("\n=== " + level.getName().toUpperCase() + " TOPICS ===");
        for (int i = 0; i < topics.size(); i++) {
            String[] parts = topics.get(i).split("\\|");
            System.out.println((i + 1) + " — " + parts[0]);
        }
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int topicChoice = getIntInput();
        if (topicChoice == 0) return;
        
        if (topicChoice < 1 || topicChoice > topics.size()) {
            System.out.println("Invalid topic");
            return;
        }
        
        String[] topicData = topics.get(topicChoice - 1).split("\\|");
        String topicName = topicData[0];
        String resource = topicData[1];
        
        Lesson baseLesson = null;
        switch (lessonType) {
            case 1:
                baseLesson = new VideoLesson(topicName, resource);
                break;
            case 2:
                baseLesson = new AudioLesson(topicName, "Audio: " + resource.replace("youtu.be", "audio.com"));
                break;
            case 3:
                baseLesson = new PracticeLesson(topicName, "Practice exercises for " + topicName);
                break;
        }
        
        System.out.println("\n=== DECORATE LESSON ===");
        System.out.println("Lesson: " + topicName);
        System.out.println("\nAdd live chat feature?");
        System.out.println("1 — Yes, add chat");
        System.out.println("2 — No, start without chat");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int addChat = getIntInput();
        if (addChat == 0) return;
        
        Lesson finalLesson = baseLesson;
        if (addChat == 1) {
            finalLesson = new ChatDecorator(baseLesson, "Zoom Chat");
            System.out.println("\nChat decorator added!");
        }
        
        System.out.println("\n--- Starting Lesson ---");
        finalLesson.start();
    }
    
    public void manageCourseSubscriptions() {
        System.out.println("\n=== COURSE SUBSCRIPTION (Observer Pattern) ===");
        System.out.println("Total students: " + allStudents.size());
        System.out.println("Total courses: " + createdCourses.size());
        
        if (createdCourses.isEmpty()) {
            System.out.println("\nNo courses available yet.");
            System.out.println("Create a course first using 'Build Course Package (Builder Pattern)'!");
            return;
        }
        
        while (true) {
            System.out.println("\n1 — Add new student");
            System.out.println("2 — Remove student");
            System.out.println("3 — View all students");
            System.out.println("4 — Make course announcement");
            System.out.println("0 — Go back");
            System.out.print("\nChoice: ");
            
            int choice = getIntInput();
            
            if (choice == 0) break;
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    scanner.nextLine();
                    String studentName = scanner.nextLine();
                    
                    System.out.println("\nAvailable courses:");
                    for (int i = 0; i < createdCourses.size(); i++) {
                        System.out.println((i + 1) + " — " + createdCourses.get(i).getCourseName());
                    }
                    System.out.print("\nSelect course number: ");
                    int courseChoice = getIntInput();
                    
                    if (courseChoice > 0 && courseChoice <= createdCourses.size()) {
                        Student newStudent = new Student(studentName);
                        Course selectedCourse = createdCourses.get(courseChoice - 1);
                        selectedCourse.subscribe(newStudent);
                        allStudents.add(newStudent);
                    } else {
                        System.out.println("Invalid course choice");
                    }
                    break;
                case 2:
                    if (allStudents.isEmpty()) {
                        System.out.println("No students to remove");
                        break;
                    }
                    System.out.println("\nSelect student to remove:");
                    for (int i = 0; i < allStudents.size(); i++) {
                        System.out.println((i + 1) + " — " + allStudents.get(i).getStudentName());
                    }
                    System.out.print("\nChoice: ");
                    int removeChoice = getIntInput();
                    if (removeChoice > 0 && removeChoice <= allStudents.size()) {
                        Student toRemove = allStudents.remove(removeChoice - 1);
                        for (Course course : createdCourses) {
                            course.unsubscribe(toRemove);
                        }
                        System.out.println(toRemove.getStudentName() + " removed from all courses");
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                case 3:
                    if (allStudents.isEmpty()) {
                        System.out.println("No students yet");
                    } else {
                        System.out.println("\n=== ALL STUDENTS ===");
                        for (int i = 0; i < allStudents.size(); i++) {
                            System.out.println((i + 1) + ". " + allStudents.get(i).getStudentName());
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nSelect course for announcement:");
                    for (int i = 0; i < createdCourses.size(); i++) {
                        System.out.println((i + 1) + " — " + createdCourses.get(i).getCourseName());
                    }
                    System.out.print("\nChoice: ");
                    int announceCourse = getIntInput();
                    
                    if (announceCourse > 0 && announceCourse <= createdCourses.size()) {
                        System.out.print("Enter announcement: ");
                        scanner.nextLine();
                        String announcement = scanner.nextLine();
                        createdCourses.get(announceCourse - 1).announceEvent(announcement);
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
    public void buildCourse() {
        System.out.println("\n=== BUILD NEW COURSE (Builder Pattern) ===");
        
        System.out.print("Enter course name: ");
        scanner.nextLine();
        String courseName = scanner.nextLine();
        
        CourseBuilder builder = new CourseBuilder(courseName);
        
        System.out.println("\n=== COURSE BUILDER ===");
        System.out.println("Course name: " + courseName);
        
        while (true) {
            System.out.println("\nCurrent lessons in builder: " + builder.getLessons().size());
            System.out.println("\n1 — Add lesson to builder");
            System.out.println("2 — View builder lessons");
            System.out.println("3 — Build and save course");
            System.out.println("0 — Cancel");
            System.out.print("\nChoice: ");
            
            int choice = getIntInput();
            
            if (choice == 0) {
                System.out.println("Course creation cancelled");
                return;
            }
            
            switch (choice) {
                case 1:
                    Lesson lesson = selectLessonWithSource();
                    if (lesson != null) {
                        builder.addLesson(lesson);
                        System.out.println("Lesson added to builder!");
                    }
                    break;
                case 2:
                    List<Lesson> builderLessons = builder.getLessons();
                    if (builderLessons.isEmpty()) {
                        System.out.println("\nNo lessons in builder yet");
                    } else {
                        System.out.println("\n=== BUILDER LESSONS ===");
                        for (int i = 0; i < builderLessons.size(); i++) {
                            Lesson l = builderLessons.get(i);
                            System.out.println((i + 1) + ". " + l.getTopic() + " [" + l.getClass().getSimpleName() + "]");
                        }
                    }
                    break;
                case 3:
                    Course newCourse = builder.build();
                    List<Lesson> lessons = builder.getLessons();
                    createdCourses.add(newCourse);
                    courseLessons.put(newCourse, new ArrayList<>(lessons));
                    
                    for (Lesson l : lessons) {
                        newCourse.addNewLesson(l.getTopic() + " [" + l.getClass().getSimpleName() + "]");
                    }
                    
                    System.out.println("\n=== COURSE BUILT SUCCESSFULLY ===");
                    System.out.println("Course: " + newCourse.getCourseName());
                    System.out.println("Total lessons: " + lessons.size());
                    System.out.println("\nCourse is now available for:");
                    System.out.println("- Student subscriptions (Observer Pattern)");
                    System.out.println("- Course management (menu item 9)");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
    public void manageExistingCourses() {
        System.out.println("\n=== MANAGE EXISTING COURSES ===");
        
        if (createdCourses.isEmpty()) {
            System.out.println("No courses created yet.");
            System.out.println("Create a course first using 'Build New Course (Builder Pattern)'!");
            return;
        }
        
        System.out.println("\nYour courses:");
        for (int i = 0; i < createdCourses.size(); i++) {
            Course c = createdCourses.get(i);
            int lessonCount = courseLessons.get(c).size();
            System.out.println((i + 1) + " — " + c.getCourseName() + " (" + lessonCount + " lessons)");
        }
        
        System.out.print("\nSelect course number to manage: ");
        int courseChoice = getIntInput();
        
        if (courseChoice < 1 || courseChoice > createdCourses.size()) {
            System.out.println("Invalid course number");
            return;
        }
        
        Course selectedCourse = createdCourses.get(courseChoice - 1);
        manageSingleCourse(selectedCourse);
    }
    
    private void manageSingleCourse(Course course) {
        while (true) {
            System.out.println("\n=== COURSE: " + course.getCourseName() + " ===");
            System.out.println("Lessons: " + courseLessons.get(course).size());
            System.out.println("Subscribers: " + course.getObserversCount());
            
            System.out.println("\n1 — Add lesson");
            System.out.println("2 — View course lessons");
            System.out.println("3 — Remove lesson");
            System.out.println("4 — Delete entire course");
            System.out.println("0 — Go back");
            System.out.print("\nChoice: ");
            
            int choice = getIntInput();
            
            if (choice == 0) return;
            
            switch (choice) {
                case 1:
                    addLessonToCourse(course);
                    break;
                case 2:
                    viewCourseLessons(course);
                    break;
                case 3:
                    removeLessonFromCourse(course);
                    break;
                case 4:
                    System.out.print("Are you sure? (1=Yes, 2=No): ");
                    int confirm = getIntInput();
                    if (confirm == 1) {
                        createdCourses.remove(course);
                        courseLessons.remove(course);
                        System.out.println("Course deleted!");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
    private void addLessonToCourse(Course course) {
        Lesson lesson = selectLessonWithSource();
        if (lesson != null) {
            courseLessons.get(course).add(lesson);
            System.out.println("\nLesson added to course!");
            course.addNewLesson(lesson.getTopic() + " [" + lesson.getClass().getSimpleName() + "]");
        }
    }
    
    private Lesson selectLessonWithSource() {
        System.out.println("\nSelect lesson source:");
        System.out.println("1 — From database");
        System.out.println("2 — From My Created Lessons (" + createdLessons.size() + " lessons)");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        int sourceChoice = getIntInput();
        
        if (sourceChoice == 0) return null;
        
        if (sourceChoice == 1) {
            return selectLessonFromDatabase();
        } else if (sourceChoice == 2) {
            if (createdLessons.isEmpty()) {
                System.out.println("\nNo created lessons available!");
                return null;
            }
            
            System.out.println("\n=== MY CREATED LESSONS ===");
            for (int i = 0; i < createdLessons.size(); i++) {
                Lesson l = createdLessons.get(i);
                System.out.println((i + 1) + " — " + l.getTopic() + " [" + l.getClass().getSimpleName() + "]");
            }
            System.out.print("\nSelect lesson: ");
            int createdChoice = getIntInput();
            
            if (createdChoice > 0 && createdChoice <= createdLessons.size()) {
                return createdLessons.get(createdChoice - 1);
            }
        }
        return null;
    }
    
    private Lesson selectLessonFromDatabase() {
        System.out.println("\nSelect level:");
        System.out.println("1 — Beginner");
        System.out.println("2 — Intermediate");
        System.out.println("3 — Advanced");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        int levelChoice = getIntInput();
        
        if (levelChoice == 0) return null;
        
        Level level = Level.fromInt(levelChoice);
        if (level == null) {
            System.out.println("Invalid level");
            return null;
        }
        
        List<String> topics = lessonDatabase.get(level.getName());
        System.out.println("\n=== AVAILABLE " + level.getName().toUpperCase() + " LESSONS ===");
        for (int i = 0; i < topics.size(); i++) {
            String[] parts = topics.get(i).split("\\|");
            System.out.println((i + 1) + " — " + parts[0]);
        }
        System.out.println("0 — Go back");
        System.out.print("\nSelect lesson: ");
        int lessonChoice = getIntInput();
        
        if (lessonChoice == 0 || lessonChoice < 1 || lessonChoice > topics.size()) {
            return null;
        }
        
        String[] topicData = topics.get(lessonChoice - 1).split("\\|");
        String topicName = topicData[0];
        String resource = topicData[1];
        
        System.out.println("\nSelect lesson type:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        int typeChoice = getIntInput();
        
        switch (typeChoice) {
            case 1:
                return new VideoLesson(topicName, resource);
            case 2:
                return new AudioLesson(topicName, "Audio: " + resource.replace("youtu.be", "audio.com"));
            case 3:
                return new PracticeLesson(topicName, "Practice exercises");
            default:
                return null;
        }
    }
    
    private void viewCourseLessons(Course course) {
        List<Lesson> lessons = courseLessons.get(course);
        if (lessons.isEmpty()) {
            System.out.println("\nNo lessons in this course yet");
        } else {
            System.out.println("\n=== COURSE LESSONS ===");
            for (int i = 0; i < lessons.size(); i++) {
                Lesson l = lessons.get(i);
                System.out.println((i + 1) + ". " + l.getTopic() + " [" + l.getClass().getSimpleName() + "]");
            }
        }
    }
    
    private void removeLessonFromCourse(Course course) {
        List<Lesson> lessons = courseLessons.get(course);
        if (lessons.isEmpty()) {
            System.out.println("\nNo lessons to remove");
            return;
        }
        
        System.out.println("\nSelect lesson to remove:");
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i + 1) + " — " + lessons.get(i).getTopic());
        }
        System.out.print("\nChoice: ");
        int removeIdx = getIntInput();
        
        if (removeIdx > 0 && removeIdx <= lessons.size()) {
            Lesson removed = lessons.remove(removeIdx - 1);
            System.out.println("Removed: " + removed.getTopic());
        } else {
            System.out.println("Invalid choice");
        }
    }
    
    private int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
}
