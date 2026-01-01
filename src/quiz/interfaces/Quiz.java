package quiz.interfaces;

public interface Quiz {

    void startQuiz();

    void submitAnswer(int questionIndex, String answer);

    void showResults();
}

