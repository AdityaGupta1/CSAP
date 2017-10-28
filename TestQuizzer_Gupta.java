import java.util.Scanner;
import java.util.Arrays;

public class TestQuizzer_Gupta {
   public static void main(String[] args) {
      Quiz quiz = new Quiz("test quiz",
         new Question(QuestionType.MULTIPLE_CHOICE, "mc test?", "72", "0", "12", "72", "600"),
         new Question(QuestionType.SHORT_ANSWER, "sa test?", "73"),
         new Question(QuestionType.TRUE_FALSE, "true/false test?", "false"));
         
      quiz.ask();
      System.out.println("score: " + quiz.getScore());
   }
}

class Quiz {
   private String title;
   private Question[] questions;
   private int score = 0;
   
   public Quiz(String title, Question... questions) {
      this.title = title;
      this.questions = questions;
   }
   
   public void ask() {
      for (Question question : questions) {
         score += question.ask();
      }
   }
   
   public int getScore() {
      return score;
   }
}

class Question {
   private QuestionType type;
   private String question;
   private String[] answers;
   private String correctAnswer;
   private Scanner scanner = new Scanner(System.in);
   
   String[] multipleChoiceLetters = {"a", "b", "c", "d"};
   
   public Question(QuestionType type, String question, String correctAnswer, String... answers) {
      this.type = type;
      this.question = question;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
   }
   
   public int ask() {
      String answer = "";
   
      switch(type) {
         case MULTIPLE_CHOICE:
            System.out.println(question);
            for (int i = 0; i < 4; i++) {
               System.out.println(multipleChoiceLetters[i] + ") " + answers[i]);
            }
            answer = scanner.nextLine().toLowerCase();
            while (!Arrays.asList(multipleChoiceLetters).contains(answer)) {
               System.out.println("Answer with \"a\", \"b\", \"c\", or \"d\"!");
               answer = scanner.nextLine().toLowerCase();
            }
            break;
         case SHORT_ANSWER:
            System.out.println(question);
            answer = scanner.nextLine();
            break;
         case TRUE_FALSE:
            System.out.println("True or False: " + question);
            answer = scanner.nextLine().toLowerCase();
            while (!(answer.equals("true") || answer.equals("false"))) {
               System.out.println("Answer with \"true\" or \"false\"!");
               answer = scanner.nextLine().toLowerCase();
            }
            break;
      }
      
      if (answer.equals(correctAnswer)) {
         System.out.println("Correct!");
         return 1;
      } else {
         System.out.println("Incorrect!");
         return 0;
      }
      
      System.out.println();
   }
}

enum QuestionType {
   MULTIPLE_CHOICE, SHORT_ANSWER, TRUE_FALSE
}