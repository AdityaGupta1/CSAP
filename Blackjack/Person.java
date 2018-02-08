/**
 * Interface so that Player and Dealer can be operated on together
 */
public interface Person {
   public int getHandValue();
   public boolean isBusted();
   public String getName();
   public void win();
   public int getWins();
   public void reset();
}