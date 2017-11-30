// Arrays-A5b: Flexi Object Race (Fini)
// Aditya Gupta, period 4

import java.util.Random;
import acm.program.GraphicsProgram;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GPoint;
import acm.graphics.GObject;
import acm.graphics.GFillable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

/*
  Races rockets to a finish line by moving them random amounts at a time
  Winner is the first rocket whose head crosses the finish line
*/
public class RocketRace_4Gupta_V2 extends GraphicsProgram {
   GRocket[] rockets;
   GRect finishLine = new GRect(975, 0, 25, 500);
   GLabel finishLineLabel = new GLabel("FINISH", 1010, 20);
   Font labelFont = new Font("Arial", Font.BOLD, 18);
   
   GRocket winner;
   boolean rocketWon = false;
   
   public void run() {
      String input;
      int numberOfRockets = 0;
      
      boolean isValid = false;
      String message = "How many rockets (2 to 9)?";
      while (!isValid) {
         input = JOptionPane.showInputDialog(message);
      
         try {
            numberOfRockets = Integer.parseInt(input);
         } catch (NumberFormatException e) {
            message = "Invalid input! Please enter an integer.\nHow many rockets (2 to 9)?";
            continue;
         }
         
         if (numberOfRockets < 2 || numberOfRockets > 9) {
            message = "Invalid input! Please enter an integer between 2 and 9, inclusive.\nHow many rockets (2 to 9)?";
            continue;
         }
         
         
         isValid = true;
      }
      
      rockets = new GRocket[numberOfRockets];
      
      for (int i = 0; i < rockets.length; i++) {
         rockets[i] = new GRocket(100, 50 + (int) Math.round((i + 0.5) * (400 / rockets.length)), this);
      }
   
      this.setSize(1200, 600);
      
      finishLine.setColor(Color.BLACK);
      finishLine.setFilled(true);
      add(finishLine);
      finishLineLabel.setFont(labelFont);
      add(finishLineLabel);
      
      while (!allRocketsFinished()) {
         for (GRocket rocket : rockets) {
            if (!isRocketFinished(rocket)) {
               rocket.move(2 + (Math.random() * 8), 0);
            }
            
            if (isRocketFinished(rocket)) {
               if (!rocketWon) {
                  winner = rocket;
                  rocketWon = true;
               }
               
               if (rocket != winner) {
                  rocket.toggleFlames(false);
               }
            }
         }
         
         pause(50);
      }
      
      winner.victoryDance(finishLine);
   }
   
   private boolean isRocketFinished(GRocket rocket) {
      if (rocket.getX() < finishLine.getX() - 80) {
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

   public static void main(String[] args) {
      new RocketRace_4Gupta_V2().start(args);
   }
}

/*
  A rocket that consists of GObjects
  Can move and do a victory dance
*/
class GRocket {
   private int x;
   private int y;
   private GraphicsProgram g;
   
   private GObject[] parts = new GObject[7];
   
   private static Random random = new Random();

   public GRocket(int x, int y, GraphicsProgram g) {
      this.x = x;
      this.y = y;
      this.g = g;
      
      // main body
      parts[0] = new GOval(x, y - 10, 50, 20);
      parts[0].setColor(Color.LIGHT_GRAY);
      
      // triangle at the end
      parts[1] = new GPolygon(new GPoint[]{new GPoint(x + 40, y + 8), new GPoint(x + 52, y), new GPoint(x + 40, y - 8)});
      // rectangle between fins
      parts[2] = new GRect(x, y - 5, 10, 10);
      // bottom fin
      parts[3] = new GPolygon(new GPoint[]{new GPoint(x, y + 5), new GPoint(x, y + 12), new GPoint(x + 10, y + 5)});
      // top fin
      parts[4] = new GPolygon(new GPoint[]{new GPoint(x, y - 5), new GPoint(x, y - 12), new GPoint(x + 10, y - 5)});
      
      for (int i = 1; i <= 4; i++) {
         parts[i].setColor(Color.LIGHT_GRAY);
         ((GFillable) parts[i]).setFillColor(Color.LIGHT_GRAY);
         ((GFillable) parts[i]).setFilled(true);
      }
      
      // flames
      parts[5] = new GPolygon(new GPoint[]{new GPoint(x, y + 12), new GPoint(x - 6, y + 8), new GPoint(x - 4, y + 6), 
         new GPoint(x - 10, y), new GPoint(x - 4, y - 6), new GPoint(x - 6, y - 8), new GPoint(x, y - 12)});
      parts[5].setColor(Color.ORANGE);
      ((GFillable) parts[5]).setFillColor(Color.ORANGE);
      ((GFillable) parts[5]).setFilled(true);
      
      // window
      parts[6] = new GOval(x + 25, y - 5, 10, 10);
      Color windowColor = new Color(174, 223, 242);
      parts[6].setColor(windowColor);
      ((GFillable) parts[6]).setFillColor(windowColor);
      ((GFillable) parts[6]).setFilled(true);
      
      for (GObject part : parts) {
         g.add(part);
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
   
   private Color randomColor() {
      return new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
   }
   
   public void victoryDance(GRect finishLine) {
      toggleFlames(true);
      
      g.remove(finishLine);
      g.add(finishLine);
   
      int move = 0;
   
      while (true) {
         for (GObject part : parts) {
            part.setColor(randomColor());
            ((GFillable) part).setFillColor(randomColor());
         }
         
         move(move, 0);
         move = random.nextInt(50) - 25;
         
         g.pause(100);
      }
   }
   
   public void toggleFlames(boolean flamesOn) {
      if (flamesOn) {
         g.add(parts[5]);
      } else {
         g.remove(parts[5]);
      }
   }
}