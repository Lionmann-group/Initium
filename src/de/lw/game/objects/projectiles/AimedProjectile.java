package de.lw.game.objects.projectiles;

import de.lw.game.core.ImageRepository;

import java.awt.Image;

public class AimedProjectile extends HostileProjectile {

    private double aimedX, aimedY;
    private boolean retry;

    public AimedProjectile(final double posX, final double posY) {
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
    public void shipPosition(final double x, final double y) {
        if (!retry) {
            aimedX = x;
            aimedY = y;
            retry = true;
        }
    }

}
