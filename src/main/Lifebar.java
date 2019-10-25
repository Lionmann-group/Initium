package main;

import java.awt.*;

public class Lifebar {

    double positionX;
    double movedPositionY;
    double sizeX;
    double sizeY;
    double maxHp;
    private int seperator;
    Image greenBar;
    Image redBar;

    public Lifebar(double positionX ,double mainPositionY, double sizeX, double maxHp) {
        this.positionX = positionX;
        this.movedPositionY = mainPositionY + 40;
        this.sizeX = sizeX;
        setSizeY(10);
        this.maxHp = maxHp;
        seperator = (int) sizeX;
        greenBar = ImageRepository.getInstance().loadImage("/Colors/Blue.png");
        redBar = ImageRepository.getInstance().loadImage("/Colors/Red.png");
        greenBar = greenBar.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
        redBar = redBar.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(redBar, (int) getPositionX(), (int) getMovedPositionY(), null);
        g2d.drawImage(greenBar, (int) getPositionX(), (int) getMovedPositionY(), null);
    }
    public void sync(double positionX ,double mainPositionY) {
        this.positionX = positionX;
        this.movedPositionY = mainPositionY - 30;
    }
    public void lifebarLoseHp(int dmg) {
        seperator = seperator - dmg;
        greenBar = greenBar.getScaledInstance(seperator + 1, (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getMovedPositionY() {
        return movedPositionY;
    }

    public void setMovedPositionY(double movedPositionY) {
        this.movedPositionY = movedPositionY;
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

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    public Image getGreenBar() {
        return greenBar;
    }

    public void setGreenBar(Image greenBar) {
        this.greenBar = greenBar;
    }

}