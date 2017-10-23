import acm.program.GraphicsProgram;
import acm.graphics.GTurtle;
import acm.graphics.GRect;
import java.awt.Color;

public class TurtleRun_GuptaA extends GraphicsProgram {
   GTurtle[] turtles = new GTurtle[]{new GTurtle(100, 100), new GTurtle(100, 200), new GTurtle(100, 300), new GTurtle(100, 400)};
   GRect finishLine = new GRect(500, 0, 25, 1000);
   
   public void run() {
      for (GTurtle turtle : turtles) {
         add(turtle);
      }
      
      finishLine.setColor(Color.BLACK);
      finishLine.setFilled(true);
      add(finishLine);
      
      while (!allTurtlesFinished()) {
         for (GTurtle turtle : turtles) {
            turtle.forward(Math.random() * 100);
         }
         
         pause(500);
      }
   }
   
   private boolean allTurtlesFinished() {
      for (GTurtle turtle : turtles) {
         if (turtle.getLocation().getX() < finishLine.getLocation().getX()) {
            return false;
         }
      }
      
      return true;
   }

   public static void main(String[] args) {
      new TurtleRun_GuptaA().start(args);
   }
}