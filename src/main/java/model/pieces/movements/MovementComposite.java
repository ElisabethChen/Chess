package main.java.model.pieces.movements;

import main.java.enums.PlayerType;
import main.java.model.Tile;

import java.awt.Point;
import java.util.ArrayList;

/**
* composite of several Movements
*/
public class MovementComposite extends Movement {

   private ArrayList<Movement> movements = new ArrayList<Movement>();

   @Override
   public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
      for (Movement movement : movements) {
         movement.pieceMovement(board, player, point);
      }
   }

   public void addMovement(Movement movement) {
      this.movements.add(movement);
   }

   public Movement getMovement(int index) {
      return (Movement) this.movements.get(index);
   }

   public ArrayList<Movement> getMovements() {
      return this.movements;
   }
}
