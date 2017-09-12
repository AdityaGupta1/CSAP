// Aditya Gupta and Maxwell Jardetzky
// StormPair 4
// ClassA2: Die Class-print-PP

public class DieTester_4GuptaJardetzky {
   public static void main(String[] args) {
      Die die1 = new Die();
      Die die2 = new Die();
      die1.print();
      die2.print();
   }
}

class Die {
   private int faceValue;
   
   public Die() {
      roll();
   }
   
   public void roll() {
      faceValue = (int)(Math.random() * 6) + 1;
   }
   
   public String toString() {
      return Integer.toString(faceValue);
   }
   
   public void print() {
      System.out.println(toString());
   }
}