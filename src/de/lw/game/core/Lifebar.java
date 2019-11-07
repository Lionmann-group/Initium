package de.lw.game.core;

import java.awt.Graphics2D;
import java.awt.Image;

public class Lifebar {

    private double positionX, movedPositionY, sizeX, sizeY;
    private int maxHp, seperator;
    private Image greenBar, redBar;

    public Lifebar(final double positionX, final double mainPositionY, final double sizeX, final int maxHp) {
        this.positionX = positionX;
        this.movedPositionY = mainPositionY + 40;
        this.sizeX = sizeX;
        setSizeY(10);
        this.maxHp = maxHp;
        seperator = (int) sizeX;
        greenBar = ImageRepository.getInstance().loadImage("/Colors/Blue.png");
        redBar = ImageRepository.getInstance().loadImage("/Colors/Red.png");
        greenBar = greenBar.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
        redBar = redBar.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(redBar, (int) getPositionX(), (int) getMovedPositionY(), null);
        g2d.drawImage(greenBar, (int) getPositionX(), (int) getMovedPositionY(), null);
    }

    public void setPosition(final double positionX, final double mainPositionY) {
        this.positionX = positionX;
        this.movedPositionY = mainPositionY - 30;
    }

    public void lifebarLoseHp(final int dmg) {
        seperator = seperator - dmg;
        greenBar = greenBar.getScaledInstance(seperator + 1, (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(final double positionX) {
        this.positionX = positionX;
    }

    public double getMovedPositionY() {
        return movedPositionY;
    }

    public void setMovedPositionY(final double movedPositionY) {
        this.movedPositionY = movedPositionY;
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

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(final int maxHp) {
        this.maxHp = maxHp;
    }

    public Image getGreenBar() {
        return greenBar;
    }

    public void setGreenBar(final Image greenBar) {
        this.greenBar = greenBar;
    }

}
