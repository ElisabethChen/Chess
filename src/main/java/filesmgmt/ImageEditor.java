package main.java.filesmgmt;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * methods used to edit images
 */
public class ImageEditor {
   /**
    * rescale the image based on the height
    * 
    * @param image  the given image
    * @param height the given height
    * @return rescaled BufferedImage
    */
   public static BufferedImage resizeImgH(BufferedImage image, int height) {
      double scale = (double) height / (double) image.getHeight();
      double width = (double) image.getWidth() * scale;

      return imageToBufferedImage(image.getScaledInstance((int) width, height, 1));
   }

   /**
    * rescale the image based on the width
    * 
    * @param image the given image
    * @param width the given width
    * @return rescaled BufferedImage
    */
   public static BufferedImage resizeImgW(BufferedImage image, int width) {
      double scale = (double) width / (double) image.getWidth();
      double height = (double) image.getHeight() * scale;
      return imageToBufferedImage(image.getScaledInstance(width, (int) height, 1));
   }

   /**
    * convert the given Image to BufferedImage
    * 
    * @param image the given Image
    * @return the converted BufferedImage
    */
   public static BufferedImage imageToBufferedImage(Image image) {
      BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
      Graphics2D g = bImage.createGraphics();
      g.drawImage(image, 0, 0, null);
      g.dispose();
      return bImage;
   }

   /**
    * convert the given BufferedImage to an ImageIcon with the given width
    * 
    * @param image the given image
    * @param width the given width
    * @return the converted ImageIcon
    */
   public static ImageIcon toImageIconW(BufferedImage image, int width) {
      BufferedImage resizedImg = ImageEditor.resizeImgW(image, width);
      return new ImageIcon(resizedImg);
   }

   /**
    * convert the given BufferedImage to an ImageIcon with the given height
    * 
    * @param image the given image
    * @param height the given height
    * @return the converted ImageIcon
    */
   public static ImageIcon toImageIconH(BufferedImage image, int height) {
      BufferedImage resizedImg = ImageEditor.resizeImgH(image, height);
      return new ImageIcon(resizedImg);
   }
}
