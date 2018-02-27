import java.awt.Color;

public class ShapeTester_4GuptaHu {
   public static void main(String[] args) {
      Circle circle = new Circle(100, 100, 50, Color.BLUE);
      Rectangle rectangle = new Rectangle(200, 200, 100, 100, Color.RED);
      
      System.out.println("circle's position: (" + circle.getX() + ", " + circle.getY() + ")");
      System.out.println("circle's area: " + circle.getArea());
      System.out.println("circle's color: " + circle.getColor());
      System.out.println("rectangle's position: (" + rectangle.getX() + ", " + rectangle.getY() + ")");
      System.out.println("rectangle's area: " + rectangle.getArea());
      System.out.println("rectangle's color: " + rectangle.getColor());
      System.out.println("rectangle's bottom-right y coordinate: " + rectangle.getBottomRightY());
   }
}

abstract class Shape {
   private int x;
   private int y;
   private Color color;

   public Shape(int x, int y, Color color) {
      this.x = x;
      this.y = y;
      this.color = color;
   }

   public Shape(int x, int y) {
      this(x, y, Color.WHITE);
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }
   
   public Color getColor() {
      return color;
   }
    
   public abstract int getArea();
}

class Circle extends Shape {
   private int radius;
   
   public Circle(int x, int y, int radius, Color color) {
      super(x, y, color);
      this.radius = radius;
   }
   
   @Override
   public int getArea() {
      return (int) Math.round(Math.PI * this.radius * this.radius);
   }
}

class Rectangle extends Shape {
   private int width;
   private int height;

   public Rectangle(int x, int y, int width, int height, Color color) {
      super(x, y, color);
      this.width = width;
      this.height = height;
   }
   
   public int getBottomRightY() {
      return this.getY() + this.height;
   }
   
   @Override
   public int getArea() {
      return this.width * this.height;
   }
}