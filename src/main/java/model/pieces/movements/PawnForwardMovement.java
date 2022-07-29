package main.java.model.pieces.movements;

import main.java.enums.PlayerType;
import main.java.model.Tile;

import java.awt.Point;

/**
* move one step forward if it's the first move of the piece
*/
public class PawnForwardMovement extends ForwardMovement {
   private boolean firstMove = true;

   public PawnForwardMovement() {
      this.directions = new Point[] { new Point(-1, 0) };
   }

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      int step = 1;
      if (firstMove) {
         step = 2;
      }
      setMovableTiles(board, player, point, getDirections(player), step);
   }

   @Override
   protected void setMovableTiles(Tile[][] board, PlayerType player, Point point, Point[] directions, int steps) {
       Point newPoint = point;
       for (int i = 0; i < steps; i++) {
           newPoint = new Point(newPoint.x+directions[0].x, newPoint.y+directions[0].y);
           if (inBoard(newPoint) && board[newPoint.x][newPoint.y].getPiece() == null) {
            board[newPoint.x][newPoint.y].setMovable(true);
           } else {
            break;
           }
       }
   }

   public boolean isFirstMove() {
      return firstMove;
   }

   public void setFirstMove(boolean firstMove) {
      this.firstMove = firstMove;
   }
}
