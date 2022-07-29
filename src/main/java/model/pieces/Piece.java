package main.java.model.pieces;

import main.java.enums.PieceType;
import main.java.enums.PlayerType;
import main.java.filesmgmt.Assets;

import java.awt.Point;
import java.awt.image.BufferedImage;

import main.java.model.Tile;
import main.java.model.pieces.movements.BishopMovement;
import main.java.model.pieces.movements.KingMovement;
import main.java.model.pieces.movements.KnightMovement;
import main.java.model.pieces.movements.Movement;
import main.java.model.pieces.movements.MovementComposite;
import main.java.model.pieces.movements.PawnEatMovement;
import main.java.model.pieces.movements.PawnForwardMovement;
import main.java.model.pieces.movements.RookMovement;

/**
* class of a Piece with corresponding movement based of it's type
*/
public class Piece {
   private PieceType pieceType;
   private PlayerType PieceOwner;
   private BufferedImage image;
   protected Movement movement;

   public Piece(PlayerType pieceOwner, PieceType piecesType) {
      this.PieceOwner = pieceOwner;
      this.pieceType = piecesType;
      this.image = Assets.getPiecesImage()[pieceOwner.ordinal()][piecesType.ordinal()];
      this.setMovement(piecesType);
   }

   /**
   * set the movement base on the piece type
   * @param pieceType the type of the piece
   */
   private void setMovement(PieceType pieceType) {
      switch (pieceType) {
         case Rook:
            this.movement = new RookMovement();
            break;
         case Knight:
            this.movement = new KnightMovement();
            break;
         case Bishop:
            this.movement = new BishopMovement();
            break;
         case Queen:
            MovementComposite queenMovement = new MovementComposite();
            queenMovement.addMovement(new RookMovement());
            queenMovement.addMovement(new BishopMovement());
            this.movement = queenMovement;
            break;
         case King:
            this.movement = new KingMovement();
            break;
         case Pawn:
            MovementComposite pawnMovement = new MovementComposite();
            pawnMovement.addMovement(new PawnEatMovement());
            pawnMovement.addMovement(new PawnForwardMovement());
            this.movement = pawnMovement;
      }
   }

   /**
    * set the tiles that this piece can move to as moveble.
    * 
    * @param board  include all tiles
    * @param player the playing player
    * @param point  the coordinate of where on the board the given piece is
    */
   public void pieceMovement(Tile[][] board, PlayerType playingPlayer, Point point) {
      this.movement.pieceMovement(board, playingPlayer, point);
   }

   public PieceType getPieceType() {
      return this.pieceType;
   }

   public PlayerType getPieceOwner() {
      return this.PieceOwner;
   }

   public BufferedImage getImage() {
      return image;
   }

   public Movement getMovement() {
      return this.movement;
   }
}
