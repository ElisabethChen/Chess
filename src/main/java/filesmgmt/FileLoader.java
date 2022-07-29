package main.java.filesmgmt;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
* methods used to load files (e.g. images or audio)
*/
public class FileLoader {
   /**
   * load the file (image or audio) of the given path
   * @param path path of the file
   * @param fileType the type of the file (either "image" or "audio")
   * @return Object of the file
   */
   private static Object basicLoader(String path, String fileType) {
      try {
         File file = new File(path);
         if (file.exists()) {
            if (fileType.equals("audio")) {
               AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
               Clip clip = AudioSystem.getClip();
               clip.open(audioStream);
               return clip;
            } else if (fileType.equals("image")) {
               return ImageIO.read(file);
            }
         }
         throw new Exception("can't found file");
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }

      return null;
   }

   /**
   * load the image as BufferedImage
   * @param path path of the file
   * @return loaded image as BufferedImage
   */
   public static BufferedImage loadImage(String path) {
      return (BufferedImage) basicLoader(path, "image");
   }

   /**
   * load the audio as Clip
   * @param path path of the file
   * @return loaded audio as Clip
   */
   public static Clip loadAudio(String path) {
      return (Clip) basicLoader(path, "audio");
   }

   /**
   * check if the path exist
   * @param path path of the file
   * @return boolean if the path exist
   */
   public static boolean checkPath(String path) {
      File file = new File(path);
      if (file.exists()) {
         return true;
      }
      return false;
   }
}
