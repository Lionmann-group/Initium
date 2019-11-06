package de.lw.game.objects.projectiles;

import de.lw.game.core.ImageRepository;

import java.awt.Image;

public class AimedProjectile extends HostileProjectile {

    double aimedX;
    double aimedY;
    boolean retry;

    public AimedProjectile(double posX, double posY) {
        super(posX, posY);
        setSizeX(20);
        setSizeY(10);
        setSpeedX(-10);
        img = ImageRepository.getInstance().loadImage("/Objects/Shot.png");
        img = img.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void move() {
        posX = posX + getSpeedX();
        if (posY < aimedY)
            posY = posY + 3;
        else if (posY > aimedY) {
            posY = posY - 3;
        }
    }

    @Override
    public void shipPosition(double x, double y) {
        if (!retry) {
            aimedX = x;
            aimedY = y;
            retry = true;
        }
    }

}
