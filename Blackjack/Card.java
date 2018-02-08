/**
 * A playing card
 */
public class Card {
   private String rank;
   private String suit;
   private int value;
   
   /**
    * Constructor that sets instance variables and determines the card's default value
    */
   public Card(String rank, String suit) {
      this.suit = suit;
      this.rank = rank;
      
      switch (rank) {
         case "A":
            value = 11;
            break;
         case "K":
         case "Q":
         case "J":
            value = 10;
            break;
         default:
            value = Integer.parseInt(rank);
      }
   }
   
   public String getRank() {
      return rank;
   }
   
   public String getSuit() {
      return suit;
   }
   
   public int getValue() {
      return value;
   }
   
   public void setValue(int value) {
      this.value = value;
   }
   
   @Override
   public String toString() {
      return rank + " of " + suit + " (" + value + " points)";
   }
}