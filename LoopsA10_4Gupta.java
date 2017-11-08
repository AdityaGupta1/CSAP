// Loops-A10: Ch 6 Book
// Aditya Gupta

/*

---------- R6.1b ----------

=====
*
====
**
===
***
==
****
=

---------- R6.2b ----------

10
12
14
16
and so on

---------- R6.4a ----------

for (int i = 0; i * i < n; i++) {
   System.out.println(i * i);
}

---------- R6.4c ----------

for (int i = 0; Math.pow(i, 2) < n; i++) {
   System.out.println(Math.pow(i, 2));
}

---------- R6.10 ----------

An off-by-one erorr is when a loop runs one time more or less than it should.
These errors showed up a lot when I was making the ASCII chart with my partner, Maxwell Jardetsky.

---------- R6.13a ----------

10

---------- R6.13c ----------

10

---------- R6.13f ----------

10

---------- R6.15 ----------

print out header
do following from 0 to 100, in increments of 10:
   print out value
   print out middle bar
   print out value converted to farenheit (multiply by 1.8 and add 32)

---------- R6.16 ----------

iterate through string separated by spaces:
   skip first two (those are the first and last name)
   skip last one (sentinel of -1)
   add these all to a count
divide count by number of scores (number of values minus 3)

+-------+-------+
| i     | score |
+-------+-------+
| 0     | 0     |
+-------+-------+
| 1     | 0     |
+-------+-------+
| 2     | 94    |
+-------+-------+
| 3     | 82.5  |
+-------+-------+
| 4     | 83.67 |
+-------+-------+
| 5     | 86.5  |
+-------+-------+
| 6     | 86.5  |
+-------+-------+

---------- R6.21a ----------

2 4 7 11 16

---------- R6.21c ----------

10 5

*/

import java.util.Scanner;
import java.lang.StringBuilder;

public class LoopsA10_4Gupta {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Input: ");
      String input = scanner.nextLine();
      System.out.println();
      
      System.out.println("E6.9\n-----");
      for (char c : input.toCharArray()) {
         System.out.println(c);
      }
      System.out.println();
      
      System.out.println("E6.10\n-----");
      System.out.println(new StringBuilder(input).reverse().toString());
   }
}