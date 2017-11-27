// Arrays-A5: Flexi Turtle Race
// Aditya Gupta, period 4

import acm.program.GraphicsProgram;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GPoint;
import acm.graphics.GObject;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JOptionPane;

/*
  Races rockets to a finish line by moving them random amounts at a time
  Winner is the first rocket whose head crosses the finish line
*/
public class RocketRace_4Gupta extends GraphicsProgram {
   GRocket[] rockets;
   GRect finishLine = new GRect(975, 0, 25, 500);
   GLabel finishLineLabel = new GLabel("FINISH", 1010, 20);
   Font labelFont = new Font("Arial", Font.BOLD, 18);
   
   GRocket winner;
   boolean rocketWon = false;
   
   GPoint victoryPoint = new GPoint(500, 700);
   
   public void run() {
      String input;
      int numberOfRockets = 0;
      
      boolean isValid = false;
      while (!isValid) {
         input = JOptionPane.showInputDialog("How many rockets (2 to 9)?");
      
         try {
            numberOfRockets = Integer.parseInt(input);
         } catch (NumberFormatException e) {
            continue;
         }
         
         if (numberOfRockets < 2 || numberOfRockets > 9) {
            continue;
           
         }
         
         
         isValid = true;
      }
      
      rockets = new GRocket[numberOfRockets];
      
      for (int i = 0; i < rockets.length; i++) {
         rockets[i] = new GRocket(100, 50 + (i * (400 / rockets.length)));
      }
   
      this.setSize(1200, 900 + 100);
      
      finishLine.setColor(Color.BLACK);
      finishLine.setFilled(true);
      add(finishLine);
      finishLineLabel.setFont(labelFont);
      add(finishLineLabel);
      
      while (!allRocketsFinished()) {
         for (GRocket rocket : rockets) {
            if (!isRocketFinished(rocket)) {
               rocket.move(20 + (Math.random() * 80), 0);
            }
            
            if (isRocketFinished(rocket)) {
               if (!rocketWon) {
                  winner = rocket;
                  rocketWon = true;
               }
            }
         }
         
         pause(500);
      }
      
      for (double i = 1; i < 2000; i *= -1.25) {
         winner.move(i, 0);
         pause(50);
      }
   }
   
   private boolean isRocketFinished(GRocket rocket) {
      if (rocket.getX() < finishLine.getX()) {
         return false;
      }
      
      return true;
   }
   
   private boolean allRocketsFinished() {
      for (GRocket rocket: rockets) {
         if (!isRocketFinished(rocket)) {
            return false;
         }
      }
      
      return true;
   }
   
   
   class GRocket {
      private int x;
      private int y;
      
      private GObject[] parts = new GObject[6];
   
      public GRocket(int x, int y) {
         this.x = x;
         this.y = y;
         
         parts[0] = new GOval(x, y - 10, 50, 20);
         ((GOval) parts[0]).setFillColor(Color.LIGHT_GRAY);
         ((GOval) parts[0]).setFilled(true);
         
         parts[1] = new GPolygon(new GPoint[]{new GPoint(x + 40, y + 8), new GPoint(x + 52, y), new GPoint(x + 40, y - 8)});
         ((GPolygon) parts[1]).setFillColor(Color.LIGHT_GRAY);
         ((GPolygon) parts[1]).setFilled(true);
         
         parts[2] = new GRect(x, y - 5, 10, 10);
         ((GRect) parts[2]).setFillColor(Color.LIGHT_GRAY);
         ((GRect) parts[2]).setFilled(true);
         
         parts[3] = new GPolygon(new GPoint[]{new GPoint(x, y + 5), new GPoint(x, y + 12), new GPoint(x + 10, y + 5)});
         ((GPolygon) parts[3]).setFillColor(Color.LIGHT_GRAY);
         ((GPolygon) parts[3]).setFilled(true);
         
         parts[4] = new GPolygon(new GPoint[]{new GPoint(x, y - 5), new GPoint(x, y - 12), new GPoint(x + 10, y - 5)});
         ((GPolygon) parts[4]).setFillColor(Color.LIGHT_GRAY);
         ((GPolygon) parts[4]).setFilled(true);
         
         for (int i = 0; i <= 4; i++) {
            parts[i].setColor(Color.LIGHT_GRAY);
         }
         
         parts[5] = new GOval(x + 30, y - 5, 10, 10);
         Color windowColor = new Color(174, 223, 242);
         ((GOval) parts[5]).setFillColor(windowColor);
         ((GOval) parts[5]).setFilled(true);
         parts[5].setColor(windowColor);
         
         for (GObject part : parts) {
            add(part);
         }
      }
      
      public int getX() {
         return x;
      }
      
      public int getY() {
         return y;
      }
      
      public void move(double dx, double dy) {
         for (GObject part : parts) {
            part.move(dx, dy);
         }
         
         x += dx;
         y += dy;
      }
   }

   public static void main(String[] args) {
      new RocketRace_4Gupta().start(args);
   }
}