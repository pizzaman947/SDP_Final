package main;

import facade.LearningPlatformFacade;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static LearningPlatformFacade facade = new LearningPlatformFacade(scanner);
    
    public static void main(String[] args) {
        System.out.println("Welcome to Language Learning Platform");
        System.out.println("=====================================\n");
        
        try {
            while (true) {
                showMainMenu();
            }
        } finally {
            scanner.close();
        }
    }
    
    private static void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Current Learning Mode: " + facade.getCurrentStrategy());
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
            case 0:
                System.out.println("Already at main menu");
                break;
            case 1:
                facade.startLessonBasedOnStrategy();
                break;
            case 2:
                facade.changeStrategy();
                break;
            case 3:
                facade.createLesson();
                break;
            case 4:
                facade.viewCreatedLessons();
                break;
            case 5:
                facade.convertGrade();
                break;
            case 6:
                facade.decorateLesson();
                break;
            case 7:
                facade.manageCourseSubscriptions();
                break;
            case 8:
                facade.buildCourse();
                break;
            case 9:
                facade.manageExistingCourses();
                break;
            case 10:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    
    private static int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
