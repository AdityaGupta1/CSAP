import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {
   private static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   private static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   private String[] names;
   private int numberOfPlayers;

   private Deck deck = new Deck(ranks, suits);
   private ArrayList<Player> players = new ArrayList<>();
   private Dealer dealer = new Dealer(new Hand(), deck);   
   
   /**
    * Constructor that sets instance variables, gets player names, and prints out the greeting banner
    */
   public BlackjackGame() {
      System.out.println("=======================================");
      System.out.println("          Aditya's Blackjack           ");
      System.out.println("=======================================\n");
      
      ArrayList<String> names = getNames();
      this.names = names.toArray(new String[names.size()]);
      numberOfPlayers = names.size();
   
      for (int i = 0; i < numberOfPlayers; i++) {
         players.add(new Player(this.names[i], new Hand(), deck));
      }
   }
   
   public void start() {
      do {
         System.out.println("\nWinners: " + playGame());
      } while (getPlayAgain());
   }
   
   /**
    * Gets a list of names to use as players
    *
    * @return  the list of names
    */
   public ArrayList<String> getNames() {
      ArrayList<String> names = new ArrayList<>();
      
      Scanner reader = new Scanner(System.in);
   
      System.out.print("Enter names (type \"stop\" to stop): ");
      String input = reader.next();
      while (!(input.equalsIgnoreCase("stop"))) {
         names.add(input);
         input = reader.next();
      }
      
      return names;
   }
   
   /**
    * Reads whether the user wants to play again (yes/no) from console
    *
    * @return  the user's choice (true/false)
    */
   public boolean getPlayAgain() {
      Scanner reader = new Scanner(System.in);
      
      System.out.print("\nPlay again? ");
      String input = reader.next().toLowerCase();
      while (!(input.equals("yes") || input.equals("no"))) {
         System.out.println("Please type either \"yes\" or \"no\"!");
         System.out.print("Play again? ");
         input = reader.next().toLowerCase();
      }
      
      return input.equals("yes");
   }
   
   /**
    * Resets the players and the dealer
    */
   public void resetPeople() {
      for (Player player : players) {
         player.reset();
      }
      
      dealer.reset();
   }
   
   public void resetDeck() {
      deck = new Deck(ranks, suits);
   }
   
   /**
    * Deals cards to the players and the dealer
    */
   public void dealCards() {
      for (int i = 0; i < 2; i++) {
         for (Player player : players) {
            player.draw();
         }
         
         dealer.draw();
      }
   }
   
   /**
    * Plays a round
    *
    * @return the winners of the round and the total win counts of each person
    */
   public String playGame() {
      deck = new Deck(ranks, suits);
      resetPeople();
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
         dealer.display();
         System.out.println();
         dealer.draw();
      }
      
      System.out.println("The dealer's final hand:");
      dealer.display();
      
      return determineWinners();
   }
   
   /**
    * Determines the winners of the round
    *
    * @return the winners of the round and the total win counts of each person
    */
   public String determineWinners() {
      ArrayList<Person> winners = new ArrayList<>();
      
      ArrayList<Person> people = new ArrayList<>();
      people.addAll(players);
      
      if (!dealer.isBusted()) {
         people.add(dealer);
         winners.add(new Player("nobody", new Hand(), null));
      }
         
      loop:
      for (Person person : people) {
         if (dealer.isBusted()) {
            if (!person.isBusted()) {
               winners.add(person);
               continue loop;
            }
         }
      
         if (person.isBusted()) {
            continue loop;
         }
         
         if (person.getHandValue() > winners.get(0).getHandValue()) {
            winners.clear();
            winners.add(person);
         } else if (person.getHandValue() == winners.get(0).getHandValue()) {
            winners.add(person);
         }
      }
      
      ArrayList<String> winnerNames = new ArrayList<>();
      
      for (Person person : winners) {
         winnerNames.add(person.getName());
         person.win();
      }
      
      String winnersString = winnerNames.toString();
      winnersString = winnersString.substring(1, winnersString.length() - 1) + "\n";
      
      if (dealer.isBusted()) {
         people.add(dealer);
      }
      
      for (Person person : people) {
         winnersString += "\n" + person.getName() + ": " + person.getWins() + " win" + (person.getWins() == 1 ? "" : "s");
      }
      return winnersString;
   }
}