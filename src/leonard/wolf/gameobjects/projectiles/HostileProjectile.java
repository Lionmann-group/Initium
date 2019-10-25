package leonard.wolf.gameobjects.projectiles;


import leonard.wolf.gamecore.ImageRepository;

import java.awt.*;

public class HostileProjectile extends BaseProjectile {

    public HostileProjectile(double posX, double posY) {
        super(posX, posY);
        setSpeedX(-9);
        setSizeX(20);
        setSizeY(10);
        img = ImageRepository.getInstance().loadImage("/Objects/Shot.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }
}