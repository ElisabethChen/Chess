package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controller.ChessController.TileListener;
import main.java.enums.PieceType;
import main.java.enums.PlayerType;
import main.java.filesmgmt.Assets;
import main.java.filesmgmt.ImageEditor;
import main.java.model.ChessModel;
import main.java.model.Tile;
import main.java.model.pieces.Piece;

/**
 * handle all graphic view of chess
 */
public class ChessView extends FrameTemplate {
    // lengths
    private final static int width = 900;
    private final static int height = 600;
    private final int pPanelHgap = width / 32;
    private final int pPanelVgap = height / 20;
    private final int pPanelWidth = width / 4 - pPanelHgap * 2;
    private final int pPanelHeight = height - pPanelVgap * 2;
    private final int labelHeight = height / 7;
    private final int eatenPanelHeight = pPanelHeight - labelHeight;
    private final int cPanelWidth = width / 2;
    private final int boardSize = height - 2 * labelHeight;

    // values that changes when update
    private TilePanel[][] boardView = new TilePanel[8][8]; // TileView[row][column]
    private JLabel[][] eatenPieces = new JLabel[2][16]; // includes pieces eaten by player 1 resp. player 2
    private JLabel notifyMsg;
    private JLabel turnLabel;

    public ChessView() {
        super(width, height, "Chess");
        Assets.init();

        mainPanel.setLayout(new BorderLayout());
        JPanel bgPanel = new BackgroundPanel(Assets.getBgImage(), panelW, panelH);
        bgPanel.setLayout(new FlowLayout(FlowLayout.CENTER, pPanelHgap, 0));
        JPanel leftPanel = createPlayerPanel(PlayerType.Player1); // includes pieces eaten by Player 1 (i.e.
                                                                  // eatenPieces[0])
        JPanel rightPanel = createPlayerPanel(PlayerType.Player2); // includes pieces eaten by Player 2 (i.e.
                                                                   // eatenPieces[1])
        JPanel centerPanel = createCenterPanel(); // includes board, turnLabel and notifyMsg

        bgPanel.add(leftPanel);
        bgPanel.add(centerPanel);
        bgPanel.add(rightPanel);
        mainPanel.add(bgPanel, BorderLayout.CENTER);
    }

    // METHODS USED TO CREATE COMPONENT

    /**
     * create a panel that includes the player's title and the pieces that was eaten
     * by this player
     * 
     * @param player Which player the panel is created for
     * @return the created panel
     */
    private JPanel createPlayerPanel(PlayerType player) {
        JPanel playerPanel = createFlowPanel(pPanelWidth, pPanelHeight, 0, 0);
        JPanel eatenPanel = createEatenPanel(player);
        JLabel title = createLabel(player.getTitle(), player.getColor(), Assets.getMediumSize(), pPanelWidth,
                labelHeight);

        Border border = BorderFactory.createLineBorder(player.getColor(), 5);
        playerPanel.setBorder(border);
        eatenPanel.setBorder(border);

        playerPanel.add(title);
        playerPanel.add(eatenPanel);
        return playerPanel;
    }

    /**
     * create a panel that shows the pieces that was eaten
     * by the given player
     * 
     * @param player given player
     * @return the created panel
     */
    private JPanel createEatenPanel(PlayerType player) {
        JPanel eatenPanel = new JPanel();
        eatenPanel = new JPanel(new GridLayout(8, 2));
        eatenPanel.setPreferredSize(new Dimension(pPanelWidth, eatenPanelHeight));
        eatenPanel.setOpaque(false);

        for (int i = 0; i < eatenPieces[0].length; i++) {
            JLabel pieceLabel = new JLabel();
            pieceLabel.setHorizontalAlignment(JLabel.CENTER);
            eatenPieces[player.ordinal()][i] = pieceLabel;
            eatenPanel.add(pieceLabel);
        }

        return eatenPanel;
    }

    /**
     * create a panel that includes the board and labels that shows the
     * playing player and notifications
     * 
     * @param player Which player the panel is created for
     * @return the created panel
     */
    private JPanel createCenterPanel() {
        JPanel centerPanel = createFlowPanel(cPanelWidth, height, 0, 0);
        JPanel boardPanel = createBoard();
        turnLabel = createLabel(PlayerType.Player1.getTitle() + "'s turn", PlayerType.Player1.getColor(),
                Assets.getLargeSize(), cPanelWidth, labelHeight);
        notifyMsg = createLabel("", Assets.getErrorColor(), Assets.getMediumSize(), cPanelWidth, labelHeight);

        centerPanel.add(turnLabel);
        centerPanel.add(boardPanel);
        centerPanel.add(notifyMsg);
        return centerPanel;
    }

    /**
     * create a transparent panel with the layout FlowLayout
     * 
     * @param width  width of the panel
     * @param height height of the panel
     * @param hgap   flowlayout's horizontal gap between components and between the
     *               components and the borders of the Container
     * @param vgap   flowlayout's vertical gap between components and between the
     *               components and the borders of the Container
     * @return the created panel
     */
    private JPanel createFlowPanel(int width, int height, int hgap, int vgap) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false);
        return panel;
    }

    /**
     * create a label with given parameters
     * 
     * @param text     text of the label
     * @param color    color of the text
     * @param fontSize size of the font
     * @param width    width of the label
     * @param height   height of the label
     * @return the created label
     */
    private JLabel createLabel(String text, Color color, int fontSize, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Algerian", Font.BOLD, fontSize));
        label.setPreferredSize(new Dimension(width, height));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }

    /**
     * create board panel
     * 
     * @return board panel
     */
    private JPanel createBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8, 0, 0));
        boardPanel.setPreferredSize(new Dimension(boardSize, boardSize));
        boardPanel.setOpaque(false);

        for (int row = 0; row < boardView.length; row++) {
            for (int col = 0; col < boardView[1].length; col++) {
                Color color = (row + col) % 2 == 0 ? Assets.getTileColorW() : Assets.getTileColorB();
                boardView[row][col] = new TilePanel(color, new Point(row, col), boardSize / 8);
                boardPanel.add(boardView[row][col]);
            }
        }
        return boardPanel;
    }

    // METHODS USED TO UPDATE THE VIEW
    /**
     * add another pieces to the eaten piece
     * 
     * @param pieceType the eaten piece type
     * @param player    the player that own the eaten pieces
     */
    private void addEatenPieces(PieceType pieceType, PlayerType player) {
        BufferedImage pieceImg = Assets.getPieceImage(pieceType, player);
        pieceImg = ImageEditor.resizeImgH(pieceImg, eatenPanelHeight / 8);

        for (int column = 0; column < eatenPieces[0].length; ++column) {
            JLabel pieceLabel = eatenPieces[(player.ordinal() + 1) % 2][column];
            if (pieceLabel.getIcon() == null) {
                pieceLabel.setIcon(new ImageIcon(pieceImg));
                break;
            }
        }
    }

    /**
     * update the board according to the model
     * 
     * @param board includes the board informations
     */
    private void updateBoard(Tile[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Tile tileModel = board[row][col];
                TilePanel tileView = boardView[row][col];
                if (tileModel.getPiece() != null) {
                    tileView.setPiece(tileModel.getPiece().getImage());
                } else {
                    tileView.removePiece();
                }
                tileView.setMovable(board[row][col].getMovable());
            }
        }
    }

    /**
     * update the turnLabel based on the player
     * 
     * @param player the playing player
     */
    private void updateTurnLabel(PlayerType player) {
        this.turnLabel.setText(player.getTitle() + "'s turn");
        this.turnLabel.setForeground(player.getColor());
    }

    /**
     * create and show the end window that shows the winner player
     * 
     * @param player the winner player
     */
    private void showEndWindow(PlayerType player) {
        int width = 600;
        int height = 400;
        FrameTemplate endWindow = new FrameTemplate(width, height);
        JPanel mainPanel = endWindow.mainPanel;
        mainPanel.setBackground(Assets.getMainColor());

        String text = String.format("<html><p style=\"text-align:center;\">Congratulations! <br>%s won!<p></html>",
                player.getTitle());
        JLabel msg = createLabel(text, player.getColor(), Assets.getLargeSize(), width, height);

        mainPanel.add(msg);
        endWindow.display();
    }

    /**
     * update the Chess View based on the model
     * 
     * @param cModel includes all information about the chess model
     */
    public void update(ChessModel cModel) {
        updateBoard(cModel.getBoard());
        Piece eatenPiece = cModel.getEatenPiece();
        if (eatenPiece != null) {
            addEatenPieces(eatenPiece.getPieceType(), eatenPiece.getPieceOwner());
        }
        if (cModel.getWinner() != null) {
            showEndWindow(cModel.getWinner());
        }
        this.notifyMsg.setText(cModel.getNotifyMsg());
        updateTurnLabel(cModel.getPlayingPlayer());
    }

    /**
     * add a TileListener for every tile on the board
     * 
     * @param tileListener the added listener
     */
    public void addTileListener(TileListener tileListener) {
        for (TilePanel[] row : boardView) {
            for (TilePanel tile : row) {
                tile.addMouseListener(tileListener);
            }
        }
    }
}
