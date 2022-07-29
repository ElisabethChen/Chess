package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;

/**
* move indefinitely in the horizontally and vertical directions
*/
public class RookMovement extends Movement {

   public RookMovement(){
      this.directions = new Point[] { new Point(-1, 0), new Point(0, -1), new Point(0, 1), new Point(1, 0)};
   }

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      setMovableTiles(board, player, point, this.directions, -1);
   }
}
