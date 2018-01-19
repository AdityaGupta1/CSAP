import java.util.ArrayList;
import java.util.Collections;

class Card {
   private String rank;
   private String suit;
   private int value;
   
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

class Deck {
   private ArrayList<Card> cards = new ArrayList<Card>();
   
   public Deck(String[] ranks, String[] suits) {
      for (String rank : ranks) {
         for (String suit : suits) {
            cards.add(new Card(rank, suit));
         }
      }
      
      shuffle();
   }
   
   public int getSize() {
      return cards.size();
   }
   
   public void shuffle() {
      Collections.shuffle(cards);
   }
   
   public boolean isEmpty() {
      return getSize() == 0;
   }
   
   public Card deal() {
      if (isEmpty()) {
         return null;
      }
   
      return cards.remove(0);
   }
}

public class DeckTester_4Gupta {
   static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      Deck deck = new Deck(ranks, suits);
      
      while (deck.getSize() > 0) {
         System.out.println(deck.deal());
      }
   }
}