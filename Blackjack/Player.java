import java.util.Scanner;

public class Player implements Person {
   private String name;
   private Hand hand;
   private Deck deck;
   
   private boolean done = false;
   
   public Player(String name, Hand hand, Deck deck) {
      this.name = name;
      this.hand = hand;
      this.deck = deck;
   }
   
   public void draw() {
      hand.addCard(deck.deal());
   }
   
   @Override
   public int getHandValue() {
      return hand.getValue();
   }
   
   @Override
   public String getName() {
      return name;
   }
   
   @Override
   public boolean isBusted() {
      return getHandValue() > 21;
   }
   
   public String getUserChoice() {
      Scanner reader = new Scanner(System.in);
      
      System.out.print("Hit or stand? ");
      String input = reader.next().toLowerCase();
      while (!(input.equals("hit") || input.equals("stand"))) {
         System.out.println("Please type either \"hit\" or \"stand\"!");
         System.out.print("Hit or stand? ");
         input = reader.next().toLowerCase();
      }
      
      return input.toLowerCase();
   }
   
   public void doRound() {
      String choice = getUserChoice();
      
      if (choice.equals("hit")) {
         draw();
      } else {
         done = true;
      }
      
      if (hand.isBusted()) {
         done = true;
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
   
   @Override
   public String toString() {
      return name + ": " + getHandValue() + " points";
   }
}
