import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class ZooTester_4Gupta {
   public static void main(String[] args) {
      ArrayList<Animal> animals = new ArrayList<>();
      animals.add(new Bird("Foozeh", "purple"));
      animals.add(new Lion());
      animals.add(new Fish("SDOAJ", 300));
      animals.add(new LeopardGecko());
      animals.add(new TokayGecko());
      
      for (Animal animal : animals) {
         animal.speak();
      }
      
      System.out.println();
      
      Bird bird = (Bird) animals.get(0);
      bird.molt();
      
      Lion lion = (Lion) animals.get(1);
      System.out.println(lion);
      lion.growMane();
      System.out.println(lion);
      
      Fish fish = (Fish) animals.get(2);
      fish.bubble();
      
      LeopardGecko leopardGecko = (LeopardGecko) animals.get(3);
      TokayGecko tokayGecko = (TokayGecko) animals.get(4);
      System.out.println(leopardGecko + " and " + tokayGecko + " bred to create:\n\t" + leopardGecko.breed(tokayGecko));
   }
}

class Animal {
   protected String name;
   
   public Animal(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public void speak() {}
   
   @Override
   public String toString() {
      return "animal named " + name;
   }
}

class Bird extends Animal {
   private String featherColor;

   public Bird(String name, String featherColor) {
      super(name);
      this.featherColor = featherColor;
   }
   
   public Bird(String name) {
      this(name, "green");
   }
   
   public Bird() {
      this("parrot from minecraft");
   }

   @Override
   public void speak() {
      System.out.println("chirp");
   }
   
   public void molt() {
      System.out.println("bird " + name + " molts " + featherColor + " feathers");
   }
   
   @Override
   public String toString() {
      return "bird named " + name + " with " + featherColor + " feathers";
   }
}

class Lion extends Animal {
   private int maneSize;

   public Lion(String name, int maneSize) {
      super(name);
      this.maneSize = maneSize;
   }

   public Lion(String name) {
      this(name, 5);
   }
   
   public Lion() {
      this("Simba");
   }
   
   @Override
   public void speak() {
      System.out.println("roar");
   }
   
   public void growMane() {
      maneSize++;
   }
   
   @Override
   public String toString() {
      return "lion named " + name + " with size " + maneSize + " mane";
   }
}

class Fish extends Animal {
   private int bubbles;

   public Fish(String name, int bubbles) {
      super(name);
      this.bubbles = bubbles;
   }

   public Fish(String name) {
      this(name, 4);
   }
   
   public Fish() {
      this("fishy");
   }
   
   @Override
   public void speak() {
      System.out.println("blub");
   }
   
   public void bubble() {
      System.out.println("fish " + name + " releases " + bubbles + " bubbles");
   }
   
   @Override
   public String toString() {
      return "fish named " + name + " that releases " + bubbles + " bubbles at a time";
   }
}

class Gecko extends Animal {
   private String skinColor;
   private String spotColor;

   public Gecko(String name, String skinColor, String spotColor) {
      super(name);
      this.skinColor = skinColor;
      this.spotColor = spotColor;
   }
   
   public Gecko(String name) {
      this(name, "white", "purple");
   }
   
   public Gecko() {
      this("lizer");
   }
   
   @Override
   public void speak() {
      System.out.println("gecko noises");
   }
   
   public Gecko breed(Gecko other) {
      Random genetics = new Random();
      String newSkinColor = (genetics.nextFloat() < 0.5) ? this.skinColor : other.skinColor;
      String newSpotColor = (genetics.nextFloat() < 0.5) ? this.spotColor : other.spotColor;
      return new Gecko(this.name + "-" + other.name, newSkinColor, newSpotColor);
   }
   
   @Override
   public String toString() {
      return "gecko named " + name + " with " + skinColor + " skin and " + spotColor + " spots";
   }
}

class LeopardGecko extends Gecko {
   public LeopardGecko(String name) {
      super(name, "yellow", "black");
   }
   
   public LeopardGecko() {
      this("larry");
   }
}

class TokayGecko extends Gecko {
   public TokayGecko(String name) {
      super(name, "slate gray", "orange");
   }
   
   public TokayGecko() {
      this("thomas");
   }
}