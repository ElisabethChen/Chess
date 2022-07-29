package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;

/**
* move one step in each directions
*/
public class KingMovement extends Movement {
   public KingMovement() {
      this.directions = getDirections();
   }

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      setMovableTiles(board, player, point, this.directions, 1);
   }

   /**
   * get the directions of King's movement
   * @return directions
   */
   private Point[] getDirections() {
      Point[] directions = new Point[8];
      int idx = 0;
      for (int row = -1; row <= 1; row++) {
         for (int col = -1; col <= 1; col++) {
            if (!(row == 0 && col == 0)) {
               directions[idx++] = new Point(row, col);
            }
         }
      }
      return directions;
   }
}
