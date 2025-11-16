package adapter.target_interface;

public interface LetterGradeSystem {
    String getLetterGrade(int correctAnswers, int totalQuestions);
    String getDescription();
}
