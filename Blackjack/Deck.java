import java.util.ArrayList;

/**
 * A deck of cards
 */
public class Deck {
   private ArrayList<Card> cards = new ArrayList<Card>();
   
   /**
    * Updates the hand's value, taking aces into account
    *
    * @param  ranks  the possible card ranks
    * @param  suits  the possible card suits
    */
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
   
   /**
    * Shuffles the deck using the "efficient selection shuffle"
    */
   public void shuffle() {
      for (int i = cards.size() - 1; i > 0; i--) {
         int j = (int) (Math.random() * (i));
         cards.set(j, cards.set(i, cards.get(j)));
      }
   }
   
   public boolean isEmpty() {
      return cards.size() == 0;
   }
   
   /**
    * Deals a card
    *
    * @return  the card dealt
    */
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