package util;

public enum Level {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");
    
    private final String name;
    
    Level(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static Level fromInt(int choice) {
        switch (choice) {
            case 1: return BEGINNER;
            case 2: return INTERMEDIATE;
            case 3: return ADVANCED;
            default: return null;
        }
    }
}
