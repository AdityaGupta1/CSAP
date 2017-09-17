/**
Simulates two populations of roaches, one large and one small, as they breed and get sprayed
*/
public class RoachSimulation_4Gupta {
   // main method
   public static void main(String[] args) {
      // small roach population
      RoachPopulation aFewRoaches = new RoachPopulation(10);
      // large roach population
      RoachPopulation lotsOfRoaches = new RoachPopulation(14298);
      
      // initial roach populations
      System.out.println("small roach population: " + aFewRoaches.getRoaches() + " roaches");
      System.out.println("large roach population: " + lotsOfRoaches.getRoaches() + " roaches");
      
      // empty newline
      System.out.println();
      
      // breeds and sprays small roach population
      aFewRoaches.breed();
      System.out.println("small roach population: " + aFewRoaches.getRoaches() + " roaches");
      aFewRoaches.spray(10);
      System.out.println("small roach population: " + aFewRoaches.getRoaches() + " roaches");
      aFewRoaches.breed();
      System.out.println("small roach population: " + aFewRoaches.getRoaches() + " roaches");
      aFewRoaches.spray(45.2);
      System.out.println("small roach population: " + aFewRoaches.getRoaches() + " roaches");
      
      // empty newline
      System.out.println();
      
      // breeds and sprays large roach population
      lotsOfRoaches.spray(12.3);
      System.out.println("large roach population: " + lotsOfRoaches.getRoaches() + " roaches");
      lotsOfRoaches.spray(2.3);
      System.out.println("large roach population: " + lotsOfRoaches.getRoaches() + " roaches");
      lotsOfRoaches.breed();
      System.out.println("large roach population: " + lotsOfRoaches.getRoaches() + " roaches");
   }
}

/**
A population of roaches that can breed and be sprayed
*/
class RoachPopulation {
   // instance variable
   // roach population
   int roaches;
   
   // constructor
   // set roach population on initialization
   public RoachPopulation(int roaches) {
      this.roaches = roaches;
   }
   
   // mutator method
   // simulates roaches breeding, and doubles the population
   public void breed() {
      roaches *= 2;
   }
   
   // mutator method
   // sprays the given percent of the roaches and kills them
   public void spray(double percent) {
      roaches -= (roaches * (percent / 100));
   }
   
   // accessor method
   // returns the current roach population
   public int getRoaches() {
      return roaches;
   }
}