import java.util.ArrayList;

public class BlackjackGame {
   private static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
   private static final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
   
   private String[] names;
   private int numberOfPlayers;

   private Deck deck = new Deck(ranks, suits);
   private ArrayList<Player> players = new ArrayList<>();
   private Dealer dealer = new Dealer(new Hand(), deck);   
   
   public BlackjackGame(String... names) {
      this.names = names;
      numberOfPlayers = names.length;
      resetPlayers();
      
      System.out.println("=======================================");
      System.out.println("          Aditya's Blackjack           ");
      System.out.println("=======================================");
   }
   
   public void resetPlayers() {
      for (int i = 0; i < numberOfPlayers; i++) {
         players.add(new Player(names[i], new Hand(), deck));
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
      
      return determineWinners();
   }
   
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
      }
      
      String winnersString = winnerNames.toString();
      return winnersString.substring(1, winnersString.length() - 1);
   }
}
