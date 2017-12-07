// Arrays-A8: Student GPA
// Aditya Gupta

import java.util.Random;

public class GPATester_Gupta4 {
   static Student[] students = new Student[7];
      
   public static void main(String[] args) {
      Random random = new Random();
      for (int i = 0; i < students.length; i++) {
         int units = random.nextInt(3) + 5;
         students[i] = new Student("student " + (i + 1), random.nextInt(12) + 1, units, units * 4 + random.nextInt(7) - 3);
      }
      
      for (Student student : students) {
         System.out.println(student);
      }
      
      System.out.println();
      
      System.out.printf("min GPA: %.02f\n", min());
      System.out.printf("max GPA: %.02f\n", max());
      System.out.printf("mean GPA: %.02f\n", mean());
   }
   
   private static double min() {
      double min = students[0].getGPA();
      
      for (Student student : students) {
         min = Math.min(min, student.getGPA());
      }
      
      return min;
   }
   
   private static double max() {
      double max = students[0].getGPA();
      
      for (Student student : students) {
         max = Math.max(max, student.getGPA());
      }
      
      return max;
   }
   
   private static double mean() {
      double total = 0;
      
      for (Student student : students) {
         total += student.getGPA();
      }
      
      return total / students.length;
   }
}

class Student {
   private String name;
   private int gradeLevel;
   private int units;
   private int gradePoints;
   
   public Student(String name, int gradeLevel) {
      this.name = name;
      this.gradeLevel = gradeLevel;
   }
   
   public Student(String name, int gradeLevel, int units, int gradePoints) {
      this.name = name;
      this.gradeLevel = gradeLevel;
      this.units = units;
      this.gradePoints = gradePoints;
   }
   
   public void updateGPA(int units, int gradePoints) {
      this.units = units;
      this.gradePoints = gradePoints;
   }
   
   public double getGPA() {
      return gradePoints / (units * 1.0);
   }
   
   public String toString() {
      return "name: " + name + ", grade level: " + gradeLevel + ", GPA: " + String.format("%.02f", getGPA());
   }
}