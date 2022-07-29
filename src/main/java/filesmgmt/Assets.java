package main.java.filesmgmt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import main.java.enums.PieceType;
import main.java.enums.PlayerType;

/**
 * includes all Assets, for instance: paths, images, colors, font style, font sizes.
 */
public class Assets {
   // Colors
   private static Color mainColor = new Color(44990);
   private static Color tileColorW = new Color(10211540);
   private static Color tileColorB = new Color(3961463);
   private static Color errorColor = new Color(13303808);
   private static Color bgFilterColor = new Color(196, 196, 196, 80);
   private static Color p1Color = Color.WHITE;
   private static Color p2Color = Color.BLACK;

   // Font type and size
   private static String fontType = "Algerian";
   private static int largeSize = 32;
   private static int mediumSize = 26;

   // Images
   private static BufferedImage[][] piecesImage;
   private static BufferedImage bgImage;
   private static BufferedImage dotImage;

   // Paths
   private static String bgPath = "src/main/resources/image/backgound/BG_chess.jpg";
   private static String dotPath = "src/main/resources/image/dots/DotDarkGray2.png";
   private static String piecesPath = "src/main/resources/image/pieces/ver1";
   private static String[] playersFolder = { "White", "Black" };

   /**
    * loading all images
    */
   public static void init() { // TODO: fix that init start by itself if possible
      bgImage = FileLoader.loadImage(bgPath);
      dotImage = FileLoader.loadImage(dotPath);
      piecesImage = new BufferedImage[2][6];
      File mainFolder = new File(piecesPath);
      for (PlayerType player : PlayerType.values()) {
         File folder = new File(mainFolder, playersFolder[player.ordinal()]);
         for (PieceType pieceType : PieceType.values()) {
            String piecePath = String.format("%s\\%s.png", folder.getPath(), pieceType.name());
            BufferedImage image = FileLoader.loadImage(piecePath);
            piecesImage[player.ordinal()][pieceType.ordinal()] = ImageEditor.resizeImgH(image, 50);
         }
      }
   }

   // GETTERS
   public static BufferedImage[][] getPiecesImage() {
      return piecesImage;
   }

   public static BufferedImage getPieceImage(PieceType pieceType, PlayerType owner) {
      return piecesImage[owner.ordinal()][pieceType.ordinal()];
   }

   public static BufferedImage getBgImage() {
      return bgImage;
   }

   public static BufferedImage getDotImage() {
      return dotImage;
   }

   public static String getFontType() {
      return fontType;
   }

   public static int getLargeSize() {
      return largeSize;
   }

   public static int getMediumSize() {
      return mediumSize;
   }

   public static Color getMainColor() {
      return mainColor;
   }

   public static Color getTileColorW() {
      return tileColorW;
   }

   public static Color getTileColorB() {
      return tileColorB;
   }

   public static Color getErrorColor() {
      return errorColor;
   }
   
   public static Color getBgFilterColor() {
      return bgFilterColor;
   }

   public static Color getP1Color() {
      return p1Color;
   }

   public static Color getP2Color() {
      return p2Color;
   }
}