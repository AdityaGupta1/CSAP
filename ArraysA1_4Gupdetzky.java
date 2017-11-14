import java.util.Arrays;

public class ArraysA1_4Gupdetzky {
   static int[] createIncreasingArray(int length) {
      int[] array = new int[length];
      for (int i = 0; i < length; i++) {
         array[i] = i * 2;
      }
      return array;
   }
   
   static int sumArray(int[] array) {
      int sum = 0;
      for (int i : array) {
         sum += i;
      }
      return sum;
   }
   
   static void printMaxMin(int[] array) {
      int max = array[0];
      int min = array[0];
      for (int i : array) {
         max = Math.max(max, i);
         min = Math.min(min, i);
      }
      System.out.println("Maximum: " + max + ", Minimum: " + min);
   }

   public static void main(String[] args) {
      int[] array = createIncreasingArray(12);
      System.out.println(Arrays.toString(array));
      System.out.println("Sum of values in array: " + sumArray(array));
      printMaxMin(array);
   }
}