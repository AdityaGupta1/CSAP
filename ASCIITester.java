// Loops-A7: ASCII Chart (PP)
// Aditya Gupta and Maxwell Jardetzky

/**
   Tests the ASCII class by printing a table with 4 columns
*/
public class ASCIITester {
   public static void main(String[] args) {
      ASCII ascii = new ASCII();
      ascii.printChart(4);
   }
}

/**
   Prints out an ASCII table with borders and stuff
   Doesn't use box-drawing characters because they don't render correctly (they show up as question marks)
*/
class ASCII {
   public void printChart(int numColumns) {
      String horizontalLine = "";
      for (int i = 0; i <= 9 * numColumns; i++) {
         horizontalLine += (i % 9 == 0 ? "+" : "-");
      }
   
      System.out.println(horizontalLine);
      for (int i = 32; i <= 123; i++) {
         System.out.printf("| %5s  " + ((i + 1) % numColumns == 0 ? "|\n" + horizontalLine + "\n" : ""), i + " " + (char)i);      
      }
   }
}