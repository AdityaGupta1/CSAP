import java.util.ArrayList;

/**
 * A hand of cards that has a point value
 */
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
   
   /**
    * Updates the hand's value, taking aces into account
    */
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
   
   /**
    * Creates a string for displaying the hand
    *
    * @return  a multi-line string for displaying the hand
    */
   @Override
   public String toString() {
      String handString = "";
      
      for (int i = 0; i < cards.size(); i++) {
         handString += cards.get(i) + ",\n";
      }
      
      handString += ((value == 21 && cards.size() == 2) ? "blackjack" : (value + " points")) + (isBusted() ? ", busted" : "");
      
      return handString;
   }
}
