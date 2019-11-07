package de.lw.game.objects.projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class BaseProjectile {

    double posX;
    double posY;
    Image img;

    private double sizeX, sizeY, speedX, speedY;

    public BaseProjectile(final double posX, final double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void move() {
        posX = posX + getSpeedX();
        posY = posY + getSpeedY();
    }

    public boolean outOfMap(final double posX, final double posY, final double width, final double height) {
        return getPosX() + getSizeX() >= width + posX ||
                getPosX() <= posX ||
                getPosY() + getSizeY() >= height + posY ||
                getPosY() <= posY;
    }

    public void draw(final Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(final double speedY) {
        this.speedY = speedY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(final double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(final double posY) {
        this.posY = posY;
    }

    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(final double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(final double sizeY) {
        this.sizeY = sizeY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(final double speedX) {
        this.speedX = speedX;
    }

    public abstract void shipPosition(final double x, final double y);

}
