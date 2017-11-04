// LoopsA7: Quizzer
// Aditya Gupta, period 4

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
Creates a quiz about chemistry
*/
public class TestQuizzer_Gupta {
   public static void main(String[] args) {
      Quiz quiz = new Quiz("Chemistry quiz",
         new Question(QuestionType.MULTIPLE_CHOICE, 
            "How many carbon atoms are in a molecule of glucose?", "6", "12", "3", "4"),
         new Question(QuestionType.SHORT_ANSWER, 
            "How many neutrons does an atom of carbon-14 have?", "8"),
         new Question(QuestionType.TRUE_FALSE, 
            "Isotopes of an element differ in their number of protons", "false"),
         new Question(QuestionType.TRUE_FALSE, 
            "Electrons have a negative charge", "true"),
         new Question(QuestionType.MULTIPLE_CHOICE, 
            "Which of the following elements' isotopes are all radioactive?", "technetium", "hydrogen", "neon", "tungsten"),
         new Question(QuestionType.MULTIPLE_CHOICE, 
            "Which of the following elements is a noble gas?", "helium", "carbon", "oxygen", "nitrogen"),
         new Question(QuestionType.MULTIPLE_CHOICE, 
            "Which of the following scientists does not have an element named after him/her?", "Isaac Newton", "Nicolaus Copernicus", "Albert Einstein", "Marie Curie"),
         new Question(QuestionType.SHORT_ANSWER, 
            "Which element is present in all organic compunds?", "carbon"));
         
      quiz.askQuestions();
      int score = quiz.getScore();
      System.out.println("Final score: " + score + "/" + quiz.getNumberOfQuestions());
      
      switch (score) {
         case 5:
            System.out.println("Could be better, but not too bad overall!");
            break;
         case 6:
            System.out.println("Not bad, only 2 missed!");
            break;
         case 7:
            System.out.println("Hey, that's pretty good!");
            break;
         case 8:
            System.out.println("Wow! You're a chemistry master!");
            break;
         default:
            System.out.println("Looks like someone needs to study more!");
            break;
      }
   }
}

/**
Represents a quiz, with a title, questions, and a score
*/
class Quiz {
   private String title;
   private List<Question> questions;
   private int score = 0;
   
   public Quiz(String title, Question... questions) {
      this.title = title;
      this.questions = Arrays.asList(questions);
      Collections.shuffle(this.questions);
   }
   
   public void askQuestions() {
      System.out.println("=================================");
      System.out.println(title);
      System.out.println("=================================");
      System.out.println();
   
      for (Question question : questions) {
         score += question.ask();
      }
   }
   
   public int getScore() {
      return score;
   }
   
   public int getNumberOfQuestions() {
      return questions.size();
   }
}

/**
Represents a question that can be either multiple choice, short answer, or true/false
*/
class Question {
   private QuestionType type;
   private String question;
   private List<String> answers;
   private String correctAnswer;
   private Scanner scanner = new Scanner(System.in);
   
   List<String> multipleChoiceLetters = Arrays.asList(new String[] {"a", "b", "c", "d"});
   
   public Question(QuestionType type, String question, String... answers) {
      this.type = type;
      this.question = question;
      correctAnswer = answers[0].toLowerCase();
      this.answers = Arrays.asList(answers);
      Collections.shuffle(this.answers);
   }
   
   public int ask() {
      String answer = "";
   
      switch(type) {
         case MULTIPLE_CHOICE:
            System.out.println(question);
            
            int correctAnswerIndex = 0;
            for (int i = 0; i < 4; i++) {
               System.out.println(multipleChoiceLetters.get(i) + ") " + answers.get(i));
               if (answers.get(i).toLowerCase().equals(correctAnswer)) {
                  correctAnswerIndex = i;
               }
            }
            correctAnswer = multipleChoiceLetters.get(correctAnswerIndex);
            
            answer = scanner.nextLine().toLowerCase();
            while (!multipleChoiceLetters.contains(answer)) {
               System.out.println("Answer with \"a\", \"b\", \"c\", or \"d\"!");
               answer = scanner.nextLine().toLowerCase();
            }
            break;
         case SHORT_ANSWER:
            System.out.println(question);
            answer = scanner.nextLine().toLowerCase();
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
      
      boolean correct = answer.equals(correctAnswer);
      if (correct) {
         System.out.println("Correct!");
      } else {
         System.out.println("Incorrect!");
      }
      System.out.println();
      
      return correct ? 1 : 0;
   }
}

/**
The three possible question types
*/
enum QuestionType {
   MULTIPLE_CHOICE, SHORT_ANSWER, TRUE_FALSE
}