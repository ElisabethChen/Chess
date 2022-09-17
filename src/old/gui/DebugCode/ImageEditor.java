package old.gui.DebugCode;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * methods used to edit and display images
 */
public class ImageEditor {
   /**
    * rescale the image based on the height
    * 
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
    * @return the converted bufferedImage
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
    * @return the converted ImageIcon
    */
   public static ImageIcon toImageIconW(BufferedImage image, int width) {
      BufferedImage resizedImg = ImageEditor.resizeImgW(image, width);
      return new ImageIcon(resizedImg);
   }

   /**
    * convert the given BufferedImage to an ImageIcon with the given height
    * 
    * @return the converted ImageIcon
    */
    public static ImageIcon toImageIconH(BufferedImage image, int height) {
      BufferedImage resizedImg = ImageEditor.resizeImgH(image, height);
      return new ImageIcon(resizedImg);
   }

   // USED FOR DEBUGGING
   public static void displayImage(BufferedImage image) {
      JFrame frame = new JFrame();
      JLabel label = new JLabel();
      label.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
      label.setIcon(new ImageIcon(image));
      Border border = BorderFactory.createLineBorder(Color.GREEN, 3);
      label.setBorder(border);
      frame.add(label);
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(3);
      frame.setLocationRelativeTo(null);
   }

   public static void displayPanel(JPanel inPanel) {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      panel.setPreferredSize(
            new Dimension(inPanel.getPreferredSize().width + 100, inPanel.getPreferredSize().height + 100));
      panel.add(inPanel);
      frame.add(panel);
      System.out.println("inPanel, bounds: " + inPanel.getWidth() + ", " + inPanel.getHeight());
      System.out.println(
            "inPanel. preferred: " + inPanel.getPreferredSize().width + ", " + inPanel.getPreferredSize().height);
      System.out.println("panel, bounds: " + panel.getWidth() + ", " + panel.getHeight());
      System.out
            .println("panel, prefferred: " + panel.getPreferredSize().width + ", " + panel.getPreferredSize().height);
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(3);
      frame.setLocationRelativeTo(null);
   }

   public static void displayLabel(JLabel inLabel) {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      panel.setPreferredSize(
            new Dimension(inLabel.getPreferredSize().width + 100, inLabel.getPreferredSize().height + 100));
      panel.add(inLabel);
      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(3);
      frame.setLocationRelativeTo(null);
   }

   public static void main(String[] args) {
   }
}
