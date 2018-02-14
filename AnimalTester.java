import java.util.ArrayList;

public class AnimalTester {
   public static void main(String[] args) {
      ArrayList<Animal> animals = new ArrayList<>();
      animals.add(new Bird("Foozeh"));
      animals.add(new Lion("jgnibo"));
      animals.add(new Fish("SDOAJ"));
      animals.add(new LeopardGecko("Larry"));
      
      for (Animal animal : animals) {
         System.out.print(animal.getName() + " says: ");
         animal.speak();
      }
   }
}

class Animal {
   private String name;
   
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
}

class Bird extends Animal {
   public Bird(String name) {
      super(name);
   }

   public void speak() {
      System.out.println("chirp");
   }
}

class Lion extends Animal {
   public Lion(String name) {
      super(name);
   }
   
   public void speak() {
      System.out.println("roar");
   }
}

class Fish extends Animal {
   public Fish(String name) {
      super(name);
   }
   
   public void speak() {
      System.out.println("blub");
   }
}

class LeopardGecko extends Animal {
   public LeopardGecko(String name) {
      super(name);
   }
   
   public void speak() {
      System.out.println("you're bad");
   }
}