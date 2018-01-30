import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tests out a blackjack (21) player
 * The player can hit until busted and can choose to stand at any time
 *
 * @author Aditya Gupta
 */
public class PlayerTester_4Gupta {
   static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      Deck deck = new Deck(ranks, suits);
      Hand hand = new Hand();
      Player player = new Player(deck, hand);
      Player player2 = new Player(deck, hand);
      
      while (!player.getHand().isBusted() && !player.isDone()) {
         player.doRound();
      }
   }
}

/**
 * Represents a player playing blackjack
 *
 * @author Aditya Gupta
 */
class Player {
   private Deck deck;
   private Hand hand;
   
   private boolean done = false;
   
   public Player(Deck deck, Hand hand) {
      this.deck = deck;
      this.hand = hand;
   }
   
   /**
    * Returns the value of the player's hand
    *
    * @return the value of the player's hand
    */
   public int getHandValue() {
      return hand.getValue();
   }
   
   /**
    * Gets the user's choice (hit or stand) through console input
    *
    * @return the user's choice
    */
   public String getUserChoice() {
      Scanner reader = new Scanner(System.in);
      
      System.out.print("Hit or stand? ");
      String input = reader.next();
      while (!(input.toLowerCase().equals("hit") || input.toLowerCase().equals("stand"))) {
         System.out.println("Please type either \"hit\" or \"stand\"!");
         System.out.print("Hit or stand? ");
         input = reader.next();
      }
      
      return input.toLowerCase();
   }
   
   /**
    * Completes one round of blackjack
    */
   public void doRound() {
      if (done) {
         return;
      }
   
      String choice = getUserChoice();
      
      if (choice.equals("hit")) {
         hand.addCard(deck.deal());
         System.out.println();
         displayHand();
         System.out.println();
      } else {
         done = true;
         System.out.println();
         displayHand();
         System.out.println();
      }
   }
   
   /**
    * Returns whether the player is "done" (whether the player has decided to stand)
    *
    * @return whether the player is "done"
    */
   public boolean isDone() {
      return done;
   }
   
   /**
    * Returns the player's hand
    *
    * @return the player's hand
    */
   public Hand getHand() {
      return hand;
   }
   
   /**
    * Displays the player's hand
    */
   public void displayHand() {
      System.out.println(hand);
   }
}

class Hand {
   private ArrayList<Card> cards = new ArrayList<Card>();
   private int value = 0;
   
   public Hand(Card... cards) {
      for (Card card : cards) {
         this.cards.add(card);
      }
      
      updateValue();
   }
   
   public void addCard(Card card) {
      cards.add(card);
      updateValue();
   }
   
   private void updateValue() {
      value = 0;
      int aces = 0;
      
      for (Card card : cards) {
         aces += card.getRank().equals("A") ? 1 : 0;
         value += card.getValue();
      }
      
      if (value <= 21 || aces == 0) {
         return;
      }
      
      for (Card card : cards) {
         if (card.getValue() != 11) {
            continue;
         }
         
         card.setValue(1);
         value -= 10;
         
         if (value < 21) {
            break;
         }
      }
   }
   
   public int getValue() {
      return value;
   }
   
   public boolean isBusted() {
      return value > 21;
   }
   
   public ArrayList<Card> getCards() {
      return cards;
   }
   
   @Override
   public String toString() {
      String handString = "";
      
      for (int i = 0; i < cards.size(); i++) {
         handString += cards.get(i) + ",\n";
      }
      
      handString += value + " points, " + (isBusted() ? "" : "not ") + "busted";
      
      return handString;
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
      for (int i = cards.size() - 1; i > 0; i--) {
         int j = (int) (Math.random() * (i));
         cards.set(j, cards.set(i, cards.get(j)));
      }
   }
   
   public boolean isEmpty() {
      return cards.size() == 0;
   }
   
   public Card deal() {
      if (isEmpty()) {
         return null;
      }
   
      return cards.remove(0);
   }
   
   public ArrayList<Card> getCards() {
      return cards;
   }
}

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