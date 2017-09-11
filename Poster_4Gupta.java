import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Poster_4Gupta {
   public static void main(String[] args) {
      JFrame window = new JFrame();
      window.setSize(800, 1200);
      window.setTitle("Poster");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
      window.setVisible(true);
      
      PosterComponent posterComponent = new PosterComponent(window.getContentPane().getBounds());
      window.add(posterComponent);
   }
}

class PosterComponent extends JComponent {
   Rectangle posterBounds;

   public PosterComponent(Rectangle posterBounds) {
      this.posterBounds = posterBounds;
   }

   public void paintComponent(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
        
      g2D.setColor(new Color(63, 255, 255));
      Rectangle frame = new Rectangle(0, 0, posterBounds.width, posterBounds.height);
      g2D.fill(frame); 
       
      g2D.setColor(new Color(63, 127, 255));
      Rectangle background = new Rectangle(40, 40, posterBounds.width - 80, posterBounds.height - 80);
      g2D.fill(background);
      
      String text = "here's a circle:";
      Font font = new Font("Comic Sans MS", Font.BOLD, 72);
      FontMetrics metrics = g.getFontMetrics(font);
      int x = (posterBounds.width - metrics.stringWidth(text)) / 2;
      int y = ((posterBounds.height - metrics.getHeight()) / 2) + metrics.getAscent();
      g2D.setFont(font);
      g2D.setColor(Color.RED);
      g2D.drawString(text, x, y);
      
      g2D.setColor(Color.YELLOW);
      Ellipse2D circle = new Ellipse2D.Float(200, y + 65, posterBounds.width - 400, posterBounds.width - 400);
      g2D.fill(circle);
      
      text = "dank poster";
      x = (posterBounds.width - metrics.stringWidth(text)) / 2;
      y = 200;
      g2D.setColor(Color.MAGENTA);
      g2D.drawString(text, x, y);
   }
}