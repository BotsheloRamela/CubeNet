package org.example;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlayerSprite {

    private double x;
    private double y;
    private final double size;
    private final Color color;

    public PlayerSprite(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void drawSprite(Graphics2D graphics2D) {
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, size, size);
        graphics2D.setColor(color);
        graphics2D.fill(square);
    }

    public void moveHorizontal(double speed) {
        x += speed;
    }

    public void moveVertical(double speed) {
        y += speed;
    }

    public void setX(double n) {
        x = n;
    }

    public void setY(double n) {
        y = n;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
