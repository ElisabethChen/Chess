package main.java.controller;

import main.java.model.ChessModel;
import main.java.view.ChessView;
import main.java.view.TilePanel;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

/**
 * Controller of Chess. Connect the model and the view.
 */
public class ChessController {
    private ChessView cView;
    private ChessModel cModel;

    public ChessController() {
        this.cView = new ChessView();
        this.cModel = new ChessModel();

        cView.addTileListener(new TileListener());
        cView.update(cModel);
    }

    public void start() {
        cView.display();
    }

    /**
     * Listen to click on the tiles. Update the model and the view
     */
    public class TileListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof TilePanel) {
                if (cModel.getWinner() == null) {
                    TilePanel tile = (TilePanel) e.getSource();
                    cModel.update(tile.getCoord());
                    cView.update(cModel);
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
