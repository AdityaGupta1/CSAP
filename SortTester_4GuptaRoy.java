import java.util.ArrayList;
import java.util.Collections;

public class SortTester_4GuptaRoy {
   public static void main(String[] args) {
      ArrayList<Centipede> centipedes = new ArrayList<>();
      centipedes.add(new Centipede("SDOAJ", 10_000_000));
      centipedes.add(new Centipede("Foozeh", 69));
      centipedes.add(new Centipede("jgnibo", 500));
      centipedes.add(new Centipede("maxyj02", 0));
      centipedes.add(new Centipede("speedmc33", 33));
      centipedes.add(new Centipede("Lemon420", 420));
      centipedes.add(new Centipede("mubxtreme", 70));
      centipedes.add(new Centipede("xXx_epicness_xXx", 12));
      centipedes.add(new Centipede("shrushru30", 30));
      centipedes.add(new Centipede("TheBigSpooker", 70));

      Collections.sort(centipedes);
      
      for (Centipede centipede : centipedes) {
         System.out.println(centipede);
      }
   }
}

class Centipede implements Comparable {
   private final String name;
   private final int legs;
   
   public Centipede(String name, int legs) {
      this.name = name;
      this.legs = legs;
   }
   
   @Override
   public int compareTo(Object object) {
      Centipede other = (Centipede) object;
      
      if (this.legs == other.legs) {
         return this.name.toLowerCase().compareTo(other.name.toLowerCase());
      }
      
      return (this.legs > other.legs) ? 1 : -1;
   }
   
   @Override
   public String toString() {
      return this.name + " with " + this.legs + " legs";
   }
}