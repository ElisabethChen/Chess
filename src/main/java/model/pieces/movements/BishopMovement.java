package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;

/**
* move diagonal in infinity many steps
*/
public class BishopMovement extends Movement {

   public BishopMovement(){
      this.directions =  new Point[] { new Point(-1, -1), new Point(1, -1), new Point(-1, 1), new Point(1, 1) };
   }

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      setMovableTiles(board, player, point, this.directions, -1);
   }
}
