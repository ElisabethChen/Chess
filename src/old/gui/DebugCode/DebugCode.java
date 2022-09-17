package old.gui.DebugCode;

import java.awt.Point;

import main.java.enums.PlayerType;
import main.java.model.ChessModel;
import main.java.model.Tile;

public class DebugCode {
    Point[] directions;
    ChessModel cModel;

    public void pieceMovement2(Tile[][] board, PlayerType player, Point clickedLoc) {
        Point[] directions = { new Point(0, 1), new Point(0, -1),
                new Point(1, 0), new Point(-1, 0) };

        for (Point direct : directions) {
            Point nextLoc = new Point(clickedLoc.x + direct.x, clickedLoc.y + direct.y);
            while (0 <= nextLoc.x && nextLoc.x < board.length && 0 <= nextLoc.y && nextLoc.y < board.length) {
                Tile tile = board[nextLoc.x][nextLoc.y];
                // setMovable(tile, player);
                if (tile.getPiece() != null) {
                    break;
                }
                nextLoc = new Point(nextLoc.x + direct.x, nextLoc.y + direct.y);
            }
        }
    }

    public void getAvaibleTile(Point clickedLoc, Point direct, Tile[][] board, PlayerType player) {
        Point nextLoc = new Point(clickedLoc.x + direct.x, clickedLoc.y + direct.y);
        if (0 <= nextLoc.x && nextLoc.x < board.length && 0 <= nextLoc.y && nextLoc.y < board.length) {
            Tile tile = board[nextLoc.x][nextLoc.y];
            if (tile.getPiece() == null || tile.getPiece().getPieceOwner() != player) {
                tile.setMovable(true);
            }
        }
    }

    public void printDirect() {
        for (Point point : directions) {
            System.out.println(point.x + " " + point.y);
        }
    }

    // public void name() {
    // while (0 <= nextLoc.x && nextLoc.x < board.length && 0 <= nextLoc.y &&
    // nextLoc.y < board.length) {
    // getAvaibleTile(clickedLoc, direct, board, player);
    // clickedLoc++;
    // if (board[clickedLoc].getPiece != null) {
    // break;
    // }
    // }
    // }

    // public void name(int steps) {
    // int counter = 0;
    // while (0 <= nextLoc.x && nextLoc.x < board.length && 0 <= nextLoc.y &&
    // nextLoc.y < board.length) {
    // getAvaibleTile(clickedLoc, direct, board, player);
    // clickedLoc++;
    // if (board[clickedLoc].getPiece != null || counter == steps) {
    // break;
    // }
    // }
    // }

    // public void directionsF() {
    // for (Point direct : directions) {
    // name
    // }
    // }

    
    public void displayBoard() {
        System.out.println("\nModel");
        String s = "| ";
        int boardLength = cModel.getBoard().length;
  
        for(int idx = 0; idx < boardLength; ++idx) {
           Tile[] tiles = cModel.getBoard()[idx];
  
           for(int tIdx = 0; tIdx < tiles.length; ++tIdx) {
              Tile tile = tiles[tIdx];
              s = s + tile.getMovable() + " | ";
           }
  
           System.out.println(s);
           s = "| ";
        }
     }
}
