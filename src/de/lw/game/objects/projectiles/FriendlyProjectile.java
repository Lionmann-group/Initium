package de.lw.game.objects.projectiles;

import de.lw.game.core.ImageRepository;

import java.awt.*;

public class FriendlyProjectile extends BaseProjectile {

    public FriendlyProjectile(double posX, double posY) {
        super(posX, posY);
        setSpeedX(14);
        setSizeX(20);
        setSizeY(10);
        img = ImageRepository.getInstance().loadImage("/Objects/Shot.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void shipPosition(final double x, final double y) {}

}
