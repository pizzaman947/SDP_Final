package adapter.adapter;

import adapter.target_interface.LetterGradeSystem;
import adapter.adaptee.PercentageGradingSystem;

public class GradeAdapter implements LetterGradeSystem {
    private PercentageGradingSystem percentageSystem;
    
    public GradeAdapter(PercentageGradingSystem percentageSystem) {
        this.percentageSystem = percentageSystem;
    }
    
    @Override
    public String getLetterGrade(int correctAnswers, int totalQuestions) {
        double percentage = percentageSystem.calculateGrade(correctAnswers, totalQuestions);
        
        if (percentage >= 90) return "A (Excellent)";
        if (percentage >= 80) return "B (Very Good)";
        if (percentage >= 70) return "C (Good)";
        if (percentage >= 60) return "D (Satisfactory)";
        return "F (Failed)";
    }
    
    @Override
    public String getDescription() {
        return "Converts percentage grades to letter grades (A-F system)";
    }
    
    public double getPercentage(int correctAnswers, int totalQuestions) {
        return percentageSystem.calculateGrade(correctAnswers, totalQuestions);
    }
}
