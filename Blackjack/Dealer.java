public class Dealer implements Person {
   private Hand hand;
   private Deck deck;

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
   
   public boolean shouldDraw() {
      return hand.getValue() < 17;
   }
   
   public void display() {
      for (int i = 1; i < hand.getSize(); i++) {
         System.out.println(hand.getCards().get(i) + ((i == hand.getSize() - 1) ? "" : ","));
      }
   }
   
   public void displayAll() {
      System.out.println(hand);
   }
   
   @Override
   public String toString() {
      return "dealer: " + getHandValue() + " points";
   }
}