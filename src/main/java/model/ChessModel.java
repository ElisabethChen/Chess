package main.java.model;

import java.awt.Point;

import main.java.enums.PieceType;
import main.java.enums.PlayerType;
import main.java.model.pieces.Piece;
import main.java.model.pieces.movements.Movement;
import main.java.model.pieces.movements.MovementComposite;
import main.java.model.pieces.movements.PawnForwardMovement;

/**
 * Handle all calculation of chess
 */
public class ChessModel {
    private PlayerType playingPlayer = PlayerType.Player1;
    private PlayerType winner;
    private Tile[][] board = new Tile[8][8];
    private Piece eatenPiece = null;
    private Point markedPiecePoint = null;
    private String notifMsg;

    public ChessModel() {
        createBoard();
        createPieces();
    }

    // METHODS USED TO CREATE COMPONENT
    private void createBoard() {
        for (int row = 0; row < this.board.length; ++row) {
            for (int col = 0; col < this.board[0].length; ++col) {
                Tile tile = new Tile(row, col);
                this.board[row][col] = tile;
            }
        }
    }

    private void createPieces() {
        for (PlayerType player : PlayerType.values()) {
            int[] rows = player == PlayerType.Player2 ? new int[] { 0, 1 } : new int[] { 7, 6 };

            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                for (int column = 0; column < this.board[0].length; ++column) {
                    Tile tile = this.board[rows[rowIdx]][column];
                    PieceType pieceType;
                    if (rowIdx == 1) {
                        pieceType = PieceType.Pawn;
                    } else {
                        pieceType = PieceType.values()[column < PieceType.values().length - 1 ? column
                                : this.board[0].length - 1 - column];
                    }

                    Piece piece = new Piece(player, pieceType);
                    tile.setPiece(piece);
                }
            }
        }
    }

    /**
     * update the chess based on the clicked tile
     * 
     * @param point the point of the clicked tile
     */
    public void update(Point point) {
        if (this.winner == null) {
            Tile tile = board[point.x][point.y];
            Piece piece = tile.piece;
            eatenPiece = null;
            notifMsg = null;
            if (tile.movable) {
                if (piece == null || piece.getPieceOwner() != playingPlayer) {
                    movePiece(markedPiecePoint, point);
                }
            } else {
                if (piece == null) {
                    notifMsg = "It's not a valid move";
                } else if (piece.getPieceOwner() == playingPlayer) {
                    resetMovable();
                    this.markedPiecePoint = point;
                    // show movable tiles
                    piece.pieceMovement(board, playingPlayer, this.markedPiecePoint);
                } else {
                    notifMsg = "It's not your pieces";
                }
            }
        }
    }

    // METODS USED IN UPDATE

    /**
    * move a piece from the fromPoint to the toPoint
    * @param fromPoint original piece location
    * @param toPoint destination piece location
    */
    private void movePiece(Point fromPoint, Point toPoint) {
        Piece piece = this.board[fromPoint.x][fromPoint.y].getPiece();
        if (piece != null) {
            this.board[fromPoint.x][fromPoint.y].removePiece();

            Tile destTile = this.board[toPoint.x][toPoint.y];
            eatenPiece = destTile.getPiece();
            destTile.setPiece(piece);

            checkPawn(destTile);
            this.markedPiecePoint = null;
            resetMovable();
            checkWinner();
            this.playingPlayer = this.playingPlayer == PlayerType.Player1 ? PlayerType.Player2
                    : PlayerType.Player1;
            checkCheckmate();
        }
    }

    /**
    * set all tiles' movable to false 
    */
    private void resetMovable() {
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                tile.setMovable(false);
            }
        }
    }

    /**
    * if there is a pawn on the tile, it will set the first click to true
    * and promote if possible
    * @param tile the given tile
    */
    private void checkPawn(Tile tile) {
        Piece piece = tile.getPiece();
        if (piece != null && piece.getPieceType() == PieceType.Pawn) {
            setFirstMoveFalse(piece);
            checkPromotion(tile);
        }
    }

    /**
    * set the pawn's first move to ture
    * @param piece the pawn piece
    */
    private void setFirstMoveFalse(Piece piece) {
        if (piece.getPieceType() == PieceType.Pawn) {
            MovementComposite pawnMovements = (MovementComposite) piece.getMovement();
            for (Movement pawnMovement : pawnMovements.getMovements()) {
                if (pawnMovement instanceof PawnForwardMovement) {
                    PawnForwardMovement pawnFMovement = (PawnForwardMovement) pawnMovement;
                    if (pawnFMovement.isFirstMove()) {
                        pawnFMovement.setFirstMove(false);
                    }
                }
            }
        }
    }

    /**
    * promote the pawn piece on the tile if possible
    * @param tile given tile
    */
    private void checkPromotion(Tile tile) {
        PlayerType pieceOwner = tile.getPiece().getPieceOwner();
        int row = 7 * pieceOwner.ordinal();
        if (tile.getRow() == row) {
            tile.setPiece(new Piece(pieceOwner, PieceType.Queen));
        }
    }

    /**
    * set the winner as the winner player if the opposing player's 
    * king is eaten
    */
    private void checkWinner() {
        if (eatenPiece != null && eatenPiece.getPieceType() == PieceType.King) {
            this.winner = PlayerType.values()[1 - eatenPiece.getPieceOwner().ordinal()];
        }
    }

    /**
    * check if it's checkmate. if true, then will notify it by setting
    * a message on the notifMsg
    */
    private void checkCheckmate() {
        Point[] kingsLoc = new Point[2];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Piece piece = board[row][col].getPiece();
                if (piece != null) {
                    piece.pieceMovement(board, piece.getPieceOwner(), new Point(row, col));
                    if (piece.getPieceType() == PieceType.King) {
                        kingsLoc[piece.getPieceOwner().ordinal()] = new Point(row, col);
                    }
                }
            }
        }
        checkKingDanger(kingsLoc);
        resetMovable();
    }

    /**
    * check if the king is in danger
    * @param kingLoc point array of king locations
    */
    private void checkKingDanger(Point[] kingsLoc) {
        boolean oneKingDanger = false;
        for (Point point : kingsLoc) {
            if (point != null) {
                Tile tile = board[point.x][point.y];
                if (tile.getMovable()) {
                    this.notifMsg = oneKingDanger ? "Both kings are threatened"
                            : tile.getPiece().getPieceOwner().getTitle() + "'s king is threatened";
                    oneKingDanger = true;
                }
            }
        }
    }

    // GETTERS
    public Tile[][] getBoard() {
        return board;
    }

    public Piece getEatenPiece() {
        return eatenPiece;
    }

    public String getNotifyMsg() {
        return notifMsg;
    }

    public PlayerType getPlayingPlayer() {
        return playingPlayer;
    }

    public PlayerType getWinner() {
        return winner;
    }
}
