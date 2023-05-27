/**
 The PlayerSprite class represents a player sprite object in a 2D game.
 It contains methods for drawing and moving the sprite.
 */

package org.example.cubenet.client;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlayerSprite {
    private double x; // The x-coordinate of the sprite's top-left corner
    private double y; // The y-coordinate of the sprite's top-left corner
    private final double size; // The size of the sprite
    private final Color color; // The color of the sprite

    /**
     * Constructs a PlayerSprite object with the specified coordinates, size, and color.
     * @param x the x-coordinate of the sprite's top-left corner
     * @param y the y-coordinate of the sprite's top-left corner
     * @param size the size of the sprite
     * @param color the color of the sprite
     */
    public PlayerSprite(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    /**
     * Draws the sprite using the specified Graphics2D object.
     * @param graphics2D the Graphics2D object used to draw the sprite
     */
    public void drawSprite(Graphics2D graphics2D) {
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, size, size);
        graphics2D.setColor(color);
        graphics2D.fill(square);
    }

    /**
     * Moves the sprite horizontally by the specified speed.
     * @param speed the speed at which to move the sprite horizontally
     */
    public void moveHorizontal(double speed) {
        x += speed;
    }

    /**
     * Moves the sprite vertically by the specified speed.
     * @param speed the speed at which to move the sprite vertically
     */
    public void moveVertical(double speed) {
        y += speed;
    }

    /**
     * Sets the x-coordinate of the sprite's top-left corner to the specified value.
     * @param n the value to set the x-coordinate to
     */
    public void setX(double n) {
        x = n;
    }

    /**
     * Sets the y-coordinate of the sprite's top-left corner to the specified value.
     * @param n the value to set the y-coordinate to
     */
    public void setY(double n) {
        y = n;
    }

    /**
     * Returns the x-coordinate of the sprite's top-left corner.
     * @return the x-coordinate of the sprite's top-left corner
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the sprite's top-left corner.
     * @return the y-coordinate of the sprite's top-left corner
     */
    public double getY() {
        return this.y;
    }
}
