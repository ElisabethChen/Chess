package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;
import main.java.model.pieces.Piece;

/**
 * template for movement of different pieces
 */
public abstract class Movement {
   Point[] directions;

   /**
    * set the tiles that the given piece can move to as moveble.
    * 
    * @param board  include all tiles
    * @param player the playing player
    * @param point  the coordinate of where on the board the given piece is
    */
   public abstract void pieceMovement(Tile[][] board, PlayerType player, Point point);

   /**
    * set the tiles that the given piece can move to as moveble.
    * 
    * @param board  include all tiles
    * @param player the playing player
    * @param point  the coordinate of where on the board the given piece is
    * @param directions the directions of where the piece can move  
    * @param steps how many steps in each directions the piece can move, -1 is equal to infinity
    */
   protected void setMovableTiles(Tile[][] board, PlayerType player, Point point, Point[] directions, int steps) {
      for (Point direct : directions) {
         int counter = 0;
         Point nextLoc = new Point(point.x + direct.x, point.y + direct.y);
         while (inBoard(nextLoc)) {
            counter++;
            Tile tile = board[nextLoc.x][nextLoc.y];
            Piece piece = tile.getPiece();
            if (piece == null || piece.getPieceOwner() != player) {
               tile.setMovable(true);
            }
            if (piece != null || counter == steps) {
               break;
            }
            nextLoc = new Point(nextLoc.x + direct.x, nextLoc.y + direct.y);
         }
      }
   }

   /**
   * check if the given point is within the board
   * @param point the given point
   * @return boolean
   */
   protected boolean inBoard(Point point) {
      return 0 <= point.x && point.x < 8 && 0 <= point.y && point.y < 8;
   }
}
