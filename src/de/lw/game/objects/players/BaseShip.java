package de.lw.game.objects.players;

import de.lw.game.objects.projectiles.BaseProjectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class BaseShip {

    Image img;
    double hp;
    double posX;
    double posY;
    double sizeX;
    double sizeY;
    double speedX;
    double speedY;

    protected final List<BaseProjectile> shotsFired = new ArrayList<>(64);

    public BaseShip(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void move() {
        posX = posX + getSpeedX();
        posY = posY + getSpeedY();
    }

    public boolean outOfArea(double posX, double posY, double width, double height) {
        return getPosX() + getSizeX() >= width + posX || getPosX() <= posX || getPosY() + getSizeY() >= height + posY
                || getPosY() <= posY;
    }


    public boolean outOfAreaRight(double posX, double width) {
        return getPosX() + getSizeX() >= width + posX;
    }

    public boolean outOfAreaLeft(double posX) {
        return getPosX() <= posX;
    }

    public boolean outOfAreaBottom(double posY, double height) {
        return getPosY() + getSizeY() >= height + posY;
    }

    public boolean outOfAreaTop(double posY) {
        return getPosY() <= posY;
    }


    public boolean hitByProjectile(BaseProjectile p) {
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public boolean inBounds(double x, double y, double sizeX, double sizeY) {
        return getPosX() < x + sizeX && getPosX() + getSizeX() > x && getPosY() < y + sizeY
                && getPosY() + getSizeY() > y;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public double getPosXMiddle() {
        return posX + sizeX / 2;
    }

    public double getPosYMiddle() {
        return posY + sizeY / 2;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void shoot() {}

    public List<BaseProjectile> getShotsFired() {
        return shotsFired;
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

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

}
