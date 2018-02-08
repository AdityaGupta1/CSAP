/**
 * The dealer in the blackjack game
 */
public class Dealer implements Person {
   private Hand hand;
   private Deck deck;
   private int wins = 0;

   public Dealer(Hand hand, Deck deck) {
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
      return "dealer";
   }
   
   @Override
   public boolean isBusted() {
      return getHandValue() > 21;
   }
   
   @Override
   public void win() {
      wins++;
   }
   
   @Override
   public int getWins() {
      return wins;
   }
   
   @Override
   public void reset() {
      hand = new Hand();
   }
   
   public boolean shouldDraw() {
      return hand.getValue() < 17;
   }
   
   public void display() {
      System.out.println(hand);
   }
   
   @Override
   public String toString() {
      return "dealer: " + getHandValue() + " points";
   }
}