public class Counter_4Gupta {
   public static void main(String args[]) {
      Counter counter = new Counter();
      counter.click();
      System.out.println(counter.getCount());
   }
}

class Counter {
   int count = 0;
   
   public void click() {
      count++;
   }
   
   public int getCount() {
      return count;
   }
}