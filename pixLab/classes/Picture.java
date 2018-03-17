import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with	java.awt.List and	java.util.List

/**
 *	A class that represents	a picture.	This class inherits from
 *	SimplePicture and	allows the student to add functionality to
 *	the Picture	class.
 *
 *	@author Barbara Ericson	ericson@cc.gatech.edu
 */
public class Picture	extends SimplePicture
{
  /////////////////////	constructors //////////////////////////////////

  /**
	* Constructor that takes no arguments
	*/
  public	Picture ()
  {
	 /* not needed	but use it to show students the implicit call to super()
	  * child constructors always	call a parent constructor
	  */
	 super();
  }

  /**
	* Constructor that takes a	file name and creates the picture
	* @param	fileName	the name	of	the file	to	create the picture from
	*/
  public	Picture(String	fileName)
  {
	 // let the	parent class handle this fileName
	 super(fileName);
  }

  /**
	* Constructor that takes the width and	height
	* @param	height the height	of	the desired	picture
	* @param	width	the width of the desired picture
	*/
  public	Picture(int	height, int	width)
  {
	 // let the	parent class handle this width and height
	 super(width,height);
  }

  /**
	* Constructor that takes a	picture and	creates a
	* copy of that	picture
	* @param	copyPicture	the picture	to	copy
	*/
  public	Picture(Picture copyPicture)
  {
	 // let the	parent class do the copy
	 super(copyPicture);
  }

  /**
	* Constructor that takes a	buffered	image
	* @param	image	the buffered image to use
	*/
  public	Picture(BufferedImage image)
  {
	 super(image);
  }

  ////////////////////// methods	///////////////////////////////////////

  /**
	* Method	to	return a	string with	information	about	this picture.
	* @return a	string with	information	about	the picture	such as fileName,
	* height	and width.
	*/
  public	String toString()
  {
	 String output	= "Picture, filename " + getFileName()	+
		" height " + getHeight()
		+ " width "	+ getWidth();
	 return output;

  }

  /**	Method to set the	blue to 0 */
  public	void zeroBlue()
  {
	 Pixel[][] pixels	= this.getPixels2D();
	 for (Pixel[] rowArray : pixels)
	 {
		for (Pixel pixelObj : rowArray)
		{
		  pixelObj.setBlue(0);
		}
	 }
  }

  /**	Method that	mirrors the	picture around	a
	 *	vertical	mirror in the center	of	the picture
	 *	from left to right */
  public	void mirrorVertical()
  {
	 Pixel[][] pixels	= this.getPixels2D();
	 Pixel leftPixel = null;
	 Pixel rightPixel	= null;
	 int width = pixels[0].length;
	 for (int row = 0; row < pixels.length; row++)
	 {
		for (int	col =	0;	col <	width	/ 2; col++)
		{
		  leftPixel	= pixels[row][col];
		  rightPixel =	pixels[row][width	- 1 -	col];
		  rightPixel.setColor(leftPixel.getColor());
		}
	 }
  }

  /**	Mirror just	part of a picture	of	a temple	*/
  public	void mirrorTemple()
  {
	 int mirrorPoint = 276;
	 Pixel leftPixel = null;
	 Pixel rightPixel	= null;
	 int count = 0;
	 Pixel[][] pixels	= this.getPixels2D();

	 // loop	through the	rows
	 for (int row = 27; row	< 97;	row++)
	 {
		//	loop from 13 to just	before the mirror	point
		for (int	col =	13; col < mirrorPoint; col++)
		{

		  leftPixel	= pixels[row][col];
		  rightPixel =	pixels[row]
								 [mirrorPoint - col + mirrorPoint];
		  rightPixel.setColor(leftPixel.getColor());
		}
	 }
  }

  /**	copy from the passed	fromPic to the
	 *	specified startRow and startCol in the
	 *	current picture
	 *	@param fromPic	the picture	to	copy from
	 *	@param startRow the start row	to	copy to
	 *	@param startCol the start col	to	copy to
	 */
  public	void copy(Picture	fromPic, int startRow, int startCol) {
	 Pixel fromPixel = null;
	 Pixel toPixel	= null;
	 Pixel[][] toPixels = this.getPixels2D();
	 Pixel[][] fromPixels =	fromPic.getPixels2D();
	 for (int fromRow	= 0, toRow = startRow; fromRow < fromPixels.length && toRow	< toPixels.length; fromRow++, toRow++) {
		for (int	fromCol = 0, toCol =	startCol; fromCol <	fromPixels[0].length	&& toCol < toPixels[0].length; fromCol++, toCol++) {
		  fromPixel	= fromPixels[fromRow][fromCol];
		  toPixel =	toPixels[toRow][toCol];
		  toPixel.setColor(fromPixel.getColor());
		}
	 }
  }

  /**	Method to create a collage	of	several pictures */
  public	void createCollage()
  {
	 Picture	flower1 = new Picture("flower1.jpg");
	 Picture	flower2 = new Picture("flower2.jpg");
	 this.copy(flower1,0,0);
	 this.copy(flower2,100,0);
	 this.copy(flower1,200,0);
	 Picture	flowerNoBlue =	new Picture(flower2);
	 flowerNoBlue.zeroBlue();
	 this.copy(flowerNoBlue,300,0);
	 this.copy(flower1,400,0);
	 this.copy(flower2,500,0);
	 this.mirrorVertical();
	 this.write("collage.jpg");
  }


  /**	Method to show	large	changes in color
	 *	@param edgeDist the distance for	finding edges
	 */
  public	void edgeDetection(int edgeDist)
  {
	 Pixel leftPixel = null;
	 Pixel rightPixel	= null;
	 Pixel[][] pixels	= this.getPixels2D();
	 Color rightColor	= null;
	 for (int row = 0; row < pixels.length; row++)
	 {
		for (int	col =	0;
			  col	< pixels[0].length-1; col++)
		{
		  leftPixel	= pixels[row][col];
		  rightPixel =	pixels[row][col+1];
		  rightColor =	rightPixel.getColor();
		  if (leftPixel.colorDistance(rightColor)	>
				edgeDist)
			 leftPixel.setColor(Color.BLACK);
		  else
			 leftPixel.setColor(Color.WHITE);
		}
	 }
  }

   public void grayscale() {
      for (Pixel[] row : this.getPixels2D()) {
         for (Pixel pixel : row) {
            int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
         }
      }
   }

   public void keepOnlyBlue() {
      for (Pixel[] row : this.getPixels2D()) {
         for (Pixel pixel : row) {
            pixel.setRed(0);
            pixel.setGreen(0);
         }
      }
   }

   public void negate() {
      for (Pixel[] row : this.getPixels2D()) {
         for (Pixel pixel : row) {
            pixel.setRed(255 - pixel.getRed());
            pixel.setGreen(255 - pixel.getGreen());
            pixel.setBlue(255 - pixel.getBlue());
         }
      }
   }
   
   public void mirrorHorizontal() {
      Pixel[][] pixels = this.getPixels2D();
      Pixel[][] mirroredPixels = new Pixel[pixels.length][pixels[0].length];
      
      for (int i = 0; i < pixels.length; i++) {
         mirroredPixels[i] = pixels[pixels.length - i - 1]; 
      }
      
      for (int j = 0; j < pixels.length; j++) {
         for (int k = 0; k < pixels[j].length; k++) {
            pixels[j][k].setColor(mirroredPixels[j][k].getColor());
         }
      }
   }
   
   public void copy(Picture from, int startPasteRow, int startPasteCol, int startCopyRow, int startCopyCol, int endCopyRow, int endCopyCol) {
      Pixel fromPixel = null;
   	Pixel toPixel = null;
   	Pixel[][] toPixels = this.getPixels2D();
   	Pixel[][] fromPixels = from.getPixels2D();
   	for (int fromRow = startCopyRow, toRow = startPasteRow; startCopyRow < fromPixels.length && fromRow < endCopyRow && toRow < toPixels.length; fromRow++, toRow++) {
   	   for (int	fromCol = startCopyCol, toCol = startPasteCol; startCopyCol < fromPixels[0].length && fromCol < endCopyCol && toCol < toPixels[0].length; fromCol++, toCol++) {
   	      fromPixel = fromPixels[fromRow][fromCol];
   	      toPixel = toPixels[toRow][toCol];
   	      toPixel.setColor(fromPixel.getColor());
   		}
   	}
   }
   
   public void createDankCollage() {
      this.mirrorHorizontal();
   
      Picture pepe = new Picture("dank/pepe.jpg");
      Picture invertedPepe = new Picture(pepe);
      invertedPepe.negate();
      Picture grayPepe = new Picture(pepe);
      grayPepe.grayscale();
      
      Picture memeMan = new Picture("dank/meme_man.png");
      Picture noBlueMemeMan = new Picture(memeMan);
      noBlueMemeMan.zeroBlue();
      Picture onlyBlueMemeMan = new Picture(memeMan);
      onlyBlueMemeMan.keepOnlyBlue();
      
      Picture orang = new Picture("dank/orang.png");
      Picture invertedOrang = new Picture(orang);
      invertedOrang.negate();
      
      Picture buzzLightyear = new Picture("dank/buzz_lightyear.jpg");
      Picture mirroredBuzzLightyear = new Picture(buzzLightyear);
      mirroredBuzzLightyear.mirrorHorizontal();
      
      this.copy(pepe, 0, 200, 200, 200, 400, 400);
      this.copy(invertedPepe, 300, 500);
      this.copy(grayPepe, 100, 0, 0, 0, 100, 100);
      this.copy(memeMan, 800, 0, 300, 500, 400, 550);
      this.copy(noBlueMemeMan, 400, 400);
      this.copy(onlyBlueMemeMan, 100, 0);
      this.copy(orang, 0, 500, 0, 0, 100, 300);
      this.copy(invertedOrang, 100, 100, 0, 0, 50, 200);
      this.copy(buzzLightyear, 0, 200, 0, 0, 50, 200);
      this.copy(mirroredBuzzLightyear, 500, 0, 500, 500, 600, 600);
   }

  /* Main method for	testing - each	class	in	Java can	have a main
	* method
	*/
   public static void main(String[] args) {
      Picture beach = new Picture("beach.jpg");
      beach.explore();
      beach.zeroBlue();
      beach.explore();
   }
}