public class StudentInheritsTester_4Gupta {
   public static void main(String[] args) {
      Person[] people = {new Student("cool kid", 15, 72, 4.8),
            new Student("not cool kid", 16, 60, 2.6),
            new Person("tall guy", 40, 72),
            new Person("short guy", 70, 60)};
            
      for (Person person : people) {
         person.speak();
      }
      
      people[0].growTaller();
      ((Student) people[1]).failTest();
      people[1].growOlder();
      people[2].growTaller();
      people[3].changeName("cool guy");
            
      System.out.println();
      for (Person person : people) {
         person.speak();
      }
   }
}

class Person {
   private String name;
   private int age;
   private int height; // inches
   
   public Person(String name, int age, int height) {
      this.name = name;
      this.age = age;
      this.height = height;
   }
   
   public String getName() {
      return this.name;
   }
   
   public void changeName(String name) {
      this.name = name;
   }
   
   public void growTaller() {
      this.height++;
   }
   
   public void growOlder() {
      this.age++;
   }

   public void speak() {
      int feet = (this.height / 12);
      int inches = (this.height % 12);
      
      String message = getName() + ": \"I am " + this.age + 
         " years old and my height is " + feet + " feet";
      
      if (inches != 0) {
         message += " " + inches + " " + (inches == 1 ? "inch" : "inches") + "\"";
      }
         
      System.out.println(message);
   }
}

class Student extends Person {
   private static int nextId = 220000;
   private final int id;
   private double gpa;
   
   public Student(String name, int age, int height, double gpa) {
      super(name, age, height);
      this.id = nextId++;
      this.gpa = gpa;
   }
   
   public void failTest() {
      this.gpa -= 0.2;
   }

   @Override
   public void speak() {
      super.speak();
      System.out.println(getName() + ": \"my id is " + this.id + " and my GPA is " + this.gpa + "\"");
   }
}