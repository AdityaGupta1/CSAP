import java.util.ArrayList;

class Card {
   private String rank;
   private String suit;
   private int value;
   
   public Card(String rank, String suit) {
      this.suit = suit;
      this.rank = rank;
      
      switch(rank) {
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
   
   @Override
   public String toString() {
      return rank + " of " + suit + " (" + value + " points)";
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
}

public class CardTester_4Gupta {
   static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   static final String[] suits = {"clubs", "diamons", "hearts", "spades"};
   
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      for (String rank : ranks) {
         for (String suit : suits) {
            cards.add(new Card(rank, suit));
         }
      }
      
      Card card1 = getRandomCard();
      System.out.println(card1);
      System.out.println(card1.getRank());
      System.out.println(card1.getSuit());
      System.out.println(card1.getValue());
      
      Card card2 = new Card("A", "clubs");
      System.out.println(card2);
      card2.setValue(1);
      System.out.println(card2);
      
   }
   
   static Card getRandomCard() {
      return cards.get((int) Math.floor(Math.random() * cards.size()));
   }
}