import java.util.ArrayList;

public class ArrayListMethodsTester_4Gupta {
   /**
    * Tests the methods of ArrayListMethods and prints out the results
    */
   public static void main(String[] args) {
      int[] data1 = {3, 6, 9, 2, 1, 4, 7, 5, 8};
      int[] data2 = {4, 6, 3, 8, 1, 2, 7, 0, 4, 1, 7, 9};
      int[] data3 = {1, 2, 3, 5, 6, 7, 8, 9};
      int[] data4 = {9, 2, 6, 3, 4, 1, 7, 1, 6, 1};
      
      ArrayListMethods methods1 = new ArrayListMethods(convertArrayToArrayList(data1));
      ArrayListMethods methods2 = new ArrayListMethods(convertArrayToArrayList(data2));
      ArrayListMethods methods3 = new ArrayListMethods(convertArrayToArrayList(data3));
      ArrayListMethods methods4 = new ArrayListMethods(convertArrayToArrayList(data4));
      
      System.out.println("arraylist 1: " + methods1);
      System.out.println("arraylist 2: " + methods2);
      System.out.println("arraylist 3: " + methods3);
      System.out.println("arraylist 4: " + methods4);
      
      methods1.swapFirstAndLast();
      System.out.println("arraylist 1, with first and last swapped: " + methods1);
      
      methods1.shiftRight();
      System.out.println("above arraylist, shifted right: " + methods1);
      
      methods1.replaceEvensWithZero();
      System.out.println("above arraylist, with even numbers replaced with 0: " + methods1);
      
      methods2.replaceWithLargerNeighbor();
      System.out.println("arraylist 2, with each number (except the first and last) replaced by the larger of its two neighbors: " + methods2);
      
      methods2.removeMiddle();
      System.out.println("above arraylist, with the middle one or two elements removed: " + methods2);
      
      methods2.moveEvensToFront();
      System.out.println("above arraylist, with even numbers moved to the front: " + methods2);
      
      System.out.println("second largest number in arraylist 3: " + methods3.getSecondLargest());
      
      System.out.println("arraylist 3 " + (methods3.isSortedAscending() ? "is" : "is not") + " sorted in ascending order");
      
      System.out.println("arraylist 4 " + (methods4.containsAdjacentDuplicate() ? "cotains" : "does not contain") + " two adjacent duplicate numbers");
      
      System.out.println("arraylist 4 " + (methods4.containsDuplicate() ? "cotains" : "does not contain") + " two duplicate numbers");
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
   
   /** 
    * Class constructor
    *
    * @param ints the ArrayList to perform operations on
    */
   public ArrayListMethods(ArrayList<Integer> ints) {
      this.ints = ints;
   }
   
   @Override
   public String toString() {
      return ints.toString();
   }
   
   /**
    * Swaps the first and last elements of the ArrayList
    */
   public void swapFirstAndLast() {
      int temp = ints.get(0);
      ints.set(0, ints.get(ints.size() - 1));
      ints.set(ints.size() - 1, temp);
   }
   
   /**
    * Replaces all even elements in the ArrayList with 0
    */
   public void shiftRight() {
      ints.add(0, ints.get(ints.size() - 1));
      ints.remove(ints.size() - 1);
   }
   
   /**
    * Shifts all elements in the ArrayList to the right by one and moves the last element into the first position
    */
   public void replaceEvensWithZero() {
      for (int i = 0; i < ints.size(); i++) {
         if (ints.get(i) % 2 == 0) {
            ints.set(i, 0);
         }
      }
   }
   
   /**
    * Replaces each element in the ArrayList except the first and last by the larger of its two neighbors
    */
   public void replaceWithLargerNeighbor() {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      temp.add(ints.get(0));
      temp.add(ints.get(ints.size() - 1));
      
      for (int i = 1; i < ints.size() - 1; i++) {
         temp.add(temp.size() - 1, Math.max(ints.get(i + 1), ints.get(i - 1)));
      }
      
      ints = temp;
   }
   
   /**
    * Removes the middle element of the ArrayList if the array length is odd, or the middle two elements of the ArrayList if the length is even
    */
   public void removeMiddle() {
      if (ints.size() % 2 == 0) {
         ints.remove((ints.size() / 2));
         ints.remove((ints.size() / 2));
      } else {
         ints.remove(ints.size() / 2);
      }
   }
   
   /**
    * Moves all even elements in the ArrayList to the front, otherwise preserving the order of the elements
    */
   public void moveEvensToFront() {
      ArrayList<Integer> evens = new ArrayList<Integer>();
      ArrayList<Integer> odds = new ArrayList<Integer>();
      
      for (int number : ints) {
         if (number % 2 == 0) {
            evens.add(number);
         } else {
            odds.add(number);
         }
      }
      
      ints.clear();
      
      for (int number : evens) {
         ints.add(number);
      }
      
      for (int number : odds) {
         ints.add(number);
      }
   }
   
   /**
    * Finds the second-largest number in the ArrayList
    *
    * @return the second largest number in the ArrayList
    */
   public int getSecondLargest() {
      int max = Math.max(ints.get(0), ints.get(1));
      int secondMax = Math.min(ints.get(0), ints.get(1));
      
      for (int i = 2; i < ints.size(); i++) {
         if (ints.get(i) > max) {
            secondMax = max;
            max = ints.get(i);
         }
      }
      
      return secondMax;
   }
   
   /**
    * Checks if the ArrayList is sorted in ascending order
    *
    * @return true if the ArrayList is sorted in ascending order, false if it is not
    */
   public boolean isSortedAscending() {
      for (int i = 0; i < ints.size() - 1; i++) {
         if (ints.get(i + 1) < ints.get(i)) {
            return false;
         }
      }
      
      return true;
   }
   
   /**
    * Checks if the ArrayList contains adjacent duplicate elements
    *
    * @return true if the ArrayList contains adjacent duplicate elements, false if it does not
    */
   public boolean containsAdjacentDuplicate() {
      for (int i = 0; i < ints.size() - 1; i++) {
         if (ints.get(i + 1) == ints.get(i)) {
            return true;
         }
      }
      
      return false;
   }
   
   /**
    * Checks if the ArrayList contains duplicate elements, regardless of whether they are adjacent or not
    *
    * @return true if the ArrayList contains duplicate elements, false if it does not
    */
   public boolean containsDuplicate() {
      for (int i = 0; i < ints.size(); i++) {
         for (int j = 0; j < i; j++) {
            if (ints.get(i) == ints.get(j)) {
               return true;
            }
         }
      }
      
      return false;
   }
}