// Loops-A7: ASCII Chart (PP)
// Aditya Gupta and Maxwell Jardetzky

/**
   Tests the ASCII class.
*/
public class ASCIITester {
   public static void main(String[] args) {
      ASCII ascii = new ASCII();
      ascii.printChart(4);
   }
}

/**
   Prints out an ASCII table, code golf style.
*/
class ASCII {
   public void printChart(int numColumns) {
      for (int i = 32; i < 129; i++) {
         System.out.printf("%5s" + ((i - numColumns + 1) % numColumns == 0 ? "\n" : "  "), i + " " + (char)i);      
      }
   }
}