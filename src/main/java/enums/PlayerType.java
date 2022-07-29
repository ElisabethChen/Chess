package main.java.enums;

import java.awt.Color;

import main.java.filesmgmt.Assets;

public enum PlayerType {
   Player1(Assets.getP1Color(), "Player 1"),
   Player2(Assets.getP2Color(), "Player 2");

   private Color color;    // Color for each Player
   private String title;   // Displayed name for each Player

   private PlayerType(Color playerColor, String title) {
      this.color = playerColor;
      this.title = title;
   }

   public Color getColor() {
      return this.color;
   }

   public String getTitle() {
      return this.title;
   }
}
