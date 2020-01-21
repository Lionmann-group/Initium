package de.lw.game.core;

import java.awt.Graphics2D;
import java.awt.Image;

public class GameOverlay {

    private final double position = 0;
    private double width = 1200;
    private double height = 800;
    private GamePanel lifePanel = new GamePanel(200, 580, 250, 50, ImageRepository.getInstance().loadImage("/Lifebar/Herz3.png").getScaledInstance(250, 50, Image.SCALE_SMOOTH));
    private GamePanel overcharge = new GamePanel(600, 580, 500, 50, ImageRepository.getInstance().loadImage("/Overchargebar/Status0.png").getScaledInstance(500, 50, Image.SCALE_SMOOTH));
    private Image background;

    GamePanel gamePanel = new GamePanel(0, 20, 1200, 550, ImageRepository.getInstance().loadImage("/Colors/Black.png").getScaledInstance(1200, 550, Image.SCALE_SMOOTH));
    // GamePanel shieldPanel = new GamePanel(200, 630, 500, 50, ImageRepository.getInstance().loadImage("/Colors/Black.png").getScaledInstance(500, 50, Image.SCALE_SMOOTH));

    public GameOverlay() {
        background = ImageRepository.getInstance().loadImage("/Colors/Red.png").getScaledInstance((int) getWidth(), (int) getHeight(), Image.SCALE_SMOOTH);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, (int) position, (int) position, null);
        gamePanel.draw(g2d);
        lifePanel.draw(g2d);
        overcharge.draw(g2d);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    //Animations
    public void setOverchargeTexture(final int ammo) {
        if (ammo == 10) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status10.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 9) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status9.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 8) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status8.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 7) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status7.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 6) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status6.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 5) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status5.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 4) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status4.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 3) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status3.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 2) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status2.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 1) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status1.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 0) {
            overcharge.setImg(ImageRepository.getInstance().loadImage("/Overchargebar/Status0.png")
                    .getScaledInstance((int) overcharge.getSizeX(), (int) overcharge.getSizeY(), Image.SCALE_SMOOTH));
        }
    }

    public void lifeAnimation(double hp) {
        if (hp == 3) {
            lifePanel.setImg(
                    ImageRepository.getInstance().loadImage("/Lifebar/Herz3.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (hp == 2) {
            lifePanel.setImg(
                    ImageRepository.getInstance().loadImage("/Lifebar/Herz2.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (hp == 1) {
            lifePanel.setImg(
                    ImageRepository.getInstance().loadImage("/Lifebar/Herz1.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        }
    }

}
