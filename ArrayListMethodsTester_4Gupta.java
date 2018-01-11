import java.util.ArrayList;

public class ArrayListMethodsTester_4Gupta {
   public static void main(String[] args) {
      int[] data = {3, 6, 9, 2, 1, 4, 7, 5};
      ArrayListMethods methods = new ArrayListMethods(convertArrayToArrayList(data));
      methods.moveEvensToFront();
      methods.display();
   }
   
   static ArrayList<Integer> convertArrayToArrayList(int[] array) {
      ArrayList<Integer> ints = new ArrayList<Integer>();
      
      for (int number : array) {
         ints.add(number);
      }
      
      return ints;
   }
}

class ArrayListMethods {
   private ArrayList<Integer> ints;
   
   public ArrayListMethods(ArrayList<Integer> ints) {
      this.ints = ints;
   }
   
   public void display() {
      System.out.println(ints);
   }
   
   public void swapFirstAndLast() {
      int temp = ints.get(0);
      ints.set(0, ints.get(ints.size() - 1));
      ints.set(ints.size() - 1, temp);
   }
   
   public void shiftRight() {
      ints.add(ints.get(ints.size() - 1));
      
      for (int i = ints.size() - 1; i > 1; i--) {
         ints.set(i, ints.get(i - 1));
      }
      
      ints.remove(0);
   }
   
   public void replaceEvensWithZero() {
      for (int i = 0; i < ints.size(); i++) {
         if (ints.get(i) % 2 == 0) {
            ints.set(i, 0);
         }
      }
   }
   
   public void replaceWithLargerNeighbor() {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      temp.add(ints[0]);
      temp.add(ints[ints.length - 1]);
      
      for (int i = 1; i < ints.length - 1; i++) {
      }
   }
   
   public void removeMiddle() {
      if (ints.size() % 2 == 0) {
         ints.remove((ints.size() / 2) - 1);
         ints.remove((ints.size() / 2) - 1);
      } else {
         ints.remove(ints.size() / 2);
      }
   }
   
   public void moveEvensToFront() {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      
      for (int number : ints) {
         if (number % 2 == 0) {
            temp.add(0, number);
         } else {
            temp.add(number);
         }
      }
      
      ints = temp;
   }
}