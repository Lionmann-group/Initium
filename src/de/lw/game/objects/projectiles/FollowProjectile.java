package de.lw.game.objects.projectiles;

import de.lw.game.core.ImageRepository;

import java.awt.Image;

public class FollowProjectile extends HostileProjectile {

    private double followerX, followerY;

    public FollowProjectile(final double posX, final double posY) {
        super(posX, posY);

        setSizeX(30);
        setSizeY(30);
        setSpeedX(-4);

        img = ImageRepository.getInstance().loadImage("/Objects/Energyball.png");
        img = img.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void move() {
        posX = posX + getSpeedX();

        if (posY < followerY)
            posY = posY + 2;
        else if (posY > followerY) {
            posY = posY - 2;
        }
    }

    @Override
    public void shipPosition(final double x, final double y) {
        followerX = x;
        followerY = y;
    }

}
