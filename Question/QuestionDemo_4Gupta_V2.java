import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
   This program shows a simple quiz with two choice questions.
*/
public class QuestionDemo_4Gupta_V2 {
   public static void main(String[] args) {
      ArrayList<Question> questions = new ArrayList<>();
      
      ChoiceQuestion first = new ChoiceQuestion();
      first.setText("What is the ideal gas equation?");
      first.addChoice("V = pi*r^2", false);
      first.addChoice("d = rt", false);
      first.addChoice("PV = nRT", true);
      first.addChoice("y = mx + b", false);
      questions.add(first);

      NumericQuestion second = new NumericQuestion();
      second.setText("What is the molar mass of hydrogen gas?");
      second.setAnswer(2.02);
      questions.add(second);

      FillInQuestion third = new FillInQuestion("Boyle's law inversely relates volume and _pressure_.");
      questions.add(third);

      ChoiceQuestion fourth = new ChoiceQuestion();
      fourth.setText("What is the unit of the ideal gas constant?");
      fourth.addChoice("mol*atm/L*K", false);
      fourth.addChoice("L*atm/mol*K", true);
      fourth.addChoice("mol*atm*L*K", false);
      fourth.addChoice("bar*mmHg/torr*kPa", false);
      questions.add(fourth);

      FillInQuestion fifth = new FillInQuestion("Charles's law directly relates temperature and _volume_.");
      questions.add(fifth);

      NumericQuestion sixth = new NumericQuestion();
      sixth.setText("What is the boiling temperature of water in kelvin?");
      sixth.setAnswer(373.15);
      questions.add(sixth);
      
      Collections.shuffle(questions);
      for (Question question : questions) {
         presentQuestion(question);
         System.out.println();
      }

      FillInQuestion seventh = new FillInQuestion("Charles's law directly relates temperature and _volume_.");
      System.out.println("fifth = seventh: " + fifth.equals(seventh));
      System.out.println("fifth = third: " + fifth.equals(third));
   }
   
   public static void presentQuestion(Question question) {
      question.display();
      System.out.print("your answer: ");
      Scanner in = new Scanner(System.in);
      String response = in.nextLine();
      System.out.println(question.checkAnswer(response) ? "correct" : "incorrect");
   }
}

class NumericQuestion extends Question {
   private double answer;
   
   public void setAnswer(double answer) {
      this.answer = answer;
   }
   
   @Override
   public void setAnswer(String answer) {
      Double.parseDouble(answer);
   }

   @Override
   public boolean checkAnswer(String response) {
      return Math.abs(Double.parseDouble(response) - answer) <= 0.01;
   }
}

class FillInQuestion extends Question {
   private String text;

   public FillInQuestion(String text) {
      String[] parts = text.split("_");
      setText(parts[0] + "____" + parts[2]);
      setAnswer(parts[1]);
      this.text = text;
   }
   
   @Override
   public boolean equals(Object other) {
      return (other instanceof FillInQuestion) && (((FillInQuestion) other).text.equals(this.text));
   }
}