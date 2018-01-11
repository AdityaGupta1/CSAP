import java.util.ArrayList;

public class ArrayListDemo {

   public static void main(String[] args) {
      ArrayList<String> aList = new ArrayList<String>();
      
      aList.add("do");
      aList.add("u");
      aList.add("kno");
      aList.add("da");
      aList.add("wae");
      
      for (String word : aList) {
         System.out.println(word);
      }
      
      aList.remove(1);
      aList.set(3, "uganda");
      aList.add(1, "knuckles");
      
      System.out.println();
      
      for (String word : aList) {
         System.out.println(word);
      }
      
      System.out.println();
      
      System.out.println("max: " + findMax(aList));
   }
   
   static String findMax(ArrayList<String> names) {
      String max = names.get(0);
      
      for (String string : names) {
         int compare = max.compareTo(string);
         
         // if not less than 0, "max" is either greater than or equal to "string"
         if (compare < 0) {
            max = string;
         }
      }
      
      return max;
   }
}