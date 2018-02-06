import java.util.ArrayList;

/**
 * Tests the blackjack game
 * 
 * Rules from https://www.bicyclecards.com/how-to-play/blackjack/
 *
 * @author Aditya Gupta
 */
public class BlackjackTester_4Gupta {
   static ArrayList<Card> cards = new ArrayList<Card>();
   
   public static void main(String[] args) {
      BlackjackGame game = new BlackjackGame(new String[]{"Sam", "Todd"});
      System.out.println("\nWinners: " + game.playGame());   
   }
}