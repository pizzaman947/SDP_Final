package adapter;

public interface LetterGradeSystem {
    String getLetterGrade(int correctAnswers, int totalQuestions);
    String getDescription();
}
