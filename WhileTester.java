import java.util.Scanner;

public class WhileTester {
   public static void main(String[] args) {
      int count = 10;
      
      while (count > 0) {
         System.out.println(count);
         count--;
      }
      
      Scanner scanner = new Scanner(System.in);
      int integer = scanner.nextInt();
      int sum = 0;
      
      
      while (integer > 0) {
         sum += integer;
         integer = scanner.nextInt();
      }
      
      System.out.println(sum);
   }
}