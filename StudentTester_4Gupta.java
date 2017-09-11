public class StudentTester_4Gupta {
   public static void main(String args[]){
      Student student = new Student("Aditya Gupta", 10);
      System.out.println(student);
   }
}

class Student {
   private String name;
   private int gradeLevel;
   
   public Student(String name, int gradeLevel) {
      this.name = name;
      if (gradeLevel < 1) {
         this.gradeLevel = 1;
      } else if (gradeLevel > 12) {
         this.gradeLevel = 12;
      } else {
         this.gradeLevel = gradeLevel;
      }
   }
   
   @Override
   public String toString() {
      return "Name: " + name + "; Grade Level: " + gradeLevel;
   }
   
   @Override
   public boolean equals(Object otherStudent) {
      return otherStudent.toString().equals(this.toString());
   }
   
   public String getName() {
      return name;
   }
   
   public int getGradeLevel() {
      return gradeLevel;
   }
   
   public void promote() {
      if (gradeLevel < 12) {
         gradeLevel++;
      }
   }
}