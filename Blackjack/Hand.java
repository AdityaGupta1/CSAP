import java.util.ArrayList;

public class Hand {
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
      
      handString += (value == 21 ? "blackjack" : (value + " points")) + (isBusted() ? ", busted" : "");
      
      return handString;
   }
}
