package de.lw.game.objects.projectiles;

import java.awt.*;

public class BaseProjectile {

    double posX;
    double posY;
    double SizeX;
    double SizeY;
    double speedX;
    double speedY;
    Image img;

    public BaseProjectile(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void move() {
        posX = posX + getSpeedX();
        posY = posY + getSpeedY();
    }

    public boolean outOfMap(double posX, double posY, double width, double height) {
        return getPosX() + getSizeX() >= width + posX || getPosX() <= posX || getPosY() + getSizeY() >= height + posY
                || getPosY() <= posY;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSizeX() {
        return SizeX;
    }

    public void setSizeX(double sizeX) {
        SizeX = sizeX;
    }

    public double getSizeY() {
        return SizeY;
    }

    public void setSizeY(double sizeY) {
        SizeY = sizeY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void shipPosition(double x, double y) {

    }
}