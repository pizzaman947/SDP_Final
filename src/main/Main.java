package main;

import view.ConsoleView;
import facade.facade.LearningPlatformFacade;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Language Learning Platform");
        System.out.println("=====================================\n");
        
        LearningPlatformFacade facade = new LearningPlatformFacade();
        ConsoleView view = new ConsoleView(facade);
        
        while (true) {
            view.showMainMenu();
        }
    }
}
