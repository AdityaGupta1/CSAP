// LoopsA2: Turtle Run (ACM)
// Aditya Gupta, period 4

import acm.program.GraphicsProgram;
import acm.graphics.GTurtle;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;

/*
  Races four turtles to a finish line by moving them random amounts at a time
  Winner is the first turtle whose head crosses the finish line
  Winner moves to the bottom and does a "victory dance"
*/
public class TurtleRun_GuptaA_V2 extends GraphicsProgram {
   GTurtle[] turtles = new GTurtle[]{new GTurtle(100, 150), new GTurtle(100, 350)};
   GRect finishLine = new GRect(975, 0, 25, 500);
   GLabel finishLineLabel = new GLabel("FINISH", 1010, 20);
   Font labelFont = new Font("Arial", Font.BOLD, 18);
   
   GTurtle winner;
   boolean turtleWon = false;
   
   Point victoryPoint = new Point(500, 700);
   
   public void run() {
      this.setSize(1200, 900 + 100);
      
      finishLine.setColor(Color.BLACK);
      finishLine.setFilled(true);
      add(finishLine);
      finishLineLabel.setFont(labelFont);
      add(finishLineLabel);
   
      for (GTurtle turtle : turtles) {
         add(turtle);
      }
      
      while (!allTurtlesFinished()) {
         for (GTurtle turtle : turtles) {
            if (!isTurtleFinished(turtle)) {
               turtle.forward(20 + (Math.random() * 80));
            }
            
            if (isTurtleFinished(turtle)) {
               if (!turtleWon) {
                  winner = turtle;
                  turtleWon = true;
               }
            }
         }
         
         pause(500);
      }
      
      Point winnerPoint = new Point((int) (winner.getX() + winner.getWidth() / 2), (int) (winner.getY() + winner.getHeight() / 2));
      double yDifference = Math.abs(victoryPoint.y - winnerPoint.y);
      double xDifference = Math.abs(victoryPoint.x - winnerPoint.x);
      winner.setDirection(180 + Math.toDegrees(Math.atan(yDifference / xDifference)));
      
      for (int i = 0; i < (int) winnerPoint.distance(victoryPoint) / 20; i++) {
         winner.forward(20);
      }
      
      // victory dance
      for (int i = 0; i < 100; i++) {
         winner.setDirection(Math.random() * 360);
         winner.forward(Math.random() * 2 * i);
      }
   }
   
   private boolean isTurtleFinished(GTurtle turtle) {
      if (turtle.getX() < finishLine.getX()) {
         return false;
      }
      
      return true;
   }
   
   private boolean allTurtlesFinished() {
      for (GTurtle turtle: turtles) {
         if (!isTurtleFinished(turtle)) {
            return false;
         }
      }
      
      return true;
   }

   public static void main(String[] args) {
      new TurtleRun_GuptaA_V2().start(args);
   }
}