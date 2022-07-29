package main.java.run;

import main.java.controller.ChessController;

/**
* run the chess game
*/
public class Chess {
    public static void main(String[] args) {
        ChessController chess = new ChessController();
        chess.start();
    }
}
