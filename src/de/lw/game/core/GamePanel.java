package de.lw.game.core;

import java.awt.Graphics2D;
import java.awt.Image;

public class GamePanel {

    private double posX, posY, sizeX, sizeY;
    private Image img;

    public GamePanel(double posX, double posY, double sizeX, double sizeY, Image img) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.img = img;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
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
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

}
