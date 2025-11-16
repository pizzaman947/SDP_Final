package adapter.adaptee;

public class PercentageGradingSystem {
    
    public double calculateGrade(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0.0;
        }
        return (correctAnswers * 100.0) / totalQuestions;
    }
    
    public String getResult(double percentage) {
        if (percentage >= 90) return "Excellent";
        if (percentage >= 75) return "Good";
        if (percentage >= 60) return "Satisfactory";
        return "Failed";
    }
}
