package main.java.model.pieces.movements;

import main.java.enums.PlayerType;

import java.awt.Point;

/**
 * movements that only move in forward directions (including diagonal forward
 * movements)
 */
public abstract class ForwardMovement extends Movement {
    /**
     * get the directions for pieces that change direction based on the player
     * 
     * @param player the playing player
     * @return directions
     */
    public Point[] getDirections(PlayerType player) {
        Point[] directions = this.directions.clone();
        if (player == PlayerType.Player2) {
            for (int i = 0; i < directions.length; i++) {
                directions[i] = new Point(this.directions[i].x * -1, this.directions[i].y);
            }
        }
        return directions;
    }
}
