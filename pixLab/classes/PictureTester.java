/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
   public static void testGrayscale() {
      Picture picture = new Picture("dank/thonk.png");
      picture.grayscale();
      picture.explore();
   }
  
   public static void testKeepOnlyBlue() {
      Picture picture = new Picture("dank/thonk.png");
      picture.keepOnlyBlue();
      picture.explore();
   }
  
   public static void testNegate() {
      Picture picture = new Picture("dank/thonk.png");
      picture.negate();
      picture.explore();
   }
  
   public static void testMirrorHorizontal() {
      Picture picture = new Picture("dank/thonk.png");
      picture.mirrorHorizontal();
      picture.explore();
   }
   
   public static void testCopy() {
      Picture picture = new Picture("dank/thonk.png");
      picture.copy(new Picture("dank/pepe.jpg"), 100, 100, 0, 0, 400, 400);
      picture.explore();
   }
   
   public static void testCollage() {
      Picture picture = new Picture("dank/thonk.png");
      picture.createDankCollage();
      picture.explore();
   }
  
   public static void main(String[] args) {
      // testGrayscale();
      // testKeepOnlyBlue();
      // testNegate();
      // testMirrorHorizontal();
      
      testCopy();
      testCollage();
   }
}