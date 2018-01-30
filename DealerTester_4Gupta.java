import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DealerTester_4Gupta {
   static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      for (int i = 0; i < 3; i++) {
         System.out.println("----------------------");
         System.out.println("Round " + (i + 1));
         System.out.println("----------------------");
         
         Dealer dealer = new Dealer(new Deck(ranks, suits), new Hand());
      
         while (dealer.shouldDraw()) {
            dealer.draw();
            dealer.display();
            System.out.println();
         }
         
         dealer.displayAll();
         System.out.println();
      }
   }
}

class Dealer {
   private Deck deck;
   private Hand hand;
   
   public Dealer(Deck deck, Hand hand) {
      this.deck = deck;
      this.hand = hand;
   }
   
   public void draw() {
      hand.addCard(deck.deal());
   }
   
   public boolean shouldDraw() {
      return hand.getValue() <= 16 || (hand.getValue() == 17 && hand.getAces() > 0);
   }
   
   public int getHandValue() {
      return hand.getValue();
   }
   
   public void display() {
      for (int i = 1; i < hand.getSize(); i++) {
         System.out.println(hand.getCards().get(i) + ((i == hand.getSize() - 1) ? "" : ","));
      }
   }
   
   public void displayAll() {
      System.out.println(hand);
   }
}

class Player {
   private Deck deck;
   private Hand hand;
   
   private boolean done = false;
   
   public Player(Deck deck, Hand hand) {
      this.deck = deck;
      this.hand = hand;
   }
   
   public int getHandValue() {
      return hand.getValue();
   }
   
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
   
   public boolean isDone() {
      return done;
   }
   
   public Hand getHand() {
      return hand;
   }
   
   public void displayHand() {
      System.out.println(hand);
   }
}

class Hand {
   private ArrayList<Card> cards = new ArrayList<Card>();
   private int value = 0;
   private int aces = 0;
   
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
      aces = 0;
      
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
   
   public int getAces() {
      return aces;
   }
   
   public ArrayList<Card> getCards() {
      return cards;
   }
   
   public int getSize() {
      return cards.size();
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