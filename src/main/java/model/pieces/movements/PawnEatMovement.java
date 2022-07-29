package main.java.model.pieces.movements;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.Tile;
import main.java.model.pieces.Piece;

/**
* move one step diagonal forward if there is a piece owned by the opposing player
*/
public class PawnEatMovement extends ForwardMovement {
    public PawnEatMovement(){
        this.directions = new Point[]{new Point(-1, -1), new Point(-1, 1)};
    }

    @Override
    public void pieceMovement(Tile[][] board, PlayerType player, Point point) {
        Point[] directions = getDirections(player);
        for (Point addPoint : directions) {
            Point nextPoint = new Point(point.x + addPoint.x, point.y + addPoint.y);
            if (inBoard(nextPoint) && isEatable(board, player, nextPoint)) {
                board[nextPoint.x][nextPoint.y].setMovable(true);
            }
        }
    }

    /**
    * check if there is a pieces owned by opposing player on the given tile
    * @param board includes all tiles
    * @param player playing player
    * @param point point of the given tile
    * @return boolean
    */
    private boolean isEatable(Tile[][] board, PlayerType player, Point point) {
        Piece piece = board[point.x][point.y].getPiece();
        if (piece != null && piece.getPieceOwner() != player) {
            return true;
        }
        return false;
    }
}
