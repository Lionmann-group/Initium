package de.lw.game.objects.players;

import de.lw.game.objects.projectiles.BaseProjectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class BaseShip {

    Image img;
    int hp;
    double posX;
    double posY;
    private double sizeX, sizeY, speedX, speedY;

    final List<BaseProjectile> shotsFired = new ArrayList<>(64);

    public BaseShip(final double posX, final double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void move() {
        posX = posX + getSpeedX();
        posY = posY + getSpeedY();
    }

    public boolean outOfArea(final double posX, final double posY, final double width, final double height) {
        return getPosX() + getSizeX() >= width + posX || getPosX() <= posX || getPosY() + getSizeY() >= height + posY
                || getPosY() <= posY;
    }


    public boolean outOfAreaRight(final double posX, final double width) {
        return getPosX() + getSizeX() >= width + posX;
    }

    public boolean outOfAreaLeft(final double posX) {
        return getPosX() <= posX;
    }

    public boolean outOfAreaBottom(final double posY, final double height) {
        return getPosY() + getSizeY() >= height + posY;
    }

    public boolean outOfAreaTop(final double posY) {
        return getPosY() <= posY;
    }


    public boolean hitByProjectile(final BaseProjectile p) {
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public boolean inBounds(final double x, final double y, final double sizeX, final double sizeY) {
        return getPosX() < x + sizeX && getPosX() + getSizeX() > x && getPosY() < y + sizeY
                && getPosY() + getSizeY() > y;
    }

    public void draw(final Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public double getPosXMiddle() {
        return posX + sizeX / 2;
    }

    public double getPosYMiddle() {
        return posY + sizeY / 2;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(final int hp) {
        this.hp = hp;
    }

    public void shoot() {}

    public List<BaseProjectile> getShotsFired() {
        return shotsFired;
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

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(final double speedY) {
        this.speedY = speedY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(final Image img) {
        this.img = img;
    }

}
