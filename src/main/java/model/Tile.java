package main.java.model;

import main.java.model.pieces.Piece;
import java.awt.Point;

/**
* class of Tile
*/
public class Tile {
    protected boolean movable = false;
    protected Piece piece;
    protected int row;
    protected int col;

    Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    protected void setPiece(Piece piece) {
        this.piece = piece;
    }

    protected void removePiece() {
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setMovable(Boolean movable) {
        this.movable = movable;
    }
    
    public Boolean getMovable() {
        return movable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public Point getPoint() {
        return new Point(row, col);
    }
}
