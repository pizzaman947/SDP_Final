package view;

import facade.facade.LearningPlatformFacade;
import decorator.component.Lesson;
import observer.subject.Course;
import observer.observer.Student;
import util.Level;
import java.util.*;

public class ConsoleView {
    private Scanner scanner;
    private LearningPlatformFacade facade;
    
    public ConsoleView(LearningPlatformFacade facade) {
        this.scanner = new Scanner(System.in);
        this.facade = facade;
    }
    
    public void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Current Learning Mode: " + facade.getCurrentStrategyName());
        System.out.println("1 — Start Lesson (based on current mode)");
        System.out.println("2 — Change Learning Strategy");
        System.out.println("3 — Create Lesson (Factory Pattern)");
        System.out.println("4 — View My Created Lessons (" + facade.getCreatedLessonsCount() + " lessons)");
        System.out.println("5 — Grade Conversion (Adapter Pattern)");
        System.out.println("6 — Lesson Decorators (Add Features)");
        System.out.println("7 — Course Subscription (Observer Pattern)");
        System.out.println("8 — Build New Course (Builder Pattern)");
        System.out.println("9 — Manage Existing Courses (" + facade.getCreatedCoursesCount() + " courses)");
        System.out.println("10 — Exit");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1: showStartLesson(); break;
            case 2: showChangeStrategy(); break;
            case 3: showCreateLesson(); break;
            case 4: showViewCreatedLessons(); break;
            case 5: showConvertGrade(); break;
            case 6: showDecorateLesson(); break;
            case 7: showManageCourseSubscriptions(); break;
            case 8: showBuildCourse(); break;
            case 9: showManageExistingCourses(); break;
            case 10: 
                System.out.println("Goodbye!");
                scanner.close();
                System.exit(0);
                break;
            default: System.out.println("Invalid choice");
        }
    }
    
    private void showStartLesson() {
        System.out.println("\n=== START LESSON ===");
        System.out.println("Current Mode: " + facade.getCurrentStrategyName());
        
        Level level = selectLevel();
        if (level == null) return;
        
        String topic = selectTopic(level);
        if (topic == null) return;
        
        facade.startLesson(level, topic);
    }
    
    private void showChangeStrategy() {
        System.out.println("\n=== CHANGE LEARNING STRATEGY ===");
        System.out.println("Current: " + facade.getCurrentStrategyName());
        System.out.println("\n1 — Video Learning (watch video lessons)");
        System.out.println("2 — Audio Practice (listen to audio)");
        System.out.println("3 — Practice Lessons (exercises and practice)");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        if (choice == 0) return;
        
        if (choice >= 1 && choice <= 3) {
            facade.changeStrategy(choice);
            System.out.println("\nStrategy changed to: " + facade.getCurrentStrategyName());
        } else {
            System.out.println("Invalid choice");
        }
    }
    
    private void showCreateLesson() {
        System.out.println("\n=== CREATE LESSON (Factory Pattern) ===");
        System.out.println("Factory Pattern creates different lesson types");
        
        System.out.println("\nSelect lesson type to create:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int typeChoice = getIntInput();
        if (typeChoice == 0 || typeChoice < 1 || typeChoice > 3) return;
        
        System.out.print("\nEnter topic name: ");
        scanner.nextLine();
        String topic = scanner.nextLine();
        
        System.out.print("Enter resource (URL/name): ");
        String resource = scanner.nextLine();
        
        Lesson lesson = facade.createLesson(typeChoice, topic, resource);
        if (lesson != null) {
            System.out.println("\nLesson created successfully!");
            System.out.println("Saved to 'My Created Lessons'");
            System.out.println("\n--- Starting created lesson ---");
            lesson.start();
        }
    }
    
    private void showViewCreatedLessons() {
        System.out.println("\n=== MY CREATED LESSONS ===");
        
        List<Lesson> lessons = facade.getCreatedLessons();
        if (lessons.isEmpty()) {
            System.out.println("No lessons created yet.");
            System.out.println("Use 'Create Lesson (Factory Pattern)' to create your first lesson!");
            return;
        }
        
        System.out.println("Total lessons: " + lessons.size());
        System.out.println("\nYour lessons:");
        
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println((i + 1) + " — " + lesson.getTopic() + " [" + lesson.getClass().getSimpleName() + "]");
        }
        
        System.out.println("\n1 — Start a lesson");
        System.out.println("2 — Delete a lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        if (choice == 0) return;
        
        if (choice == 1) {
            System.out.print("Enter lesson number to start: ");
            int num = getIntInput();
            if (num > 0 && num <= lessons.size()) {
                System.out.println("\n--- Starting lesson ---");
                lessons.get(num - 1).start();
            }
        } else if (choice == 2) {
            System.out.print("Enter lesson number to delete: ");
            int num = getIntInput();
            Lesson removed = facade.deleteLesson(num - 1);
            if (removed != null) {
                System.out.println("Deleted: " + removed.getTopic());
            }
        }
    }
    
    private void showConvertGrade() {
        System.out.println("\n=== GRADE CONVERSION (Adapter Pattern) ===");
        System.out.println("Convert percentage grades to letter grades");
        System.out.println("0 — Go back");
        System.out.print("\nEnter percentage (0-100): ");
        
        int input = getIntInput();
        if (input == 0) return;
        
        if (input >= 0 && input <= 100) {
            String letterGrade = facade.convertGrade(input);
            System.out.println("\nPercentage grade: " + input + "%");
            System.out.println("Letter grade: " + letterGrade);
        } else {
            System.out.println("Invalid percentage");
        }
    }
    
    private void showDecorateLesson() {
        System.out.println("\n=== LESSON DECORATORS ===");
        System.out.println("Add features to a lesson");
        
        System.out.println("\nSelect lesson type:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int lessonType = getIntInput();
        if (lessonType == 0 || lessonType < 1 || lessonType > 3) return;
        
        Level level = selectLevel();
        if (level == null) return;
        
        String topic = selectTopic(level);
        if (topic == null) return;
        
        System.out.println("\n=== DECORATE LESSON ===");
        System.out.println("Lesson: " + topic);
        System.out.println("\nAdd live chat feature?");
        System.out.println("1 — Yes, add chat");
        System.out.println("2 — No, start without chat");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int addChat = getIntInput();
        if (addChat == 0) return;
        
        facade.decorateAndStartLesson(level, topic, lessonType, addChat == 1);
    }
    
    private void showManageCourseSubscriptions() {
        System.out.println("\n=== COURSE SUBSCRIPTION (Observer Pattern) ===");
        System.out.println("Total students: " + facade.getAllStudentsCount());
        System.out.println("Total courses: " + facade.getCreatedCoursesCount());
        
        if (facade.getCreatedCoursesCount() == 0) {
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
                case 1: handleAddStudent(); break;
                case 2: handleRemoveStudent(); break;
                case 3: handleViewStudents(); break;
                case 4: handleMakeAnnouncement(); break;
                default: System.out.println("Invalid choice");
            }
        }
    }
    
    private void handleAddStudent() {
        System.out.print("Enter student name: ");
        scanner.nextLine();
        String studentName = scanner.nextLine();
        
        List<Course> courses = facade.getCreatedCourses();
        System.out.println("\nAvailable courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + " — " + courses.get(i).getCourseName());
        }
        System.out.print("\nSelect course number: ");
        int courseChoice = getIntInput();
        
        facade.addStudentToCourse(studentName, courseChoice - 1);
    }
    
    private void handleRemoveStudent() {
        List<Student> students = facade.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students to remove");
            return;
        }
        
        System.out.println("\nSelect student to remove:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + " — " + students.get(i).getStudentName());
        }
        System.out.print("\nChoice: ");
        int removeChoice = getIntInput();
        
        facade.removeStudent(removeChoice - 1);
    }
    
    private void handleViewStudents() {
        List<Student> students = facade.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students yet");
        } else {
            System.out.println("\n=== ALL STUDENTS ===");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i).getStudentName());
            }
        }
    }
    
    private void handleMakeAnnouncement() {
        List<Course> courses = facade.getCreatedCourses();
        System.out.println("\nSelect course for announcement:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + " — " + courses.get(i).getCourseName());
        }
        System.out.print("\nChoice: ");
        int courseIdx = getIntInput();
        
        System.out.print("Enter announcement: ");
        scanner.nextLine();
        String announcement = scanner.nextLine();
        
        facade.makeCourseAnnouncement(courseIdx - 1, announcement);
    }
    
    private void showBuildCourse() {
        System.out.println("\n=== BUILD NEW COURSE (Builder Pattern) ===");
        
        System.out.print("Enter course name: ");
        scanner.nextLine();
        String courseName = scanner.nextLine();
        
        facade.startCourseBuilder(courseName);
        
        System.out.println("\n=== COURSE BUILDER ===");
        System.out.println("Course name: " + courseName);
        
        while (true) {
            System.out.println("\nCurrent lessons in builder: " + facade.getBuilderLessonsCount());
            System.out.println("\n1 — Add lesson to builder");
            System.out.println("2 — View builder lessons");
            System.out.println("3 — Build and save course");
            System.out.println("0 — Cancel");
            System.out.print("\nChoice: ");
            
            int choice = getIntInput();
            if (choice == 0) {
                System.out.println("Course creation cancelled");
                facade.cancelCourseBuilder();
                return;
            }
            
            switch (choice) {
                case 1: handleAddLessonToBuilder(); break;
                case 2: handleViewBuilderLessons(); break;
                case 3: 
                    if (handleFinishBuildingCourse()) return;
                    break;
                default: System.out.println("Invalid choice");
            }
        }
    }
    
    private void handleAddLessonToBuilder() {
        Lesson lesson = selectLessonWithSource();
        if (lesson != null) {
            facade.addLessonToBuilder(lesson);
            System.out.println("Lesson added to builder!");
        }
    }
    
    private void handleViewBuilderLessons() {
        List<Lesson> lessons = facade.getBuilderLessons();
        if (lessons.isEmpty()) {
            System.out.println("\nNo lessons in builder yet");
        } else {
            System.out.println("\n=== BUILDER LESSONS ===");
            for (int i = 0; i < lessons.size(); i++) {
                System.out.println((i + 1) + ". " + lessons.get(i).getTopic() + " [" + lessons.get(i).getClass().getSimpleName() + "]");
            }
        }
    }
    
    private boolean handleFinishBuildingCourse() {
        Course course = facade.finishCourseBuilder();
        System.out.println("\n=== COURSE BUILT SUCCESSFULLY ===");
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Total lessons: " + course.getLessons().size());
        System.out.println("\nCourse is now available for:");
        System.out.println("- Student subscriptions (Observer Pattern)");
        System.out.println("- Course management (menu item 9)");
        return true;
    }
    
    private void showManageExistingCourses() {
        System.out.println("\n=== MANAGE EXISTING COURSES ===");
        
        List<Course> courses = facade.getCreatedCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses created yet.");
            System.out.println("Create a course first using 'Build New Course (Builder Pattern)'!");
            return;
        }
        
        System.out.println("\nYour courses:");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            int lessonCount = facade.getCourseLessonsCount(c);
            System.out.println((i + 1) + " — " + c.getCourseName() + " (" + lessonCount + " lessons)");
        }
        
        System.out.print("\nSelect course number to manage: ");
        int courseChoice = getIntInput();
        
        if (courseChoice >= 1 && courseChoice <= courses.size()) {
            manageSingleCourse(courses.get(courseChoice - 1));
        } else {
            System.out.println("Invalid course number");
        }
    }
    
    private void manageSingleCourse(Course course) {
        while (true) {
            System.out.println("\n=== COURSE: " + course.getCourseName() + " ===");
            System.out.println("Lessons: " + facade.getCourseLessonsCount(course));
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
                case 1: handleAddLessonToCourse(course); break;
                case 2: handleViewCourseLessons(course); break;
                case 3: handleRemoveLessonFromCourse(course); break;
                case 4: 
                    if (handleDeleteCourse(course)) return;
                    break;
                default: System.out.println("Invalid choice");
            }
        }
    }
    
    private void handleAddLessonToCourse(Course course) {
        Lesson lesson = selectLessonWithSource();
        if (lesson != null) {
            facade.addLessonToCourse(course, lesson);
            System.out.println("\nLesson added to course!");
        }
    }
    
    private void handleViewCourseLessons(Course course) {
        List<Lesson> lessons = facade.getCourseLessons(course);
        if (lessons.isEmpty()) {
            System.out.println("\nNo lessons in this course yet");
        } else {
            System.out.println("\n=== COURSE LESSONS ===");
            for (int i = 0; i < lessons.size(); i++) {
                System.out.println((i + 1) + ". " + lessons.get(i).getTopic() + " [" + lessons.get(i).getClass().getSimpleName() + "]");
            }
        }
    }
    
    private void handleRemoveLessonFromCourse(Course course) {
        List<Lesson> lessons = facade.getCourseLessons(course);
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
        
        Lesson removed = facade.removeLessonFromCourse(course, removeIdx - 1);
        if (removed != null) {
            System.out.println("Removed: " + removed.getTopic());
        }
    }
    
    private boolean handleDeleteCourse(Course course) {
        System.out.print("Are you sure? (1=Yes, 2=No): ");
        int confirm = getIntInput();
        if (confirm == 1) {
            facade.deleteCourse(course);
            System.out.println("Course deleted!");
            return true;
        }
        return false;
    }
    
    private Lesson selectLessonWithSource() {
        System.out.println("\nSelect lesson source:");
        System.out.println("1 — From database");
        System.out.println("2 — From My Created Lessons (" + facade.getCreatedLessonsCount() + " lessons)");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        int sourceChoice = getIntInput();
        
        if (sourceChoice == 0) return null;
        
        if (sourceChoice == 1) {
            return selectLessonFromDatabase();
        } else if (sourceChoice == 2) {
            return selectFromCreatedLessons();
        }
        return null;
    }
    
    private Lesson selectLessonFromDatabase() {
        Level level = selectLevel();
        if (level == null) return null;
        
        String topic = selectTopic(level);
        if (topic == null) return null;
        
        System.out.println("\nSelect lesson type:");
        System.out.println("1 — Video Lesson");
        System.out.println("2 — Audio Lesson");
        System.out.println("3 — Practice Lesson");
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        int typeChoice = getIntInput();
        
        if (typeChoice >= 1 && typeChoice <= 3) {
            return facade.createLessonFromDatabase(level, topic, typeChoice);
        }
        return null;
    }
    
    private Lesson selectFromCreatedLessons() {
        List<Lesson> lessons = facade.getCreatedLessons();
        if (lessons.isEmpty()) {
            System.out.println("\nNo created lessons available!");
            return null;
        }
        
        System.out.println("\n=== MY CREATED LESSONS ===");
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i + 1) + " — " + lessons.get(i).getTopic() + " [" + lessons.get(i).getClass().getSimpleName() + "]");
        }
        System.out.print("\nSelect lesson: ");
        int choice = getIntInput();
        
        if (choice > 0 && choice <= lessons.size()) {
            return lessons.get(choice - 1);
        }
        return null;
    }
    
    private Level selectLevel() {
        System.out.println("\nSelect your level:");
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
        }
        return level;
    }
    
    private String selectTopic(Level level) {
        List<String> topics = facade.getTopicsForLevel(level);
        System.out.println("\n=== " + level.getName().toUpperCase() + " TOPICS ===");
        for (int i = 0; i < topics.size(); i++) {
            System.out.println((i + 1) + " — " + topics.get(i));
        }
        System.out.println("0 — Go back");
        System.out.print("\nChoice: ");
        
        int topicChoice = getIntInput();
        if (topicChoice == 0 || topicChoice < 1 || topicChoice > topics.size()) {
            return null;
        }
        
        return topics.get(topicChoice - 1);
    }
    
    private int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
    
    public void close() {
        scanner.close();
    }
}
