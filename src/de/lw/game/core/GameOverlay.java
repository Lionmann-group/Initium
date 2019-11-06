package de.lw.game.core;

import java.awt.*;

public class GameOverlay {

    ImageRepository repository = ImageRepository.getInstance();

    final double position = 0;
    double width = 1200;
    double height = 800;
    GamePanel gamePanel = new GamePanel(0, 20, 1200, 550, repository.loadImage("/Colors/Black.png").getScaledInstance(1200, 550, Image.SCALE_SMOOTH));
    GamePanel lifePanel = new GamePanel(200, 580, 250, 50, repository.loadImage("/Lifebar/Herz3.png").getScaledInstance(250, 50, Image.SCALE_SMOOTH));
    GamePanel shipPanel = new GamePanel(600, 580, 500, 50, repository.loadImage("/Overchargebar/Status0.png").getScaledInstance(500, 50, Image.SCALE_SMOOTH));
    GamePanel shieldPanel = new GamePanel(200, 630, 500, 50, repository.loadImage("/Colors/Black.png").getScaledInstance(500, 50, Image.SCALE_SMOOTH));
    Image background;

    public GameOverlay() {
        background = repository.loadImage("/Colors/Red.png").getScaledInstance((int)getWidth(), (int)getHeight(), Image.SCALE_SMOOTH);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background,(int)position,(int)position,null);
        gamePanel.draw(g2d);
        lifePanel.draw(g2d);
        shipPanel.draw(g2d);
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
    public void overchargebarAnimation(double ammo) {
        if (ammo == 10) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status10.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 9) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status9.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 8) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status8.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 7) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status7.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 6) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status6.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 5) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status5.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 4) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status4.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 3) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status3.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 2) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status2.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 1) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status1.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (ammo == 0) {
            shipPanel.setImg(repository.loadImage("/Overchargebar/Status0.png")
                    .getScaledInstance((int) shipPanel.getSizeX(), (int) shipPanel.getSizeY(), Image.SCALE_SMOOTH));
        }
    }
    public void lifeAnimation(double hp) {
        if (hp == 3) {
            lifePanel.setImg(
                    repository.loadImage("/Lifebar/Herz3.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (hp == 2) {
            lifePanel.setImg(
                    repository.loadImage("/Lifebar/Herz2.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        } else if (hp == 1) {
            lifePanel.setImg(
                    repository.loadImage("/Lifebar/Herz1.png").getScaledInstance((int) lifePanel.getSizeX(),
                            (int) lifePanel.getSizeY(), Image.SCALE_SMOOTH));
        }
    }
}