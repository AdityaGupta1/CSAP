import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;

class TilesComponent extends JComponent {
   List<Color> colors;
   int width;
   int height;
   int tileWidth;
   int tileHeight;

   public TilesComponent(List<Color> colors, int width, int height, int tileWidth, int tileHeight) {
      this.colors = colors;
      this.width = width;
      this.height = height;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
   }

   public void paintComponent(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      
      for (int x = 0; x < width; x += tileWidth) {
         for (int y = 0; y < height; y += tileHeight) {
            g2D.setColor(colors.get((int) Math.floor(Math.random() * colors.size())));
            Rectangle rectangle = new Rectangle(x, y, tileWidth, tileHeight);
            g2D.fill(rectangle);
         }
      }
   }
   
   public void setColors(List<Color> colors) {
      this.colors = colors;
   }
   
   public void setTileWidth(int tileWidth) {
      this.tileWidth = tileWidth;
   }
   
   public void setTileHeight(int tileHeight) {
      this.tileHeight = tileHeight;
   }
}

public class Tiles {
   static int numberOfColors = 3;
      
   private static ArrayList<Color> randomColors() {
      ArrayList<Color> colors = new ArrayList<Color>();
      
      for (int i = 0; i < numberOfColors; i++) {
         colors.add(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
      }
      
      return colors;
   }
   
   private static int randomTileDimension() {
      return (int) Math.ceil(Math.random() * 100);
   }

   public static void main(String[] args) {
      JFrame window = new JFrame();
      
      window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
      window.setTitle("Tiles");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
      int width = windowBounds.width;
      int height = windowBounds.height;
      
      TilesComponent rectangle = new TilesComponent(randomColors(), width, height, randomTileDimension(), randomTileDimension());
      window.add(rectangle);
      
      window.setVisible(true);
      
      while (true) {
         rectangle.setColors(randomColors());
         rectangle.setTileWidth(randomTileDimension());
         rectangle.setTileHeight(randomTileDimension());
                  
         window.repaint();
         
         try {
            Thread.sleep(100);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}