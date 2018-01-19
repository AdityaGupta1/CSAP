import java.util.List;
import java.util.ArrayList;

public class MinTester_GuptaHu {
   public static void main(String[] args) {
      ArrayList<Integer> ints = new ArrayList<Integer>();
      ints.add(-2);
      ints.add(12);
      ints.add(22);
      ints.add(17);
      ints.add(19);
      ints.add(-10);
      ints.add(-2);
      System.out.println("Test 1: 2nd smallest is " + find2ndMin(ints));
      
      ints = new ArrayList<Integer>();
      ints.add(-2);
      ints.add(12);
      ints.add(22);
      ints.add(17);
      ints.add(19);
      ints.add(-1);
      ints.add(-2);
      System.out.println("Test 2: 2nd smallest is " + find2ndMin(ints));
      
      ints = new ArrayList<Integer>();
      ints.add(-2);
      ints.add(12);
      ints.add(22);
      ints.add(17);
      ints.add(19);
      ints.add(-1);
      ints.add(999999);
      System.out.println("Test 3: 2nd smallest is " + find2ndMin(ints));
   }
   
   static int find2ndMin(List<Integer> ints) {
      int min = Math.max(ints.get(0), ints.get(1));
      int secondMin = Math.min(ints.get(0), ints.get(1));
      
      for (int number : ints) {
         if (number < min) {
            secondMin = min;
            min = number;
         } else if (number < secondMin) {
            secondMin = number;
         }
      }
      
      return secondMin;
   }
}