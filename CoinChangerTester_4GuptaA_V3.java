// Aditya Gupta
// TypesA2: Bell Market Coin Changer

import java.util.Scanner;

/**
 Creates a CoinChanger, performs some transactions, and prints out the results
*/
public class CoinChangerTester_4GuptaA_V3 {
   public static void main(String[] args) {
      printOpeningCredits();
   
      CoinChanger bellMarketCoinChanger = new CoinChanger(172, 156, 134, 173);
      System.out.println(bellMarketCoinChanger);
      Scanner scanner = new Scanner(System.in);
      
      for (int i = 0; i < 3; i++) {
         System.out.println();
         System.out.print("Enter value of total sale: $");
         double totalSale = scanner.nextDouble();
         System.out.print("Enter amount given: $");
         double amountGiven = scanner.nextDouble();
         System.out.println();
         bellMarketCoinChanger.purchase(totalSale, amountGiven);
         System.out.print("\n\n\n");
         System.out.println(bellMarketCoinChanger);
      }
   }
   
   private static void printOpeningCredits() {
      System.out.println("  |_ ");
      System.out.println(" /|  ");
      System.out.println("| |  ");
      System.out.println(" \\|  ");
      System.out.println("  |\\ ");
      System.out.println("  | |");
      System.out.println(" _|/ ");
      System.out.println("  |  \n");
      System.out.println("$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$");
      System.out.println("|                                                     |");
      System.out.println("| $$$  Bell Market Coin Changer  $$$                  |");
      System.out.println("| $$$  by Aditya Gupta           $$$                  |");
      System.out.println("|                                                     |");
      System.out.println("$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$==$");
      System.out.print("\n\n\n");

   }
}

/**
 Simulates a coin changer that gives change in quarters, dimes, nickels, and pennies
*/
class CoinChanger {
   int quarters;
   int dimes;
   int nickels;
   int pennies;

   public CoinChanger(int quarters, int dimes, int nickels, int pennies) {
      this.quarters = quarters;
      this.dimes = dimes;
      this.nickels = nickels;
      this.pennies = pennies;
   }
   
   public void purchase(double totalSale, double amountGiven) {
      System.out.printf("Performing transaction - total sale: $%.2f, amount given: $%.2f\n", totalSale, amountGiven);
   
      int remainingChange = (int) Math.round((amountGiven - totalSale) * 100);
      int quartersToGive = remainingChange / 25;
      remainingChange %= 25;
      int dimesToGive = remainingChange / 10;
      remainingChange %= 10;
      int nickelsToGive = remainingChange / 5;
      remainingChange %= 5;
      int penniesToGive = remainingChange;
      
      quarters -= quartersToGive;
      dimes -= dimesToGive;
      nickels -= nickelsToGive;
      pennies -= penniesToGive;
      
      System.out.printf("Change to give: $%.2f\n", amountGiven - totalSale);
      System.out.println("Gave " + 
         quartersToGive + " quarters, " + 
         dimesToGive + " dimes, " + 
         nickelsToGive + " nickels, and " + 
         penniesToGive + " pennies in change");
   }
   
   @Override
   public String toString() {
      return "The coin changer has " + 
         quarters + " quarters, " + 
         dimes + " dimes, " + 
         nickels + " nickels, and " + 
         pennies + " pennies";

   }
}