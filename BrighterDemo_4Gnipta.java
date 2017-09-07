import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JComponent;
import javax.swing.JFrame;

// Intro-A7: Colors (PP)
// Aditya Gupta and Jack Gnibus
public class BrighterDemo_4Gnipta {
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(1000, 1000);
      frame.setTitle("Colors");     
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Color initialColor = new Color(50, 100, 150);
      System.out.println("Original Color:");
      System.out.println("Red: " + initialColor.getRed() + "  Green: " + initialColor.getGreen() + "  Blue: " + initialColor.getBlue());
      Color newColor = initialColor.brighter();
      System.out.println("After Brightening:");
      System.out.println("Red: " + newColor.getRed() + "  Green: " + newColor.getGreen() + "  Blue: " + newColor.getBlue());
      
      frame.setVisible(true);
 
      RectangleComponent rectangleComponent = new RectangleComponent(initialColor, newColor, frame.getContentPane().getBounds());
      frame.add(rectangleComponent);
   }
}

class RectangleComponent extends JComponent {
   Color initialColor;
   Color newColor;
   Rectangle frameBounds;

   public RectangleComponent(Color initialColor, Color newColor, Rectangle frameBounds) {
      this.initialColor = initialColor;
      this.newColor = newColor;
      this.frameBounds = frameBounds;
   }

   public void paintComponent(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      
      g2D.setColor(initialColor);
      Rectangle initialColorRect = new Rectangle(0, 0, 500, 1000);
      g2D.fill(initialColorRect);
      g2D.setColor(newColor);
      Rectangle newColorRect = new Rectangle(500, 0, 500, 1000);
      g2D.fill(newColorRect);
      
      String text = "Original Color";
      g2D.setColor(Color.BLACK);
      Font font = new Font("Verdana", Font.BOLD, 32);
      g2D.setFont(font);
      g2D.drawString(text, 10, 38);
      
      FontMetrics metrics = g2D.getFontMetrics(font);
      text = "New Color";
      g2D.drawString(text, frameBounds.width - metrics.stringWidth(text) - 10, 38);
   }
}