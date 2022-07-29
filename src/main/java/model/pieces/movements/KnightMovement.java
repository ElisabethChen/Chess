package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;

/**
* moves in a "L-shape", i.e. two step in one direction and one step in
* the direction with right angle to the previous direction.
*/
public class KnightMovement extends Movement {
   public KnightMovement() {
      this.directions = getDirections();
   }

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      setMovableTiles(board, player, point, this.directions, 1);
   }

   /**
    * get the directions of Knight's movement
    * 
    * @return directions
    */
   private Point[] getDirections() {
      Point[] directions = new Point[2 * 2 * 2];
      int idx = 0;
      for (int var1 = -2; var1 <= 2; var1 += 4) {
         for (int var2 = -1; var2 <= 1; var2 += 2) {
            directions[idx++] = new Point(var1, var2);
            directions[idx++] = new Point(var2, var1);
         }
      }
      return directions;
   }
}
