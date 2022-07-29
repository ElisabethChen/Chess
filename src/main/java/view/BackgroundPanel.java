package main.java.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import main.java.filesmgmt.Assets;
import main.java.filesmgmt.ImageEditor;

/**
* create a panel with background
*/
public class BackgroundPanel extends JPanel {
   private BufferedImage bgImage;
   private int width;
   private int height;

   public BackgroundPanel(BufferedImage bgImage, int width, int height) {
      this.bgImage = bgImage;
      this.width = width;
      this.height = height;
      this.setPreferredSize(new Dimension(width, height));
   }

   /**
   * paint the background to the panel
   */
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.bgImage = ImageEditor.resizeImgW(this.bgImage, this.width);
      g.drawImage(this.bgImage, 0, 0, null);
      g.setColor(Assets.getBgFilterColor());
      g.fillRect(0, 0, this.width, this.height);
   }
}
