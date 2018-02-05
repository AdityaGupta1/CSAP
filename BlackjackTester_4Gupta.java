import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackjackTester_4Gupta {
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      BlackjackGame game = new BlackjackGame(new String[]{"Sam", "Todd", "James", "Bob"});
      System.out.println("\nWinners: " + game.playGame());   
   }
}

class BlackjackGame {
   private static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   private static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   private String[] names;
   private int numberOfPlayers;

   private Deck deck = new Deck(ranks, suits);
   private ArrayList<Player> players = new ArrayList<>();
   private Dealer dealer = new Dealer(deck, new Hand());   
   
   public BlackjackGame(String... names) {
      this.names = names;
      numberOfPlayers = names.length;
      resetPlayers();
   }
   
   public void resetPlayers() {
      for (int i = 0; i < numberOfPlayers; i++) {
         players.add(new Player(names[i], deck, new Hand()));
      }
   }
   
   public void resetDeck() {
      deck = new Deck(ranks, suits);
   }
   
   public void dealCards() {
      for (int i = 0; i < 2; i++) {
         for (Player player : players) {
            player.draw();
         }
         
         dealer.draw();
      }
   }
   
   public String playGame() {
      dealCards();
   
      for (Player player : players) {
         System.out.println("\n[It is " + player.getName() + "'s turn]");
      
         while (!player.isDone()) {
            System.out.println();
            player.displayHand();
            System.out.println();
            player.doRound();
         }
         
         System.out.println("\n" + player.getName() + "'s final hand:");
         player.displayHand();
      }
      
      System.out.println("\n[It is the dealer's turn]\n");
      
      while (dealer.shouldDraw()) {
         dealer.displayAll();
         System.out.println();
         dealer.draw();
      }
      
      System.out.println("The dealer's final hand:");
      dealer.displayAll();
      
      ArrayList<Integer> scores = new ArrayList<>();
      
      for (Player player : players) {
         scores.add(player.getHandValue());
      }
      scores.add(dealer.getHandValue());
      
      ArrayList<Integer> unsortedScores = new ArrayList<>(scores);
      
      Collections.sort(scores);
      
      int maxScore = 0;
      ArrayList<String> winners = new ArrayList<>();
      
      loop:
      for (int i = 0; i < scores.size(); i++) {
         if (scores.get(i) > 21) {
            continue loop;
         }
         
         if (maxScore == 0) {
            maxScore = scores.get(i);
            System.out.println(maxScore);
         }
         
         if (scores.get(i) == maxScore) {
            if (i == unsortedScores.size() - 1) {
               winners.add("dealer");
            } else {
               winners.add(players.get(i).getName());
            }
         }
      }
      
      if (maxScore == 0) {
         winners.add("nobody");
      }
      
      String winnersString = winners.toString();
      return winnersString.substring(1, winnersString.length() - 1);
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
      return hand.getValue() < 17 || (hand.getValue() == 17 && hand.getAces() > 0);
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
   private String name;
   private Deck deck;
   private Hand hand;
   
   private boolean done = false;
   
   public Player(String name, Deck deck, Hand hand) {
      this.name = name;
      this.deck = deck;
      this.hand = hand;
   }
   
   public int getHandValue() {
      return hand.getValue();
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
   
   public void draw() {
      hand.addCard(deck.deal());
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
   
   public String getName() {
      return name;
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