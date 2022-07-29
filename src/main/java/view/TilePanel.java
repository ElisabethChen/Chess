package main.java.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.enums.PieceType;
import main.java.enums.PlayerType;
import main.java.filesmgmt.Assets;
import main.java.filesmgmt.ImageEditor;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * class for tile view
 */
public class TilePanel extends JPanel {
    private Point coordinate;
    private int tileSize;
    private ImageIcon pieceImg;
    private ImageIcon movableImg;
    private boolean movable = false;
    private JLabel pieceLabel = new JLabel();
    private JLabel movableLabel = new JLabel();

    TilePanel(Color color, Point location, int tileSize) {
        this.coordinate = location;
        this.tileSize = tileSize;
        this.movableImg = ImageEditor.toImageIconH(Assets.getDotImage(), tileSize);

        this.setBackground(color);
        // this.setOpaque(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbConstraints = new GridBagConstraints();
        this.addLabel(movableLabel, gbConstraints);
        this.addLabel(pieceLabel, gbConstraints);
    }

    /**
     * add the label on each other
     * 
     * @param label the given label
     */
    private void addLabel(JLabel label, GridBagConstraints gbConstraints) {
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        this.add(label, gbConstraints);
    }

    /**
     * set the given piece on the panel
     * 
     * @param piceType  type of given piece
     * @param piceOwner owner of given piece
     */
    public void setPiece(PieceType pieceType, PlayerType pieceOwner) {
        BufferedImage buffImg = Assets.getPieceImage(pieceType, pieceOwner);
        this.pieceImg = ImageEditor.toImageIconH(buffImg, tileSize);
        this.pieceLabel.setIcon(this.pieceImg);
    }

    /**
     * set the given piece on the panel
     * 
     * @param piceImg image of given piece
     */
    public void setPiece(BufferedImage pieceImg) {
        this.pieceImg = ImageEditor.toImageIconH(pieceImg, tileSize);
        this.pieceLabel.setIcon(this.pieceImg);
    }

    /**
    * remove the piece on the panel
    */
    public void removePiece() {
        this.pieceLabel.setIcon(null);
    }

    public Point getCoord() {
        return coordinate;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
        if (this.movable) {
            this.movableLabel.setIcon(movableImg);
        } else {
            this.movableLabel.setIcon(null);
        }
    }

}
