public class TestQuizzer_Gupta {
   public static void main(String[] args) {
   }
}

class Quiz {
   private String title;
   private Question[] questions;
   private int score;
   
   public Quiz(String title, Question[] questions, int score) {
      this.title = title;
      this.questions = questions;
      this.score = score;
   }
}

class Question {
   private QuestionType type;
   private String question;
   private String[] answers;
   private String correctAnswer;
   private Scanner scanner = new Scanner(System.in);
   
   public Question(QuestionType type, String question, String[] answers, String correctAnswer) {
      this.type = type;
      this.question = question;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
   }
   
   public void ask() {
      String answer;
   
      switch(type) {
         case QuestionType.MULTIPLE_CHOICE:
            break;
         case QuestionType.SHORT_ANSWER:
            System.out.println(question);
            answer = scanner.nextLine();
            break;
         case QuestionType.TRUE_FALSE:
            System.out.println("True or False: " + question);
            answer = scanner.nextLine().toLowerCase();
            while (!(answer.equals("true") || answer.equals("false"))) {
               System.out.println("Answer with \"true\" or \"false\"!");
               answer = scanner.nextLine().toLowerCase();
            }
            break;
      }
   }
}

enum QuestionType {
   MULTIPLE_CHOICE, SHORT_ANSWER, TRUE_FALSE
}