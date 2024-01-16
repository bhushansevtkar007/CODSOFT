import java.util.*;

class QuizQuestion {
    String question;
    List<String> options;
    int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
}

class Quiz {
    List<QuizQuestion> questions;
    int currentQuestionIndex;
    int score;
    Timer timer;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                showNextQuestion();
            }
        }, 0, 15000); // 15 seconds timer for each question

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.question);
            for (int i = 0; i < currentQuestion.options.size(); i++) {
                System.out.println((i + 1) + ". " + currentQuestion.options.get(i));
            }

            System.out.print("Your choice (1-" + currentQuestion.options.size() + "): ");
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();

            if (userChoice == currentQuestion.correctOptionIndex + 1) {
                System.out.println("Correct! Well done!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + (currentQuestion.correctOptionIndex + 1));
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                timer.cancel();
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        System.out.println("\nTime's up! Moving to the next question.");
                        showNextQuestion();
                    }
                }, 0, 15000); // 15 seconds timer for each question
            } else {
                endQuiz();
            }
        }
    }

    private void endQuiz() {
        timer.cancel();
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());

        // Display a summary of correct/incorrect answers
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + (scoredCorrect(q) ? "Correct" : "Incorrect"));
        }
    }

    private boolean scoredCorrect(QuizQuestion question) {
        return question.correctOptionIndex + 1 == userAnswers.get(question);
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Sample quiz questions
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?",
                Arrays.asList("Berlin", "Paris", "Madrid", "Rome"), 1));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"), 0));
        quizQuestions.add(new QuizQuestion("What is the largest mammal in the world?",
                Arrays.asList("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1));

        // Create a quiz with the sample questions
        Quiz quiz = new Quiz(quizQuestions);

        // Start the quiz
        quiz.startQuiz();
    }
}
