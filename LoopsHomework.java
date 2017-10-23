// Loops-A1: Ch 6 Book
// Aditya Gupta

import java.util.Scanner;

public class LoopsHomework {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
   
      System.out.println("---------------------------------\nE6.2a\n---------------------------------\n");
      
      int evenNumber = 2;
      int evenNumberSum = 0;
      
      while (evenNumber <= 100) {
         evenNumberSum += evenNumber;
         evenNumber += 2;
      }
      
      System.out.println("Sum of even numbers between 2 and 100 (inclusive): " + evenNumberSum);
      
      System.out.println("\n---------------------------------\nE6.2d\n---------------------------------\n");
      
      System.out.print("Enter value of a: ");
      int a = scanner.nextInt();
      System.out.print("Enter value of b: ");
      int b = scanner.nextInt();
      int oddNumberSum = 0;
      
      while (a <= b) {
         if (a % 2 == 1) {
            oddNumberSum += a;
         }
         
         a++;
      }
      
      System.out.println("Sum of odd numbers between a and b (inclusive): " + oddNumberSum);
      
      System.out.println("\n---------------------------------\nE6.3a\nEnd of input: -9999\n---------------------------------\n");
      
      int input = scanner.nextInt();
      int min = input;
      int max = input;
      
      while (input != -9999) {
         min = Math.min(min, input);
         max = Math.max(max, input);
         input = scanner.nextInt();
      }
      
      System.out.println("\nLowest value: " + min);
      System.out.println("Highest value: " + max);
      
      System.out.println("\n---------------------------------\nE6.3c\nEnd of input: -9999\n---------------------------------\n");
      
      int cumulativeSum = 0;
      input = scanner.nextInt();
      
      while (input != -9999) {
         cumulativeSum += input;
         System.out.println("Cumulative sum: " + cumulativeSum);
         input = scanner.nextInt();
      }
      
      System.out.println("\n---------------------------------\nE6.6a\nEnd of input: -9999\n---------------------------------\n");
      
      DataSet dataSet = new DataSet();
      double inputDouble = scanner.nextDouble();
      
      while (inputDouble != -9999) {
         dataSet.add(inputDouble);
         inputDouble = scanner.nextDouble();
      }

      System.out.println("\nAverage: " + dataSet.getAverage());
      System.out.println("Lowest value: " + dataSet.getMin());
      System.out.println("Highest value: " + dataSet.getMax());
      System.out.println("Range: " + dataSet.getRange());
   }
}

class DataSet {
   double total = 0;
   double count = 0;
   boolean initialized = false;
   double min;
   double max;
   
   public void add(double newDouble) {
      total += newDouble;
      count++;
      
      if (!initialized) {
         min = newDouble;
         max = newDouble;
         initialized = true;
      } else {
         min = Math.min(min, newDouble);
         max = Math.max(max, newDouble);
      }
   }
   
   public double getAverage() {
      return total / count;
   }
   
   public double getMin() {
      return min;
   }
   
   public double getMax() {
      return max;
   }
   
   public double getRange() {
      return max - min;
   }
}