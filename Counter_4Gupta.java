public class Counter_4Gupta {
   public static void main(String args[]) {
      Counter counter = new Counter();
      counter.click(10);
      Counter counter2 = new Counter();
      counter2.click(10);
      System.out.println(counter.equals(counter2));
   }
}

class Counter {
   private int count = 0;

   public void click() {
      count++;
   }
   
   public void click(int times) {
      for (int time = 0; time < times; time++) {
         click();
      }
   }

   public int getCount() {
      return count;
   }
   
   @Override
   public String toString() {
      return "Count: " + count;
   }
   
   @Override
   public boolean equals(Object otherObject) {
      return (otherObject instanceof Counter) && this.getCount() == ((Counter) otherObject).count;
   }
}