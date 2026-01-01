package quiz;

import quiz.interfaces.Quiz;
import quiz.models.Question;
import quiz.services.QuizService;

public class Main {
    public static void main(String[] args) {

        Question[] questions = {
                new Question(
                        "What is Java?",
                        new String[]{"Programming Language", "OS", "Browser", "Database"},
                        "Programming Language"
                ),
                new Question(
                        "Which company owns Java?",
                        new String[]{"Google", "Microsoft", "Oracle", "Apple"},
                        "Oracle"
                )
        };

        Quiz quiz = new QuizService(questions);
        quiz.startQuiz();
    }
}

